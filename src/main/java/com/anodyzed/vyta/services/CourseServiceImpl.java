package com.anodyzed.vyta.services;

import com.anodyzed.vyta.model.Course;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CourseServiceImpl
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
  private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

  private Map<Long,Course> courses;

  public CourseServiceImpl () {
    log.info("Constructing Course Service");
    courses = new HashMap<>();
    Course course1 = new Course();
    Course course2 = new Course();
    course1.setId(1);
    course1.setName("REST with Spring");
    courses.put(course1.getId(),course1);
    course2.setId(2);
    course2.setName("Learn Spring Security");
    courses.put(course2.getId(),course2);
  } //CourseServiceImpl

  @Override
  public Course getById (long id) {
    return courses.get(id);
  } //getById

  @Override
  public void update (long id,Course course) {
    courses.put(id,course);
  } //update

} //*CourseServiceImpl
