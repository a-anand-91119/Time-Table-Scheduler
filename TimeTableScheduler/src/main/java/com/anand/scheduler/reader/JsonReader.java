package com.anand.scheduler.reader;

import java.io.File;
import java.io.IOException;

import com.anand.scheduler.driver.DriverClass;
import com.anand.scheduler.exceptions.TeacherNotFoundException;
import com.anand.scheduler.raw.RawJsonData;
import com.anand.scheduler.reference.domain.ReferenceTable;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	private JsonReader(){
		// private constructor to hide the implicit public one
	}
	
	public static ReferenceTable readJsonData() throws TeacherNotFoundException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File(DriverClass.fileName);
		
		RawJsonData rawJsonData = objectMapper.readValue(file, RawJsonData.class);
		return new ReferenceTable().initialize(rawJsonData);
		
	}
}
