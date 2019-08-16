package com.anodyzed.vyta.config;

import javax.servlet.ServletContext;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * WebAppInitializer
 *
 * @author Chris Pratt
 * @since 2019-05-23
 */
public class WebAppInitializer implements WebApplicationInitializer {
  private static final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

  @Override
  public void onStartup (ServletContext container) {
    log.trace("--==<<(( Web App Initializing ))>>==-----");
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfiguration.class,SecurityConfig.class/*,PersistenceConfig.class*/);
    container.addListener(new ContextLoaderListener(context));
    container.addServlet("cxf",new CXFServlet()).addMapping("/api/*");
//    container.addFilter("springSecurityFilterChain",new DelegatingFilterProxy()).addMappingForServletNames(EnumSet.of(REQUEST,FORWARD,INCLUDE,ERROR),false,"cxf");
    context.refresh();
  } //onStartup
  
} //*WebAppInitializer
