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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static javax.ws.rs.core.MediaType.TEXT_XML;

/**
 * StudentResource
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Path("/student")
//@Produces(APPLICATION_JSON)
@Produces(TEXT_XML)
@Controller("studentResource")
public class StudentResource {
  @Autowired
  private StudentService studentService;

  @GET
  @Path("/{studentId}")
  public Student getStudent (@PathParam("studentId") long studentId) {
    return studentService.getById(studentId);
  } //getStudent

  @POST
  public Response createStudent (Student student) {
    if(student.getId() == null) {
      studentService.create(student);
      return Response.created(URI.create("/student/" + student.getId())).build();
    } else {
      return Response.status(Response.Status.CONFLICT).build();
    }
  } //createStudent

  @DELETE
  @Path("/{studentId}")
  public Response deleteStudent (@PathParam("studentId") long studentId) {
    Student student = studentService.getById(studentId);
    if(student != null) {
      studentService.delete(student);
      return Response.noContent().build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  } //deleteStudent

} //*StudentResource
