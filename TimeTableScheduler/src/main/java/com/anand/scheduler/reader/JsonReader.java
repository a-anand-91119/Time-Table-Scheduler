package com.anand.scheduler.reader;

import java.io.File;
import java.io.IOException;

import com.anand.scheduler.raw.RawJsonData;
import com.anand.scheduler.reference.domain.ReferenceTable;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	public static void main(String[] args) {
		readJsonData();
	}

	public static void readJsonData() {
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("input.json");
		try {
			RawJsonData rawJsonData = objectMapper.readValue(file, RawJsonData.class);
			ReferenceTable timeTable = prepareReferenceTable(rawJsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ReferenceTable prepareReferenceTable(RawJsonData rawJsonData) {
		ReferenceTable referenceTable = new ReferenceTable().initialize(rawJsonData);
		
		return referenceTable;
	}
}
