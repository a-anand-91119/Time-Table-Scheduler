package com.anand.scheduler.raw;

import java.util.List;

public class Classes {

	private String name;
	private List<AssignedSubject> subjects;

	public Classes() {
		super();
	}

	public Classes(String name, List<AssignedSubject> subjects) {
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

	public List<AssignedSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<AssignedSubject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Classes [name=" + name + ", subjects=" + subjects + "]";
	}

}
