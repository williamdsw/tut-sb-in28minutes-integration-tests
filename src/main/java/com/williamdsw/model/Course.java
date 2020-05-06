package com.williamdsw.model;

import java.util.ArrayList;
import java.util.List;

public class Course {

	// FIELDS

	private String id;
	private String name;
	private String description;
	private List<String> steps = new ArrayList<>();

	// CONSTRUCTORS

	public Course() {
	}

	public Course(String id, String name, String description, List<String> steps) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.steps = steps;
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

	public List<String> getSteps() {
		return steps;
	}

	// OVERRIDED FUNCTIONS

	@Override
	public String toString() {
		return String.format ("Course [id=%s, name=%s, description=%s, steps=%s]", 
							  this.getId (), this.getName (), this.getDescription (), this.getSteps ());
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
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}