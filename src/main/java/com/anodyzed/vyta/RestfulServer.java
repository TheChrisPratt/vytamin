package com.anodyzed.vyta;

import com.anodyzed.vyta.config.AppConfiguration;
import com.anodyzed.vyta.config.PropertiesAccessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * RestfulServer
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulServer {
  private static final Logger log = LoggerFactory.getLogger(RestfulServer.class);

  private static ApplicationContext ctx;

  private static List<Object> services;
//  private static final List<Class<?>> providerClasses = Arrays.asList(JacksonJsonProvider.class);
  private static List<Object> providers;
  private static Map<Object,Object> extensionMappings;

  private static List<Object> getServices () {
    if(services == null) {
      services = new ArrayList<>(ctx.getBeansWithAnnotation(Controller.class).values());
    }
    return services;
  } //getServices

  private static List<Object> getProviders () {
    if(providers == null) {
      providers = new ArrayList<>();
      providers.add(ctx.getBean("jsonProvider"));
//      providers.add(new ExceptionRestHandler());
//      Controller controller;
//      SpringResourceFactory provider;
//      for(Class<?> providerClass : providerClasses) {
//        if((controller = providerClass.getAnnotation(Controller.class)) != null) {
//          provider = new SpringResourceFactory(controller.value());
//          provider.setApplicationContext(ctx);
//          providers.add(provider);
//        }
//      }
    }
    return providers;
//    return new SingletonResourceProvider(ctx.getBean(CourseResource.class));
  } //getProviders

  private static List<Feature> getFeatures () {
//    return Collections.singletonList(new LoggingFeature());
    return Collections.emptyList();
  } //getFeatures

  private static Map<Object,Object> getExtensionMappings () {
    if(extensionMappings == null) {
      extensionMappings = new HashMap<>();
//      extensionMappings.put("xml",TEXT_XML);
      extensionMappings.put("json",APPLICATION_JSON);
    }
    return extensionMappings;
  } //getExtensionMappings

  public static void main (String... args) {
    log.info("Starting Server...");
    ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
    PropertiesAccessor props = ctx.getBean("propertyAccessor",PropertiesAccessor.class);
    JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
    factoryBean.setBus((SpringBus)ctx.getBean("springBus"));
    factoryBean.setServiceBeans(getServices());
    factoryBean.setProviders(getProviders());
    factoryBean.setFeatures(getFeatures());
    factoryBean.setExtensionMappings(getExtensionMappings());
    factoryBean.setAddress(props.get("server.url"));
    Server server = factoryBean.create();
    log.info("Server Startup Complete: {}",server.getDestination());
  } //main

} //*RestfulServer
