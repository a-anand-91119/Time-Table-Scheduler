package com.anand.scheduler.genetic.algorithm;

import java.util.Random;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

	private static final double MUTATION_RATE = 0.6;
	private static final double CROSSOVER_RATE = 0.6;
	private static final int NO_OF_ELITE_CHRMOSOMES = 50;
	private Random random = new Random();
	
	public GeneticAlgorithm() {
		// default 
	}
	
	public Population evolve(Population population){
		Population evolvedPopulation = new Population();
		
		IntStream.range(0, NO_OF_ELITE_CHRMOSOMES)
			.forEach(index -> evolvedPopulation.setChromosome(index, population.getChromosome(index)));
		
		IntStream.range(NO_OF_ELITE_CHRMOSOMES, Population.INITIAL_POPULATION_SIZE)
			.forEach(index -> {
				Chromosome fatherChromosome = rouletteSelection(population);
				Chromosome motherChromosome = rouletteSelection(population);
				
				Chromosome offspringChromosome = createOffspring(fatherChromosome, motherChromosome);
				
				evolvedPopulation.setChromosome(index, offspringChromosome);
			});
		evolvedPopulation.sortPopulationByFitness();
		return evolvedPopulation;
	}
	
	private Chromosome rouletteSelection(Population population) {
		double expectedFitness = random.nextDouble() * Population.getInitialPopulationFitness();
		double currentFitness = 0.0;
		int index = -1;
		
		while(currentFitness <= expectedFitness) {
			currentFitness += population.getChromosome(++index).getFitness();
		}
		
		return population.getChromosome(index);
	}

	private Chromosome createOffspring(Chromosome fatherChromosome, Chromosome motherChromosome) {
		Chromosome offspringChromosome = null;
		
		if(Math.random() < CROSSOVER_RATE)
			offspringChromosome = crossOverChromosome(fatherChromosome, motherChromosome);
		else
			offspringChromosome = motherChromosome;
		
		offspringChromosome = mutateChromosome(offspringChromosome);
		
		return offspringChromosome;
	}
	
	private Chromosome crossOverChromosome(Chromosome chromosome1, Chromosome chromosome2){
		Chromosome crossOverChromosome = new Chromosome(chromosome1.getGene().length);
		double fitness = chromosome1.getFitness() > chromosome2.getFitness() ? chromosome1.getFitness()
				: chromosome2.getFitness();
		
		for(int i =0; i < chromosome1.getGene().length; i++){
			if(Math.random() < CROSSOVER_RATE)
				crossOverChromosome.getGene()[i] = chromosome1.getGene()[i];
			else
				crossOverChromosome.getGene()[i] = chromosome2.getGene()[i];
		}
		if(crossOverChromosome.getFitness() > fitness)
			return crossOverChromosome;
		else
			return chromosome1.getFitness() > chromosome2.getFitness() ? chromosome1 : chromosome2;
	}
	
	private Chromosome mutateChromosome(Chromosome chromosome){
		Chromosome mutatedChromosome = new Chromosome(chromosome.getGene().length);
		
		for(int i =0; i < chromosome.getGene().length; i++){
			if(Math.random() < MUTATION_RATE)
				mutatedChromosome.getGene()[i] = new Gene(chromosome.getGene()[0].getGeneSize()).initialize(i);
			else
				mutatedChromosome.getGene()[i] = chromosome.getGene()[i];
		}
		
		return mutatedChromosome.getFitness() > chromosome.getFitness() ? mutatedChromosome : chromosome;
	}
	
}
