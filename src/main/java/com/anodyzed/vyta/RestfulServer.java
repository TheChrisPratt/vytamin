package com.anodyzed.vyta;

import com.anodyzed.vyta.config.AppConfiguration;
import com.anodyzed.vyta.resources.CourseResource;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * RestfulServer
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulServer {

  public static void main (String... args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);

    JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
    factoryBean.setResourceClasses(CourseResource.class);
    factoryBean.setResourceProvider(new SingletonResourceProvider(ctx.getBean(CourseResource.class)));
    factoryBean.setAddress("http://localhost:8888/");
    Server server = factoryBean.create();

  } //main

} //*RestfulServer
