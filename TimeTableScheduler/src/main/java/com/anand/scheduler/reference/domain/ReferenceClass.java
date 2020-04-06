package com.anand.scheduler.reference.domain;

import java.util.Map;

public class ReferenceClass {

	private String name;
	Map<String, ReferenceSubject> referenceSubjectDataMap;

	public ReferenceClass() {
		super();
	}

	public ReferenceClass(String name, Map<String, ReferenceSubject> referenceSubjectDataMap) {
		super();
		this.name = name;
		this.referenceSubjectDataMap = referenceSubjectDataMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, ReferenceSubject> getReferenceSubjectDataMap() {
		return referenceSubjectDataMap;
	}

	public void setReferenceSubjectDataMap(Map<String, ReferenceSubject> referenceSubjectDataMap) {
		this.referenceSubjectDataMap = referenceSubjectDataMap;
	}

	@Override
	public String toString() {
		return "ReferenceClass [name=" + name + ", referenceSubjectDataMap=" + referenceSubjectDataMap + "]";
	}

}
