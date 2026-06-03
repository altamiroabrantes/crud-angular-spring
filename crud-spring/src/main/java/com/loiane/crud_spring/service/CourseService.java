package com.loiane.crud_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.loiane.crud_spring.exception.RecordNotFoundException;
import com.loiane.crud_spring.model.Course;
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

	public List<Course> list() {
		return courseRepository.findAll();
	}

	public Course findById(@NotNull @Positive Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public Course create(@Valid Course course) {
		return courseRepository.save(course);
	}

	public Course update(@NotNull @Positive Long id, @Valid Course course) {
		return courseRepository.findById(id).map(recordFound -> {
			recordFound.setName(course.getName());
			recordFound.setCategory(course.getCategory());
			return courseRepository.save(recordFound);
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public void delete(@NotNull @Positive @PathVariable("id") Long id) {
		courseRepository.delete(
				courseRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
	}


}
