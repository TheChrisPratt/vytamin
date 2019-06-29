package com.anodyzed.vyta;

import com.anodyzed.vyta.config.AppConfiguration;
import com.anodyzed.vyta.resources.CourseResource;
import com.anodyzed.vyta.resources.StudentResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * RestfulServer
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulServer {
  private static final Logger log = LoggerFactory.getLogger(RestfulServer.class);

  private static final List<Class<?>> resources = Arrays.asList(CourseResource.class,StudentResource.class);
  private static List<ResourceProvider> providers;

  private static List<Class<?>> getResources () {
    return resources;
  } //getResources

  private static List<ResourceProvider> getProviders (ApplicationContext ctx) {
    if(providers == null) {
      Component component;
      SpringResourceFactory provider;
      providers = new ArrayList<>();
      for(Class<?> resource : resources) {
        if((component = resource.getAnnotation(Component.class)) != null) {
          provider = new SpringResourceFactory(component.value());
          provider.setApplicationContext(ctx);
          providers.add(provider);
        }
      }
    }
    return providers;
//    return new SingletonResourceProvider(ctx.getBean(CourseResource.class));
  } //getProviders

  public static void main (String... args) {
    log.info("Starting Server...");
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
    factoryBean.setBus((SpringBus)ctx.getBean("springBus"));
    factoryBean.setResourceClasses(getResources());
    factoryBean.setResourceProviders(getProviders(ctx));
    factoryBean.setAddress("http://localhost:8888/");
    Server server = factoryBean.create();
    log.info("Server Startup Complete: {}",server.getDestination());
  } //main

} //*RestfulServer
