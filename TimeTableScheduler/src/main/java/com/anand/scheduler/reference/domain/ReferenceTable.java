package com.anand.scheduler.reference.domain;

import java.util.List;

import com.anand.scheduler.raw.AssignedSubject;
import com.anand.scheduler.raw.Classes;
import com.anand.scheduler.raw.RawJsonData;

public class ReferenceTable {

	private TimeSlot[] timeSlots = null;
	
	public ReferenceTable() {
	}

	public ReferenceTable initialize(RawJsonData rawJsonData) {
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
					timeSlots[slotCount++] = new TimeSlot(timeSlotId++, individualClass.getName(), subject.getSubjectId());
					hrsPerSubject++;
				}
			}
		}
		
		return this;
	}

	public void displayReferenceData() {
		for(int i =0;i<timeSlots.length;i++)
			System.out.println(timeSlots[i]);
	}
	
}
