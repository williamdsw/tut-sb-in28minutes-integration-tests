package com.williamdsw.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.williamdsw.model.Course;

@ExtendWith (SpringExtension.class)
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

	// FIELDS
	
	@LocalServerPort private int port;
	TestRestTemplate restTemplate = new TestRestTemplate ();
	HttpHeaders headers = new HttpHeaders ();
	
	private final String RETRIEVE_STUDENT_COURSE_URL = "/students/1/courses/1";
	private final String ADD_COURSE_URL = "/students/1/courses";
	
	// TESTS
	
	@Test
	public void testRetrieveStudentCourse () throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String> (null, headers);
		ResponseEntity<String> response = restTemplate.exchange (createURLWithPort (RETRIEVE_STUDENT_COURSE_URL), HttpMethod.GET, entity, String.class);
		String expected = "{ \"id\": \"1\", \"name\": \"Spring\", \"description\": \"10 Steps\", \"steps\": [\"Learn Maven\", \"Import Project\", \"First Example\", \"Second Example\"] }";
		JSONAssert.assertEquals (expected, response.getBody(), false);
		System.out.println(response.getBody());
	}
	
	@Test
	public void testAddCourse () {
		List<String> steps = Arrays.asList ("Import Project", "Run Tests Class");
		Course course = new Course(null, "Java Tests", "Integration Test in Spring Boot", steps);
		HttpEntity<Course> entity = new HttpEntity<Course> (course, headers);
		ResponseEntity<String> response = restTemplate.exchange (createURLWithPort (ADD_COURSE_URL), HttpMethod.POST, entity, String.class);
		String location = response.getHeaders ().getLocation ().toString();
		assertTrue (location.contains ("/students/1/courses/"));
		System.out.println(location);
	}
	
	// HELPER FUNCTIONS
	
	private String createURLWithPort (String uri) {
		return String.format("http://localhost:%1s%2s", port, uri);
	}
}