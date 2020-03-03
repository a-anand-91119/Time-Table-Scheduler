package com.anand.scheduler.raw;

import java.util.List;

public class RawJsonData {

	private int noOfHoursPerDay;
	private int noOfDaysPerWeek;
	private List<Classes> classes;
	private List<Teacher> teachers;
	private List<Subject> subjects;

	public RawJsonData() {
		super();
	}

	public RawJsonData(int noOfHoursPerDay, int noOfDaysPerWeek,
			List<Classes> classes, List<Teacher> teachers,
			List<Subject> subjects) {
		super();
		this.noOfHoursPerDay = noOfHoursPerDay;
		this.noOfDaysPerWeek = noOfDaysPerWeek;
		this.classes = classes;
		this.teachers = teachers;
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public int getNoOfHoursPerDay() {
		return noOfHoursPerDay;
	}

	public void setNoOfHoursPerDay(int noOfHoursPerDay) {
		this.noOfHoursPerDay = noOfHoursPerDay;
	}

	public int getNoOfDaysPerWeek() {
		return noOfDaysPerWeek;
	}

	public void setNoOfDaysPerWeek(int noOfDaysPerWeek) {
		this.noOfDaysPerWeek = noOfDaysPerWeek;
	}

	@Override
	public String toString() {
		return "RawJsonData [noOfHoursPerDay=" + noOfHoursPerDay
				+ ", noOfDaysPerWeek=" + noOfDaysPerWeek + ", classes="
				+ classes + ", teachers=" + teachers + ", subjects=" + subjects
				+ "]";
	}

}
