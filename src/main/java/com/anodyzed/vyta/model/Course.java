package com.anodyzed.vyta.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Course
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@XmlRootElement(name="course")
public class Course {
  private Long id;
  private String name;
  private List<Student> students = new ArrayList<>();

  public Long getId () {
    return id;
  } //getId

  public void setId (long id) {
    this.id = id;
  } //setId

  public String getName () {
    return name;
  } //getName

  public void setName (String name) {
    this.name = name;
  } //setName

  public List<Student> getStudents () {
    return students;
  } //getStudents

  public void setStudents (List<Student> students) {
    this.students = students;
  } //setStudents

  public void register (Student student) {
    students.add(student);
  } //register

  public Student findStudentById (long id) {
    return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
  } //findStudentById

} //*Course
