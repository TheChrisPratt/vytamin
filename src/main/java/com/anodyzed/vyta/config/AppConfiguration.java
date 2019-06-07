package com.anodyzed.vyta.config;

import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfiguration
 *
 * @author Chris Pratt
 * @since 2019-05-23
 */
@Configuration
//@EnableJpaRepositories("com.anodyzed.vyta.repositories")
@ComponentScan //(excludeFilters=@ComponentScan.Filter(type= FilterType.ANNOTATION,pattern="com.anodyzed.vyta.repositories"))
public class AppConfiguration {

  @Bean
  public SpringBus springBus () {
    return new SpringBus();
  } //springBus

} //*AppConfiguration