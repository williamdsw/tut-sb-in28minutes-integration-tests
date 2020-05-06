package com.williamdsw.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.williamdsw.model.Course;
import com.williamdsw.model.Student;

@Service
public class StudentService {
	
	// FIELDS
	
	private static List<Student> students = new ArrayList<>();
	
	// STATIC BLOCK
	
	static {
		List<String> steps1 = Arrays.asList ("Learn Maven", "Import Project", "First Example", "Second Example");
		List<String> steps2 = Arrays.asList ("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example");
		List<String> steps3 = Arrays.asList ("Pom.xml", "Build Life Cycle", "Parent POM", "Importing into Eclipse");
		Course course1 = new Course ("1", "Spring", "10 Steps", steps1);
		Course course2 = new Course ("2", "Spring MVC", "10 Examples", steps1);
		Course course3 = new Course ("3", "Spring Boot", "6K Students", steps2);
		Course course4 = new Course ("4", "Spring", "Most popular maven course on internet", steps3);
		
		Student dave = new Student ("1", "Dave Mustaine", "Web Programmer");
		dave.getCourses().addAll (Arrays.asList (course1, course2));
		Student james = new Student ("2", "James Hetfield", "Java Programmer");
		james.getCourses().addAll (Arrays.asList (course1, course2, course3, course4));
		
		students.add (dave);
		students.add (james);
	}
	
	// HELPER FUNCTIONS
	
	public List<Student> retrieveAllStudents () {
		return students;
	}
	
	public Student retrieveStudent (String studentId) {
		for (Student student : students) {
			if (student.getId ().equals (studentId) ) {
				return student;
			}
		}
		
		return null;
	}
	
	public List<Course> retrieveCourses (String studentId) {
		Student student = this.retrieveStudent (studentId);
		if (student == null) {
			return null;
		}
		
		return student.getCourses ();
	}
	
	public Course retrieveCourse (String studentId, String courseId) {
		Student student = this.retrieveStudent (studentId);
		if (student == null) {
			return null;
		}
		
		for (Course course : student.getCourses () ) {
			if (course.getId ().equals (courseId)) {
				return course;
			}
		}
		
		return null;
	}
	
	public Course addCourse (String studentId, Course course) {
		Student student = this.retrieveStudent (studentId);
		if (student == null) {
			return null;
		}
		
		SecureRandom random = new SecureRandom ();
		String randomId = new BigInteger (130, random).toString (32);
		course.setId (randomId);
		student.getCourses ().add (course);

		return course;
	}
}
