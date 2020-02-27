package com.anand.scheduler.raw;

import java.util.List;

public class RawJsonData {

	private int noOfHoursPerDay;
	private int noOfDaysPerWeek;
	private List<Classes> classes;
	private List<Teacher> teachers;

	public RawJsonData() {
		super();
	}

	public RawJsonData(int noOfHoursPerDay, int noOfDaysPerWeek, List<Classes> classes, List<Teacher> teachers) {
		super();
		this.noOfHoursPerDay = noOfHoursPerDay;
		this.noOfDaysPerWeek = noOfDaysPerWeek;
		this.classes = classes;
		this.teachers = teachers;
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
		return "RawJsonData [noOfHoursPerDay=" + noOfHoursPerDay + ", noOfDaysPerWeek=" + noOfDaysPerWeek + ", classes="
				+ classes + ", teachers=" + teachers + "]";
	}

}
