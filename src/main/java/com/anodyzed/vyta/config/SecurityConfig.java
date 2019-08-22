package com.anodyzed.vyta.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig
 *
 * @author Chris Pratt
 * @since 2019-08-11
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

  @Override
  public void configure (AuthenticationManagerBuilder auth) throws Exception {
    super.configure(auth);
    log.trace("--==<<(( Configuring Authentication Manager ))>>==-----");
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth.inMemoryAuthentication().withUser("bob").password(encoder.encode("{noop}bobpassword")).roles("USER")
                           .and().withUser("fred").password(encoder.encode("{noop}fredpassword")).roles("ADMIN","USER");
  } //configure

//  @Override
//  public void configure (WebSecurity web) {
//    web.ignoring().antMatchers("/api/**");
//  } //configure

  @Override
  protected void configure (HttpSecurity http) throws Exception {
    log.trace("--==<<(( Configuring HTTP Security ))>>==-----");
    http
//        .oauth2Login()
//        .openidLogin()
//        .rememberMe()
//        .authorizeRequests().antMatchers("/**").authenticated()
        .authorizeRequests().anyRequest().authenticated()
        .and().httpBasic()
        .and().csrf().disable();
  } //configure

} //*SecurityConfig
