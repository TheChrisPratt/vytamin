package com.anodyzed.vyta.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import org.apache.cxf.bus.spring.SpringBus;
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
//@EnableJpaRepositories("com.anodyzed.vyta.repositories")
@ComponentScan("com.anodyzed.vyta") //(excludeFilters=@ComponentScan.Filter(type= FilterType.ANNOTATION,pattern="com.anodyzed.vyta.repositories"))
@PropertySource({"classpath:application.properties"})
public class AppConfiguration {

  @Bean
  public SpringBus springBus () {
    return new SpringBus();
  } //springBus

  @Bean
  public JacksonJsonProvider jsonProvider () {
    return new JacksonJsonProvider();
  } //jsonProvider

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigurer () {
    return new PropertySourcesPlaceholderConfigurer();
  } //propertyConfigurer

} //*AppConfiguration
