package com.anand.scheduler.reference.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.anand.scheduler.exceptions.TeacherNotFoundException;
import com.anand.scheduler.raw.AssignedSubject;
import com.anand.scheduler.raw.Classes;
import com.anand.scheduler.raw.RawJsonData;
import com.anand.scheduler.raw.Subject;
import com.anand.scheduler.raw.Teacher;

public class ReferenceTable {

	private static TimeSlot[] timeSlots = null;
	private static int noOfDaysPerWeek = 0;
	private static int noOfHoursPerDay = 0;
	private static int noOfClasses = 0;
	private static Map<String, ReferenceClass> referenceClassDatas= null;
	
	public ReferenceTable initialize(RawJsonData rawJsonData) throws TeacherNotFoundException {
		assignConstants(rawJsonData);
		assignTeachers(rawJsonData);
		prepareReferenceTable(rawJsonData);
		buildReferenceClassessMap(rawJsonData);
		return this;
	}

 	private void buildReferenceClassessMap(RawJsonData rawJsonData) {
 		referenceClassDatas = new HashMap<String, ReferenceClass>();
 		
 		for(Classes classData : rawJsonData.getClasses()) {
 			Map<String, ReferenceSubject> referenceSubjectDataMap = new HashMap<>();
 			ReferenceClass referenceClass = new ReferenceClass();
 			
 			referenceClass.setName(classData.getName());
 			
 			for(AssignedSubject subjectAssigned : classData.getSubjects()) {
 				ReferenceSubject referenceSubject = new ReferenceSubject();
 				
 				referenceSubject.setNoOfHours(subjectAssigned.getNoOfHours());
 				referenceSubject.setSubjectId(subjectAssigned.getSubjectId());

 				referenceSubjectDataMap.put(referenceSubject.getSubjectId(), referenceSubject);
 			}
 			referenceClass.setReferenceSubjectDataMap(referenceSubjectDataMap);
 			referenceClassDatas.put(referenceClass.getName(), referenceClass);
 		}
		
	}

	private void assignConstants(RawJsonData rawJsonData) {
		ReferenceTable.noOfDaysPerWeek = rawJsonData.getNoOfDaysPerWeek();
		ReferenceTable.noOfHoursPerDay = rawJsonData.getNoOfHoursPerDay();
		ReferenceTable.noOfClasses = rawJsonData.getClasses().size();
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
				
				if(classSubjects.size() <= subjectNo){
					timeSlots[slotCount++] = new TimeSlot(timeSlotId++);
					// free period
				}else {
					if(hrsPerSubject >= Integer.parseInt(classSubjects.get(subjectNo).getNoOfHours())){
						subjectNo++;
						hrsPerSubject = 0;
					}
					AssignedSubject subject = classSubjects.get(subjectNo);
					timeSlots[slotCount++] = new TimeSlot(timeSlotId++, individualClass.getName(), subject.getSubjectId(), subject.getTeacherId());
					hrsPerSubject++;
				}
			}//end of hours * days loop
		}// end of classes loop
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

	public static void displayReferenceData() {
		for (int i = 0; i < timeSlots.length; i++)
			System.out.println(timeSlots[i]);
	}

	public static TimeSlot getTimeSlot(int index) {
		return timeSlots[index];
	}
	
	public static int getNoOfDaysPerWeek() {
		return noOfDaysPerWeek;
	}

	public static int getNoOfHoursPerDay() {
		return noOfHoursPerDay;
	}

	public static int getNoOfClasses() {
		return noOfClasses;
	}

	public static int validateClassSubjectCount(String className, Map<String, Integer> subjectsAllotedCount) {
		int subjectCountDifference = 0;
		
		ReferenceClass referenceClass = referenceClassDatas.get(className);
		
		for (Entry<String, Integer> subjectData : subjectsAllotedCount.entrySet()) {
			
			ReferenceSubject subjectInQuestion = referenceClass.getReferenceSubjectDataMap().get(subjectData.getKey());
			
			subjectCountDifference += 
					Math.abs(Integer.parseInt(subjectInQuestion.getNoOfHours()) - subjectData.getValue());
		}
		return subjectCountDifference;
	}

}
