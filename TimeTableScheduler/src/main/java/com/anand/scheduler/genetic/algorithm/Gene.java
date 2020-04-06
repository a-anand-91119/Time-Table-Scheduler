package com.anand.scheduler.genetic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Gene {

	private int[] genes;

	Gene(int noOfDays, int noOfHours) {
		genes = new int[noOfDays * noOfHours];
	}

	public Gene(int geneSize) {
		genes = new int[geneSize];
	}

	public Gene initialize(int geneIndex) {
		List<Integer> randomCollection = new ArrayList<>();
		IntStream.range(1, genes.length + 1).forEach(val -> randomCollection.add(val));
		Collections.shuffle(randomCollection);
		
		for (int i = 0; i < genes.length; i++) {
			genes[i] = geneIndex * genes.length + randomCollection.get(i);
		}
		return this;
	}

	public int getGeneSize() {
		return genes.length;
	}

	public int getGeneValue(int i) {
		return genes[i];
	}

	public void setGeneValue(int i, int geneValue) {
		genes[i] = geneValue;
	}

	public int getRandomValue() {
		return ThreadLocalRandom.current().nextInt(1, genes.length + 1);
	}

	@Override
	public String toString() {
		return Arrays.toString(genes);
	}

}
