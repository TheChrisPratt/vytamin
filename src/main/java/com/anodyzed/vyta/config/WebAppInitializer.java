package com.anodyzed.vyta.config;

import javax.servlet.ServletContext;

import org.apache.cxf.transport.servlet.CXFServlet;
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

  @Override
  public void onStartup (ServletContext container) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfiguration.class);
    container.addListener(new ContextLoaderListener(context));
    container.addServlet("cxf",new CXFServlet()).addMapping("/api/*");
  } //onStartup
  
} //*WebAppInitializer
