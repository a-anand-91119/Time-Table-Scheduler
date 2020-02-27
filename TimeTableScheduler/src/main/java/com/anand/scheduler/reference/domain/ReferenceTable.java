package com.anand.scheduler.reference.domain;

import com.anand.scheduler.raw.Classes;
import com.anand.scheduler.raw.RawJsonData;

public class ReferenceTable {

	private TimeSlot[] timeSlots = null;
	
	public ReferenceTable() {
	}

	public ReferenceTable initialize(RawJsonData rawJsonData) {
		int subjectNo = 0;
		int slotCount = 0;
		timeSlots = new TimeSlot[rawJsonData.getNoOfDaysPerWeek() * rawJsonData.getNoOfHoursPerDay()
				* rawJsonData.getClasses().size()];
		
		for (Classes individualClass : rawJsonData.getClasses()) {
			subjectNo = 0;
			for (int i = 0; i < rawJsonData.getNoOfDaysPerWeek() * rawJsonData.getNoOfHoursPerDay(); i++) {
				
			}
		}
		
		return this;
	}
	
}
