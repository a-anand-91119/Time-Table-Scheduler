package com.anand.scheduler.genetic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import com.anand.scheduler.driver.DriverClass;
import com.anand.scheduler.reference.domain.ReferenceTable;
import com.anand.scheduler.reference.domain.TimeSlot;

public class Chromosome {

	private Gene[] genes = null;
	private boolean isFitnessModified = false;
	private double fitness = 0.0;
	private static int noOfClasses;
	private static int noOfDays;
	private static int noOfHours;
	
	Map<String, HashSet<Integer>> classToDuplicateTimeSlot = new HashMap<>();

	public Chromosome(int noOfClasses, int noOfDays, int noOfHours) {
		this.noOfClasses = noOfClasses;
		this.noOfDays = noOfDays;
		this.noOfHours = noOfHours;
		genes = new Gene[noOfClasses];
	}

	public Chromosome initialize() {
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene(noOfDays, noOfHours).initialize(i);
		}
		fitness = calculateFitness();
		return this;
	}

	public Chromosome(int geneSize) {
		genes = new Gene[geneSize];
	}

	public double getFitness() {
		if (isFitnessModified) {
			fitness = calculateFitness();
			isFitnessModified = false;
		}
		return fitness;
	}

	private double calculateFitness() {
		List<String> teachersList = new ArrayList<String>();
		int noOfCollisions = 0;
		Map<String, Map<String, Integer>> classToSubjectToHours = new HashMap<String, Map<String, Integer>>();
		
		for (int i = 0; i < noOfHours * noOfDays; i++) {
			
			teachersList.clear();
			for (int j = 0; j < noOfClasses; j++) {
				
				int timeSlotId = genes[j].getGeneValue(i);
				
				TimeSlot currentTimeSlot = ReferenceTable.getTimeSlot(timeSlotId - 1);
				
				if(DriverClass.restrictDuplicates) {
					
					HashSet<Integer> timeSlotIds = classToDuplicateTimeSlot.get(currentTimeSlot.getClassId());
					
					if(timeSlotIds != null) {
						if(timeSlotIds.contains(timeSlotId)) {
							noOfCollisions++;
							continue;
						}else
							timeSlotIds.add(timeSlotId);
					}else {
						classToDuplicateTimeSlot.put(currentTimeSlot.getClassId(),  new HashSet<Integer>() {
							private static final long serialVersionUID = 1L;
							{add(timeSlotId);}
						});
					}
				}
				
				if(!currentTimeSlot.isFreePeriod()) {
					
					if(DriverClass.strictSubjectHours)
						classToSubjectToHours.computeIfPresent(currentTimeSlot.getClassId(), (key, val) -> {
							val.computeIfPresent(currentTimeSlot.getSubjectId(), (key1, val1) -> val1 + 1);
							val.computeIfAbsent(currentTimeSlot.getSubjectId(), key1 -> 1);
							return val;
						});
					
					if(DriverClass.strictSubjectHours)
						classToSubjectToHours.putIfAbsent(currentTimeSlot.getClassId(),
								new HashMap<String, Integer>() {
									private static final long serialVersionUID = 1L;
									{
										put(currentTimeSlot.getSubjectId(), 1);
									}
								});
					
					if(teachersList.contains(currentTimeSlot.getTeacherId()))
						noOfCollisions++;
					else
						teachersList.add(currentTimeSlot.getTeacherId());
				}
			}
			
		}
		
		classToDuplicateTimeSlot.clear();
		
		int noOfSubjectMismatches = 0;
		
		if(DriverClass.strictSubjectHours)
			noOfSubjectMismatches = validateSubjectsAndTheirCounts(classToSubjectToHours);
		
		return 1 - (((double)noOfCollisions + noOfSubjectMismatches) / (noOfClasses * noOfDays * noOfHours));
	}

	private int validateSubjectsAndTheirCounts(Map<String, Map<String, Integer>> classToSubjectToHours) {
		int noOfMismatches = 0;
		for(Entry<String, Map<String, Integer>> entry : classToSubjectToHours.entrySet()) {
			noOfMismatches += ReferenceTable.validateClassSubjectCount(entry.getKey(), entry.getValue());
		}
		return noOfMismatches;
	}

	public Gene[] getGene() {
		isFitnessModified = true;
		return genes;
	}

	@Override
	public String toString() {
		StringBuilder returnStringBuilder = new StringBuilder();
		for(Gene gene : genes) {
			returnStringBuilder.append(gene).append("\n");
		}
		return returnStringBuilder.toString();
	}

	public void displayDataAsTimeTable() {
		System.out.println("--------------------------------Generated TimeTable--------------------------------");
		for (int i = 0; i < noOfClasses; i++) {
			TimeSlot classSlot = ReferenceTable.getTimeSlot(genes[i].getGeneValue(0) - 1);
			System.out.println("\nClass Name: " + classSlot.getClassId());
			int day = 1;
			boolean isFirst = true;
			for(int j = 0; j < noOfHours * noOfDays; j++) {
				TimeSlot timeSlot = ReferenceTable.getTimeSlot(genes[i].getGeneValue(j) - 1);
				if((j + 1) % noOfHours == 0) {
					System.out.println("TSID: " + String.format("%03d", timeSlot.getTimeSlotId()) + " <> SUB: " + String.format("%03d", Integer.parseInt(timeSlot.getSubjectId())) + " <> TCHR: " + String.format("%03d", Integer.parseInt(timeSlot.getTeacherId())));
					day++;
					isFirst = true;
				}else {
					if(isFirst){
						isFirst = false;
						System.out.print("Day: " + day + " ==> ");
					}
					System.out.print("TSID: " + String.format("%03d", timeSlot.getTimeSlotId()) + " <> SUB: " + String.format("%03d", Integer.parseInt(timeSlot.getSubjectId())) + " <> TCHR: " + String.format("%03d", Integer.parseInt(timeSlot.getTeacherId())) + " ||| ");
				}
			}
		}
		System.out.println("\n-----------------------------------------------------------------------------------");
	}
}
