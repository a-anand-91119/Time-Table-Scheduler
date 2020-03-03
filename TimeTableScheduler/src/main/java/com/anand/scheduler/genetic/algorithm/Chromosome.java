package com.anand.scheduler.genetic.algorithm;

public class Chromosome {

	private Gene gene = null;
	private boolean isFitnessModified = false;
	private double fitness = 0.0;
	
	public Chromosome(int noOfClasses, int noOfDays, int noOfHours) {
		gene = new Gene(noOfClasses, noOfDays, noOfHours).initialize();
	}
	
	public double getFitness(){
		if(isFitnessModified){
			fitness = calculateFitness();
			isFitnessModified = false;
		}
		return fitness;
	}

	private double calculateFitness() {
		return 0.0;
	}
	
	public Gene getGene(){
		isFitnessModified = true;
		return gene;
	}
}
