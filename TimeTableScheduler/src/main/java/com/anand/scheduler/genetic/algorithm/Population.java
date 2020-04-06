package com.anand.scheduler.genetic.algorithm;

import java.util.Arrays;

public class Population {

	public static final int INITIAL_POPULATION_SIZE = 200;
	private Chromosome[] chromosomes = null;
	private int noOfClasses = 0;
	private int noOfDays = 0;
	private int noOfHours = 0;
	private static double initialPopulationFitness = 0.0;
	
	public Population(int noOfClasses, int noOfDays, int noOfHours){
		this.noOfClasses = noOfClasses;
		this.noOfDays = noOfDays;
		this.noOfHours = noOfHours;
	}
	
	public Population() {
		chromosomes = new Chromosome[INITIAL_POPULATION_SIZE];
	}

	public Population(int tournamentPopulationSize) {
		chromosomes = new Chromosome[tournamentPopulationSize];
	}

	public Population initialize(){
		chromosomes = new Chromosome[INITIAL_POPULATION_SIZE];
		for(int i = 0; i< INITIAL_POPULATION_SIZE;i++){
			chromosomes[i] = new Chromosome(noOfClasses, noOfDays, noOfHours).initialize();
			initialPopulationFitness += chromosomes[i].getFitness();
		}
		sortPopulationByFitness();
		return this;
	}
	
	public void setChromosome(int index, Chromosome chromosome){
		chromosomes[index] = chromosome;
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

	public Chromosome getChromosome(int index) {
		return chromosomes[index];
	}
	
	public double getFiness(){
		return chromosomes[0].getFitness();
	}

	public void displayPopulationData() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Fitness Of Fittest Chromosome: " + chromosomes[0].getFitness());
		System.out.println("Fittest Chromosome:");
		System.out.println(chromosomes[0]);
		System.out.println("****************************************************************");
		for(int i =0;i<chromosomes.length / 30;i++) {
			System.out.println("Fitness: " + chromosomes[i].getFitness());
			System.out.println(chromosomes[i]);
		}
		System.out.println("****************************************************************");
		System.out.println("-------------------------------------------------------------------------");
	}

	public void displayPopulationDataAsTimeTable() {
		chromosomes[0].displayDataAsTimeTable();
	}

	public static double getInitialPopulationFitness() {
		return initialPopulationFitness = initialPopulationFitness / 10 ;
	}

}
