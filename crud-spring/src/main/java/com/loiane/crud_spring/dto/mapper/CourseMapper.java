package com.loiane.crud_spring.dto.mapper;

import org.springframework.stereotype.Component;

import com.loiane.crud_spring.dto.CourseDTO;
import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.model.Course;

@Component
public class CourseMapper {

	public CourseDTO toDTO(Course course) {
		if (course == null)
			return null;
		return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
	}

	public Course toEntity(CourseDTO courseDTO) {

		if (courseDTO == null) {
			return null;
		}

		Course course = new Course();
		if (courseDTO.id() != null) {
			course.setId(courseDTO.id());
		}
		course.setName(courseDTO.name());
		course.setCategory(CategoryEnum.valueOf(courseDTO.category()));
		return course;
	}

//	public CategoryEnum convertCategoryValue(String value) {
//		if (value == null) {
//			return null;
//		}
//		return switch (value) {
//			case "Front-end" -> CategoryEnum.FRONT_END;
//			case "Back-end" -> CategoryEnum.BACK_END;
//			default -> throw new IllegalArgumentException("Categoria inválida: " + value);
//		};
//	}
}
