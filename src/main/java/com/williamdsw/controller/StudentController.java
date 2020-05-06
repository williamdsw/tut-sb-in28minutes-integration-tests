package com.williamdsw.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamdsw.model.Course;
import com.williamdsw.service.StudentService;

@RestController
@RequestMapping ("/students")
public class StudentController {
	
	// FIELDS
	
	@Autowired
	private StudentService studentService;
	
	// ENDPOINTS
	
	@GetMapping ("/{studentId}/courses")
	public List<Course> retrieveCoursesForStudent (@PathVariable String studentId) {
		return studentService.retrieveCourses (studentId);
	}
	
	@GetMapping ("/{studentId}/courses/{courseId}")
	public Course retrieveDetailsForCourse (@PathVariable String studentId, @PathVariable String courseId) {
		return studentService.retrieveCourse (studentId, courseId);
	}
	
	@PostMapping ("/{studentId}/courses")
	public ResponseEntity<Void> registerStudentForCourse (@PathVariable String studentId, @RequestBody Course newCourse) {
		Course course = studentService.addCourse (studentId, newCourse);

		if (course == null) {
			return ResponseEntity.noContent ().build ();
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest ().path ("/{id}").buildAndExpand (course.getId ()).toUri ();
		return ResponseEntity.created (location).build ();
	}
}
