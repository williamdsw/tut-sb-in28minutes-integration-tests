package com.williamdsw.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	// FIELDS
	
	private String id;
	private String name;
	private String description;
	private List<Course> courses = new ArrayList<>();
	
	// CONSTRUCTORS
	
	public Student() {}
	public Student(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	// GETTERS / SETTERS
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	// OVERRIDED FUNCTIONS
	
	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s, description=%s, course=%s]", this.getId (),
							 this.getName (), this.getDescription (), this.getCourses ());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}