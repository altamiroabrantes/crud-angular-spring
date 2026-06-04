package com.loiane.crud_spring.enums.converters;

import java.util.stream.Stream;

import com.loiane.crud_spring.enums.CategoryEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<CategoryEnum, String>{

	@Override
	public String convertToDatabaseColumn(CategoryEnum categoryEnum) {
		if(categoryEnum == null) {
			return null;
		}
		return categoryEnum.getValue();
	}

	@Override
	public CategoryEnum convertToEntityAttribute(String value) {
		if(value == null) {
			return null;
		}
		return Stream.of(CategoryEnum.values())
				.filter(c -> c.getValue().equals(value))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

}
