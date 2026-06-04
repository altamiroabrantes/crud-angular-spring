package com.loiane.crud_spring.enums.converters;

import java.util.stream.Stream;

import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.enums.StatusEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String>{

	@Override
	public String convertToDatabaseColumn(StatusEnum statusEnum) {
		if(statusEnum == null) {
			return null;
		}
		return statusEnum.getValue();
	}

	@Override
	public StatusEnum convertToEntityAttribute(String value) {
		if(value == null) {
			return null;
		}
		return Stream.of(StatusEnum.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
