package com.anand.scheduler.reader;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.anand.scheduler.raw.RawJsonData;
import com.anand.scheduler.reference.domain.ReferenceTable;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	public static void main(String[] args) {
		readJsonData();
	}

	
	public static void readJsonData() {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("input.json");
		try {
			InjectableValues subjectInjectableId = new InjectableValues.Std();
			Random random = new Random();
			  ((InjectableValues.Std) subjectInjectableId).addValue("subjectId", random.nextInt());
			RawJsonData rawJsonData = objectMapper.setInjectableValues(subjectInjectableId).readValue(file, RawJsonData.class);
			System.out.println(rawJsonData);
			ReferenceTable timeTable = prepareReferenceTable(rawJsonData);
			timeTable.displayReferenceData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ReferenceTable prepareReferenceTable(RawJsonData rawJsonData) {
		ReferenceTable referenceTable = new ReferenceTable().initialize(rawJsonData);
		
		return referenceTable;
	}
}
