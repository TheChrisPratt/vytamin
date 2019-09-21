package com.anodyzed.vyta;

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
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * RestfulServer
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulServer {
  private static final Logger log = LoggerFactory.getLogger(RestfulServer.class);

  private static ApplicationContext context;

  private static List<Object> services;
  private static List<Object> providers;
  private static Map<Object,Object> extensionMappings;

  private static List<Object> getServices () {
    if(services == null) {
      services = new ArrayList<>(context.getBeansWithAnnotation(Controller.class).values());
    }
    return services;
  } //getServices

  private static List<Object> getProviders () {
    if(providers == null) {
      providers = new ArrayList<>();
      providers.add(context.getBean("jsonProvider"));
      providers.add(context.getBean("jaxbXmlProvider"));
      providers.add(context.getBean("authenticationExceptionMapper"));
    }
    return providers;
  } //getProviders

  private static List<Feature> getFeatures () {
//    return Collections.singletonList(new LoggingFeature());
    return Collections.emptyList();
  } //getFeatures

  private static Map<Object,Object> getExtensionMappings () {
    if(extensionMappings == null) {
      extensionMappings = new HashMap<>();
      extensionMappings.put("xml",APPLICATION_XML);
      extensionMappings.put("json",APPLICATION_JSON);
    }
    return extensionMappings;
  } //getExtensionMappings

  public static void main (String... args) {
    log.info("--==<<(( Starting Server... ))>>==-----");
    context = new AnnotationConfigApplicationContext("com.anodyzed.vyta.config");
    PropertiesAccessor props = context.getBean("propertyAccessor",PropertiesAccessor.class);
    JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
    factoryBean.setBus(context.getBean("springBus",SpringBus.class));
    factoryBean.setServiceBeans(getServices());
    factoryBean.setProviders(getProviders());
    factoryBean.setFeatures(getFeatures());
    factoryBean.setExtensionMappings(getExtensionMappings());
    factoryBean.setAddress(props.get("server.url"));
    Server server = factoryBean.create();
    log.info("--==<<(( Server Startup Complete: {} ))>>==-----",server.getDestination().getAddress().getAddress().getValue());
  } //main

} //*RestfulServer
