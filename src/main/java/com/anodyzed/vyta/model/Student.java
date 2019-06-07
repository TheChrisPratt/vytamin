package com.anodyzed.vyta.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Student
 *
 * @author Chris Pratt
 * @since 2019-05-25
 */
@XmlRootElement(name="student")
public class Student {
  private Long id;
  private String name;

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

} //*Student
