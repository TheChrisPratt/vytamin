package com.anodyzed.vyta.resources;

import com.anodyzed.vyta.model.Student;
import com.anodyzed.vyta.services.StudentService;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
 * StudentResource
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Path("/student")
@Produces(APPLICATION_JSON)
@Controller("studentResource")
public class StudentResource {
  private static final Logger log = LoggerFactory.getLogger(StudentResource.class);

  @Autowired
  private StudentService studentService;

  @GET
  @Path("/{studentId}")
  @Secured("ROLE_USER")
  public Student getStudent (@PathParam("studentId") long studentId) {
    log.trace("--==<<(( Getting Student {} ))>>==-----",studentId);
    return studentService.getById(studentId);
  } //getStudent

  @POST
  @Secured({"ROLE_USER","ROLE_ADMIN"})
  public Response createStudent (Student student) {
    if(student.getId() == null) {
      log.trace("--==<<(( Creating Student {} ))>>==-----",student.getName());
      studentService.create(student);
      return Response.created(URI.create("/student/" + student.getId())).build();
    } else {
      log.warn("Create Student cannot be supplied an existing ID");
      return Response.status(Response.Status.CONFLICT).build();
    }
  } //createStudent

  @DELETE
  @Path("/{studentId}")
  @Secured({"ROLE_USER","ROLE_ADMIN"})
  public Response deleteStudent (@PathParam("studentId") long studentId) {
    Student student = studentService.getById(studentId);
    if(student != null) {
      log.trace("--==<<(( Removing Student {} ))>>==-----",student.getName());
      studentService.delete(student);
      return Response.noContent().build();
    } else {
      log.debug("Student {} not found",studentId);
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  } //deleteStudent

} //*StudentResource
