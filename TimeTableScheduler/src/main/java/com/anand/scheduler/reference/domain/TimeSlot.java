package com.anand.scheduler.reference.domain;

public class TimeSlot {

	private int timeSlotId;
	private String classId;
	private String subjectId;
	private String teacherId;

	public TimeSlot() {
		super();
	}

	public TimeSlot(int timeSlotId, String classId, String subjectId, String teacherId) {
		super();
		this.timeSlotId = timeSlotId;
		this.classId = classId;
		this.subjectId = subjectId;
		this.teacherId = teacherId;
	}

	public TimeSlot(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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
		return "TimeSlot [timeSlotId=" + timeSlotId + ", classId=" + classId + ", subjectId=" + subjectId
				+ ", teacherId=" + teacherId + "]";
	}

}
