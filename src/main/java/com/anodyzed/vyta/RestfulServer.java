package com.anodyzed.vyta;

import com.anodyzed.vyta.config.AppConfiguration;
import com.anodyzed.vyta.resources.CourseResource;
import com.anodyzed.vyta.resources.StudentResource;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * RestfulServer
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulServer {
  private static final Logger log = LoggerFactory.getLogger(RestfulServer.class);

  private static List<Class<?>> getResources () {
    List<Class<?>> resources = new ArrayList<>();
    resources.add(CourseResource.class);
    resources.add(StudentResource.class);
    return resources;
  } //getResources

  private static List<ResourceProvider> getProviders (ApplicationContext ctx) {
    List<ResourceProvider> providers = new ArrayList<>();
    SpringResourceFactory provider = new SpringResourceFactory("springBus");
    provider.setApplicationContext(ctx);
    providers.add(provider);
//    providers.add(SingletonResourceProvider(ctx.getBean(CourseResource.class)));
    return providers;
  } //getProviders

  public static void main (String... args) {
    log.info("Starting Server...");
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
    factoryBean.setResourceClasses(getResources());
    factoryBean.setResourceProviders(getProviders(ctx));
    factoryBean.setAddress("http://localhost:8888/");
    Server server = factoryBean.create();
    log.info("Server Startup Complete: {}",server.getDestination());
  } //main

} //*RestfulServer
