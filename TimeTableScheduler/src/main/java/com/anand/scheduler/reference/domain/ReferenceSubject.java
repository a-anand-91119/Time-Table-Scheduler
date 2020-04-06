package com.anand.scheduler.reference.domain;

public class ReferenceSubject {
	private String subjectId;
	private String noOfHours;

	public ReferenceSubject(String subjectId, String noOfHours) {
		super();
		this.subjectId = subjectId;
		this.noOfHours = noOfHours;
	}

	public ReferenceSubject() {
		super();
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getNoOfHours() {
		return noOfHours;
	}

	public void setNoOfHours(String noOfHours) {
		this.noOfHours = noOfHours;
	}

	@Override
	public String toString() {
		return "ReferenceSubject [subjectId=" + subjectId + ", noOfHours=" + noOfHours + "]";
	}
}
