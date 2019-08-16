package com.anodyzed.vyta.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * AppConfiguration
 *
 * @author Chris Pratt
 * @since 2019-05-23
 */
@Configuration
@ComponentScan("com.anodyzed.vyta") //(excludeFilters=@ComponentScan.Filter(type=FilterType.ANNOTATION,pattern="com.anodyzed.vyta.repositories"))
@PropertySource("classpath:application.properties")
public class AppConfiguration {
  private static final Logger log = LoggerFactory.getLogger(AppConfiguration.class);

  @Bean
  public SpringBus springBus () {
    log.trace("--==<<(( Getting Spring Bus ))>>==-----");
    return new SpringBus();
  } //springBus

  @Bean
  public JacksonJsonProvider jsonProvider () {
    log.trace("--==<<(( Getting Jackson JSON Provider ))>>==-----");
    return new JacksonJsonProvider();
  } //jsonProvider

  @Bean
  public JAXBElementProvider jaxbXmlProvider () {
    log.trace("--==<<(( Getting JAXB XML Provider ))>>==-----");
    return new JAXBElementProvider();
  } //jaxbXmlProvider

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer () {
    log.trace("--==<<(( Getting Property Placeholder Configurer ))>>==-----");
    return new PropertySourcesPlaceholderConfigurer();
  } //propertyConfigurer

  @Bean
  public static PropertiesAccessor propertyAccessor () {
    log.trace("--==<<(( Getting Property Accessor ))>>==-----");
    return new PropertiesAccessor();
  } //propertyAccessor

} //*AppConfiguration
