package com.anodyzed.vyta.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * PersistenceConfig
 *
 * @author Chris Pratt
 * @since 2019-08-11
 */
//@Configuration
//@EnableJpaRepositories("com.anodyzed.vyta.repositories")
@PropertySource("classpath:application.properties")
public class PersistenceConfig {
  @Value("jdbc.driver")
  private static String jdbcDriver;
  @Value("jdbc.url")
  private static String jdbcUrl;
  @Value("jdbc.username")
  private static String jdbcUsername;
  @Value("jdbc.password")
  private static String jdbcPassword;

  @Bean
  public static DataSource dataSource () {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(jdbcUrl,jdbcUsername,jdbcPassword);
    dataSource.setDriverClassName(jdbcDriver);
    return dataSource;
  } //dataSource

} //*PersistenceConfig
