package com.anand.scheduler.reference.domain;

import java.util.List;

import com.anand.scheduler.exceptions.TeacherNotFoundException;
import com.anand.scheduler.raw.AssignedSubject;
import com.anand.scheduler.raw.Classes;
import com.anand.scheduler.raw.RawJsonData;
import com.anand.scheduler.raw.Teacher;

public class ReferenceTable {

	private TimeSlot[] timeSlots = null;
	
	public ReferenceTable() {
	}

	public ReferenceTable initialize(RawJsonData rawJsonData) throws TeacherNotFoundException {
		
		assignTeachers(rawJsonData);
		prepareReferenceTable(rawJsonData);
		return this;
	}

 	private void prepareReferenceTable(RawJsonData rawJsonData) {
		int subjectNo = 0;
		int slotCount = 0;
		int hrsPerSubject = 0;
		timeSlots = new TimeSlot[rawJsonData.getNoOfDaysPerWeek() * rawJsonData.getNoOfHoursPerDay()
				* rawJsonData.getClasses().size()];
		int timeSlotId = 1;
		for (Classes individualClass : rawJsonData.getClasses()) {
			subjectNo = 0;
			hrsPerSubject = 0;
			List<AssignedSubject> classSubjects = individualClass.getSubjects();
			for (int i = 0; i < rawJsonData.getNoOfDaysPerWeek() * rawJsonData.getNoOfHoursPerDay(); i++) {
				
				if(hrsPerSubject >= Integer.parseInt(classSubjects.get(subjectNo).getNoOfHours())){
					subjectNo++;
					hrsPerSubject = 0;
				}
				if(classSubjects.size() <= subjectNo){
					timeSlots[slotCount++] = new TimeSlot(timeSlotId++);
					// free period
				}else{
					AssignedSubject subject = classSubjects.get(subjectNo);
					timeSlots[slotCount++] = new TimeSlot(timeSlotId++, individualClass.getName(), subject.getSubjectId(), subject.getTeacherId());
					hrsPerSubject++;
				}
			}
		}
		
		
	}

	private void assignTeachers(RawJsonData rawJsonData) throws TeacherNotFoundException {
		for(Classes individualClass : rawJsonData.getClasses()) {
			for(AssignedSubject assignedSubject : individualClass.getSubjects()) {
				int previousTeacherAssignedHours = -1;
				int teacherIndex = -1;
				for(int i =0; i < rawJsonData.getTeachers().size(); i++) {
					Teacher teacher = rawJsonData.getTeachers().get(i);
					if(teacher.getSubjectIds().contains(assignedSubject.getSubjectId())) {
						// Found teacher who can teach the subject
						Integer assignedHours = teacher.getAssignedHours();
						if(assignedHours == null) {
							assignedHours = 0;
							teacher.setAssignedHours(assignedHours);
						}
						
						if(previousTeacherAssignedHours == -1) {
							// no teacher has been assigned to this subject
							teacherIndex = i;
							previousTeacherAssignedHours = assignedHours;
						}else if(previousTeacherAssignedHours > assignedHours) {
							// the current teacher has less hours assigned
							teacherIndex = i;
							previousTeacherAssignedHours = assignedHours;
						}
					}
				}// Teachers Loop Ends
				if(teacherIndex == -1)
					throw new TeacherNotFoundException("No Teacher Found For Subject Id: " + assignedSubject.getSubjectId());
				rawJsonData.getTeachers().get(teacherIndex).incrementAssignment();
				assignedSubject.setTeacherId(rawJsonData.getTeachers().get(teacherIndex).getTeacherId());
			}// Subjects Loop Ends
		} // Classes Loop Ends
	}

	public void displayReferenceData() {
		for(int i =0;i<timeSlots.length;i++)
			System.out.println(timeSlots[i]);
	}
	
}
