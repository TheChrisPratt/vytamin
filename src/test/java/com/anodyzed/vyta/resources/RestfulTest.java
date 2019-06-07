package com.anodyzed.vyta.resources;

import com.anodyzed.vyta.model.Course;
import com.anodyzed.vyta.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.bind.JAXB;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * RestfulTest
 *
 * @author Chris Pratt
 * @since 2019-05-27
 */
public class RestfulTest {
  private static String BASE_URL = "http://localhost:8888/baeldung/course/";
  private static CloseableHttpClient client;

  @BeforeClass
  public static void createClient () {
    client = HttpClients.createDefault();
  } //createClient

  @AfterClass
  public static void closeClient () throws IOException {
    client.close();
  } //closeClient

  private Course getCourse (int courseOrder) throws IOException {
    URL url = new URL(BASE_URL + courseOrder);
    InputStream input = url.openStream();
    return JAXB.unmarshal(new InputStreamReader(input),Course.class);
  } //getCourse

  private Student getStudent (int courseOrder,int studentOrder)
    throws IOException {
    URL url = new URL(BASE_URL + courseOrder + "/student/" + studentOrder);
    InputStream input = url.openStream();
    return JAXB.unmarshal(new InputStreamReader(input),Student.class);
  } //getStudent

} //*RestfulTest
