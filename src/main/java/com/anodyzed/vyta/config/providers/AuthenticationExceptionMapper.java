package com.anodyzed.vyta.config.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * AuthenticationExceptionMapper
 *
 * @author Chris Pratt
 * @since 2019-09-18
 */
@Provider
@Service("authenticationExceptionMapper")
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

  @Override
  public Response toResponse (AuthenticationException exception) {
    return ExceptionFormatter.formatException(exception,UNAUTHORIZED);
  } //toResponse

} //*AuthenticationExceptionMapper
