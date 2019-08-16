package com.anodyzed.vyta.resources;

import com.anodyzed.vyta.model.Course;
import com.anodyzed.vyta.services.CourseService;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * CourseResource
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Path("/course")
@Produces(APPLICATION_JSON)
@Controller("courseResource")
public class CourseResource {
  private static final Logger log = LoggerFactory.getLogger(CourseResource.class);

  @Autowired
  private CourseService courseService;

  @GET
  @Path("/{courseId}")
  @Secured("ROLE_USER")
  public Course getCourse (@PathParam("courseId") long courseId) {
    log.trace("--==<<(( Getting Course {} ))>>==-----",courseId);
    return courseService.getById(courseId);
  } //getCourse

  @PUT
  @Path("/{courseId}")
  @Secured({"ROLE_USER","ROLE_ADMIN"})
  public Response updateCourse (@PathParam("courseId") long courseId,Course course) {
    Course existing = courseService.getById(courseId);
    if(existing != null) {
      log.trace("--==<<(( Updating Course {} ))>>==-----",courseId);
      if(!course.equals(existing)) {
        courseService.update(courseId,course);
        return Response.noContent().build();
      } else {
        return Response.notModified().build();
      }
    } else {
      log.warn("Course {} not found",courseId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  } //updateCourse

} //*CourseResource
