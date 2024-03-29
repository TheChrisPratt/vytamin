package com.anodyzed.vyta.services;

import com.anodyzed.vyta.model.Course;
import com.anodyzed.vyta.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentServiceImpl
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
  private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

  @Autowired
  private CourseService courseService;

  private AtomicLong nextId = new AtomicLong(0);
  private Map<Long,Student> students;
  private boolean initialized = false;

  private void init () {
    if(!initialized) {
      initialized = true;
      log.trace("--==<<(( Initializing Student Service ))>>==-----");
      log.trace("--==<<(( CourseService is {} ))>>==-----",(courseService != null) ? "not null" : "null");
      students = new HashMap<>();
      Course course = courseService.getById(1);
      Student student1 = new Student();
      Student student2 = new Student();
      student1.setId(nextId.incrementAndGet());
      student1.setName("Student A");
      course.register(student1);
      students.put(student1.getId(),student1);
      student2.setId(nextId.incrementAndGet());
      student2.setName("Student B");
      course.register(student2);
      students.put(student2.getId(),student2);
    }
  } //init

  @Override
  public Student getById (long id) {
    init();
    return students.get(id);
  } //getById

  @Override
  public void create (Student student) {
    init();
    student.setId(nextId.incrementAndGet());
    students.put(student.getId(),student);
  } //create

  @Override
  public void delete (Student student) {
    init();
    students.remove(student.getId());
  } //delete

} //*StudentServiceImpl
