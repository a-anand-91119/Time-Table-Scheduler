package com.anand.scheduler.raw;

import java.util.List;

public class Teacher {

	String name;
	List<String> subjects;

	public Teacher() {
		super();
	}

	public Teacher(String name, List<String> subjects) {
		super();
		this.name = name;
		this.subjects = subjects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", subjects=" + subjects + "]";
	}

}
