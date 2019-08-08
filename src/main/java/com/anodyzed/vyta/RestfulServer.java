package com.anodyzed.vyta;

import com.anodyzed.vyta.config.AppConfiguration;
import com.anodyzed.vyta.config.PropertiesAccessor;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_XML;

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
  private static List<ResourceProvider> providers;
  private static Map<Object,Object> extensionMappings;

  private static List<Object> getServices () {
    if(services == null) {
      services = new ArrayList<>(ctx.getBeansWithAnnotation(Controller.class).values());
    }
    return services;
  } //getServices

  private static List<ResourceProvider> getProviders () {
    if(providers == null) {
      providers = new ArrayList<>();
      providers.add(new SingletonResourceProvider(ctx.getBean(JacksonJsonProvider.class)));
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

  private static Map<Object,Object> getExtensionMappings () {
    if(extensionMappings == null) {
      extensionMappings = new HashMap<>();
      extensionMappings.put("xml",TEXT_XML);
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
    factoryBean.setResourceProviders(getProviders());
    factoryBean.setExtensionMappings(getExtensionMappings());
    factoryBean.setAddress(props.get("server.url"));
    Server server = factoryBean.create();
    log.info("Server Startup Complete: {}",server.getDestination());
  } //main

} //*RestfulServer
