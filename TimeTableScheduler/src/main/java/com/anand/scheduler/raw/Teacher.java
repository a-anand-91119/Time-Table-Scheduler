package com.anand.scheduler.raw;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Teacher {

	private String teacherId;
	private String teacherName;
	private List<String> subjectIds;
	private Integer assignedHours;

	public Teacher() {
		super();
	}

	public Teacher(String teacherId, String teacherName, List<String> subjectIds, Integer assignedHours) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.subjectIds = subjectIds;
		this.assignedHours = assignedHours;
	}

	public Integer getAssignedHours() {
		return assignedHours;
	}

	public void setAssignedHours(Integer assignedHours) {
		this.assignedHours = assignedHours;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<String> getSubjectIds() {
		return subjectIds;
	}
	
	@JsonSetter("subjects")
	public void setSubjectIds(List<String> subjectIds) {
		this.subjectIds = subjectIds;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", subjectIds=" + subjectIds
				+ ", assignedHours=" + assignedHours + "]";
	}

	public void incrementAssignment() {
		this.assignedHours++;
		
	}

}
