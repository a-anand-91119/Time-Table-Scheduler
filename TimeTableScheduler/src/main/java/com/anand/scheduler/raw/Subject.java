package com.anand.scheduler.raw;

public class Subject {
	private String subjectName;
	private String noOfHours;

	public Subject() {
		super();
	}

	public Subject(String subjectName, String noOfHours) {
		super();
		this.subjectName = subjectName;
		this.noOfHours = noOfHours;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}

	@Override
	public String toString() {
		return "Subject [subjectName=" + subjectName + ", noOfHours=" + noOfHours + "]";
	}

}
