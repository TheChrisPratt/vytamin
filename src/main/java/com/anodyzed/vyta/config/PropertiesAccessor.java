package com.anodyzed.vyta.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * PropertiesAccessor
 *
 * @author Chris Pratt
 * @since 2019-08-07
 */
public class PropertiesAccessor implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  private final Map<String,String> cache = new ConcurrentHashMap<>();

  @Override
  public void setApplicationContext (ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  } //setApplicationContext

  public String get (String key) {
    if(cache.containsKey(key)) {
      return cache.get(key);
    } else {
      String prop = null;
      try {
        prop = applicationContext.getEnvironment().resolvePlaceholders("${" + key.trim() + '}');
        cache.put(key,prop);
      } catch(IllegalArgumentException x) {
        // Property Not Found, return null;
      }
      return prop;
    }
  } //get
  
} //*PropertiesAccessor
