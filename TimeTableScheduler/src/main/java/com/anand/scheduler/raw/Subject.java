package com.anand.scheduler.raw;

public class Subject {
	private String subjectId;
	private String subjectName;

	public Subject() {
		super();
	}

	public Subject(String subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName="
				+ subjectName + "]";
	}

}
