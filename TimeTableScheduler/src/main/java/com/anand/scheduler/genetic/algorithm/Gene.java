package com.anand.scheduler.genetic.algorithm;

import java.util.concurrent.ThreadLocalRandom;

public class Gene {

	private int[] genes;
	
	Gene(int noOfClasses, int noOfDays, int noOfHours){
		genes = new int[noOfClasses * noOfDays * noOfHours];
	}
	
	public Gene initialize(){
		for(int i =0;i<genes.length;i++){
			genes[i] = ThreadLocalRandom.current().nextInt(1, genes.length + 1);
		}
		return this;
	}
}
