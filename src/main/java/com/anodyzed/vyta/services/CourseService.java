package com.anodyzed.vyta.services;

import com.anodyzed.vyta.model.Course;

/**
 * CourseService
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
public interface CourseService {
  Course getById(long id);
  void update(long id,Course course);
} //#CourseService
