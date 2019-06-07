package com.anodyzed.vyta.services;

import com.anodyzed.vyta.model.Student;

/**
 * StudentService
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
public interface StudentService {
  Student getById(long id);
  void create(Student student);
  void delete(Student student);
} //#StudentService
