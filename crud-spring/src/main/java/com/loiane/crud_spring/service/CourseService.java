package com.loiane.crud_spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.loiane.crud_spring.dto.CourseDTO;
import com.loiane.crud_spring.dto.mapper.CourseMapper;
import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.exception.RecordNotFoundException;
import com.loiane.crud_spring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@Service
@AllArgsConstructor
public class CourseService {

	private final CourseRepository courseRepository;
	private final CourseMapper mapper ;

	public List<CourseDTO> list() {
		return courseRepository.findAll()
				.stream()
				//.map(course -> mapper.toDTO(course))
				.map(mapper::toDTO)
				.collect(Collectors.toList());
	}

	public CourseDTO findById(@NotNull @Positive Long id) {
		return courseRepository.findById(id)
				.map(mapper::toDTO)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CourseDTO create(@Valid @NotNull CourseDTO course) {
		return mapper.toDTO(courseRepository.save(mapper.toEntity(course)));
	}

	public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
		return courseRepository.findById(id).map(recordFound -> {
			recordFound.setName(course.name());
			recordFound.setCategory(CategoryEnum.valueOf(course.category()));
			return mapper.toDTO(courseRepository.save(recordFound));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@NotNull @Positive Long id) {
		courseRepository.delete(
				courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}


}
