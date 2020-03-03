package com.anand.scheduler.genetic.algorithm;

import java.util.Arrays;

public class Population {

	public static final int INITIAL_POPULATION_SIZE = 100;
	private Chromosome[] chromosomes = null;
	private int noOfClasses = 0;
	private int noOfDays = 0;
	private int noOfHours = 0;
	
	public Population(int noOfClasses, int noOfDays, int noOfHours){
		this.noOfClasses = noOfClasses;
		this.noOfDays = noOfDays;
		this.noOfHours = noOfHours;
	}
	
	public Population initialize(){
		chromosomes = new Chromosome[INITIAL_POPULATION_SIZE];
		for(int i = 0; i< INITIAL_POPULATION_SIZE;i++){
			chromosomes[i] = new Chromosome(noOfClasses, noOfDays, noOfHours);
		}
		return this;
	}
	
	public void sortPopulationByFitness(){
		Arrays.sort(chromosomes, (c1, c2) -> {
			if (c1.getFitness() > c2.getFitness())
				return -1;
			else if (c1.getFitness() < c2.getFitness())
				return 1;
			else
				return 0;
		});
	}
}
