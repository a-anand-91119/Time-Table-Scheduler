package com.anand.scheduler.raw;

public class AssignedSubject {

	private String subjectId;
	private String noOfHours;

	public AssignedSubject(String subjectId, String noOfHours) {
		super();
		this.subjectId = subjectId;
		this.noOfHours = noOfHours;
	}

	public AssignedSubject() {
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
		return "AssignedSubject [subjectId=" + subjectId + ", noOfHours="
				+ noOfHours + "]";
	}

}
