package com.anodyzed.vyta.config.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExceptionFormatter
 *
 * @author Chris Pratt
 * @since 2019-09-18
 */
class ExceptionFormatter {
  private static final Logger log = LoggerFactory.getLogger(ExceptionFormatter.class);

  static Response formatException (Exception x,Status status) {
    log.error("Exception thrown in REST endpoint",x);
    return Response.status(status).build();
  } //formatException

  static Response formatException (Exception x,int statusCode) {
    log.error("Exception thrown in REST endpoint",x);
    return Response.status(statusCode).build();
  } //formatException

} //*ExceptionFormatter
