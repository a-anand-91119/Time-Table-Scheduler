package com.anand.scheduler.reference.domain;

public class TimeSlot {

	private int timeSlotId;
	private String classId;
	private String subjectId;

	public TimeSlot() {
		super();
	}

	public TimeSlot(int timeSlotId, String classId, String subjectId) {
		super();
		this.timeSlotId = timeSlotId;
		this.classId = classId;
		this.subjectId = subjectId;
	}

	public TimeSlot(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "TimeSlot [timeSlotId=" + timeSlotId + ", classId=" + classId
				+ ", subjectId=" + subjectId + "]";
	}

}
