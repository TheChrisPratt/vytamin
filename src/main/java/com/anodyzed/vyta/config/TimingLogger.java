package com.anodyzed.vyta.config;

import com.anodyzed.vyta.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;

/**
 * TimingLogger
 *
 * This class is used to log the timings of methods called using Spring AOP.
 * It prints the stack in a human readable form and the amount of time it took
 * to complete the call.
 *
 * Here is a sample of what it outputs:
 *
 * @author Chris Pratt
 * @since 2016-03-11
 */
//@Aspect
//@Configuration
public class TimingLogger {
  private static final Logger log = LoggerFactory.getLogger(TimingLogger.class);

  private static final ThreadLocal<List<String>> callStack = ThreadLocal.withInitial(ArrayList::new);
  private static final ThreadLocal<Integer> callStackDepth = ThreadLocal.withInitial(() -> 0);

  @Around("(execution(public * com.anodyzed.vyta.resources.*Resource.*(..))) || " +
          "(execution(public * com.anodyzed.vyta.services.*ServiceImpl.*(..))) || " +
          "(execution(public * com.anodyzed.vyta.services.*Helper.*(..))) || " +
          "(execution(public * com.anodyzed.vyta.repositories.*Repository*.*(..)))")
  public Object profile (ProceedingJoinPoint call) throws Throwable {
    long start = 0L;
    boolean timingEnabled = log.isTraceEnabled();

    List<String> logMessageList = null;
    int logMessageCurrentIndex = 0;

    if(timingEnabled) {
      callStackDepth.set(callStackDepth.get() + 1);

      logMessageList = callStack.get();
      logMessageCurrentIndex = logMessageList.size();
        // Just add a placeholder
      logMessageList.add(null);
      start = System.currentTimeMillis();
    }
    try {
      return call.proceed();
    } finally {
      if(timingEnabled) {
        long end = System.currentTimeMillis();
        Integer depth = callStackDepth.get();
        callStackDepth.set(--depth);

        String padding = Strings.repeat("|  ",depth);
        logMessageList.set(logMessageCurrentIndex,String.format("%s|-- %,d millisecond(s) to complete %s",padding,(end - start),call.getSignature()));

        if(depth == 0) {
          log.trace(logMessageList.stream().collect(Collectors.joining("\n","\n","")));
          callStack.remove();
        }
      }
    }
  } //profile

} //*TimingLogger
