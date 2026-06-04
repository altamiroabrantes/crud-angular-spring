package com.loiane.crud_spring.enums;

public enum StatusEnum {
	
	ACTIVE("Ativo"), INACTIVE("Inativo");

	public String value;

	private StatusEnum(String value) {
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
