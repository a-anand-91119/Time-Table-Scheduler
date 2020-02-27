package com.anand.scheduler.raw;

import java.util.List;

public class Classes {

	private String name;
	private List<Subject> subjects;

	public Classes() {
		super();
	}

	public Classes(String name, List<Subject> subjects) {
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

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Classes [name=" + name + ", subjects=" + subjects + "]";
	}

}
