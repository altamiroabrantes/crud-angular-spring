package com.loiane.crud_spring.enums;

public enum CategoryEnum {

	BACK_END("Back-end"), FRONT_END("Front-end");

	public String value;

	private CategoryEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
