package com.anodyzed.vyta.resources;

import com.anodyzed.vyta.model.Course;
import com.anodyzed.vyta.services.CourseService;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * CourseResource
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Path("/course")
@Produces("text/xml")
public class CourseResource {
  @Autowired
  private CourseService courseService;

  @GET
  @Path("/{courseId}")
  public Course getCourse (@PathParam("courseId") long courseId) {
    return courseService.getById(courseId);
  } //getCourse

  @PUT
  @Path("/{courseId}")
  public Response updateCourse (@PathParam("courseId") long courseId,Course course) {
    Course existing = courseService.getById(courseId);
    if(existing != null) {
      if(!course.equals(existing)) {
        courseService.update(courseId,course);
        return Response.noContent().build();
      } else {
        return Response.notModified().build();
      }
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  } //updateCourse

} //*CourseResource
