package it.alessiogaeta.surveymaster.model;

public class SearchCriterion {
	private final String key;
	private final String operation;
	private final Object value;

	public SearchCriterion(String key, String operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getOperation() {
		return operation;
	}

	public Object getValue() {
		return value;
	}
}
