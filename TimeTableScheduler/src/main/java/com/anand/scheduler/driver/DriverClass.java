package com.anand.scheduler.driver;

import java.io.IOException;

import com.anand.scheduler.exceptions.TeacherNotFoundException;
import com.anand.scheduler.genetic.algorithm.GeneticAlgorithm;
import com.anand.scheduler.genetic.algorithm.Population;
import com.anand.scheduler.reader.JsonReader;
import com.anand.scheduler.reference.domain.ReferenceTable;

public class DriverClass {

	public static boolean strictSubjectHours = false;
	public static boolean restrictDuplicates = false;
	public static String fileName = "input3.json";
	
	public static void main(String[] args) {
		try {
			if(args.length >= 1)
				strictSubjectHours = args[0].equals("Y");
			if(args.length >= 2)
				restrictDuplicates = args[1].equals("Y");
			if(args.length >= 3)
				fileName = args[2];
			
			JsonReader.readJsonData();
			ReferenceTable.displayReferenceData();
			GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
			Population population = new Population(
					ReferenceTable.getNoOfClasses(),
					ReferenceTable.getNoOfDaysPerWeek(),
					ReferenceTable.getNoOfHoursPerDay()).initialize();
			
			int generation = 1;
			System.out.println("---------------------------- Generation : 0 ----------------------------");
			population.displayPopulationData();
			
			double oldfitness = 0;
			while(/*generation < 5000 && */population.getFiness() < 1.0){
				generation++;
				population = geneticAlgorithm.evolve(population);
				//if(oldfitness != population.getFiness()) {
					System.out.println("---------------------------- Generation : " + generation + " ----------------------------");
					population.displayPopulationData();
				//}
				oldfitness = population.getFiness();
			}
			
			population.displayPopulationDataAsTimeTable();
			
		} catch (IOException e) {
			System.err.println("IO Error: " + e.getMessage());
			e.printStackTrace();
		} catch (TeacherNotFoundException e) {
			System.err.println("Invalid Input Data. " + e.getMessage());
		}
	}
}
