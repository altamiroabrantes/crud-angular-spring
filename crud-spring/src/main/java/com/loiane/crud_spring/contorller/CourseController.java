package com.loiane.crud_spring.contorller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.crud_spring.model.Course;
import com.loiane.crud_spring.repository.CourseRepository;
import com.loiane.crud_spring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

	private final CourseService courseService;

	@GetMapping
	public @ResponseBody List<Course> list() {
		return courseService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findById(@NotNull @Positive @PathVariable("id") Long id) {
		return courseService.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Course create(@Valid @RequestBody Course course) {
		return courseService.create(course);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> update(@NotNull @Positive @PathVariable("id") Long id, @Valid @RequestBody Course course) {
		return courseService.update(id, course)
				.map(recordFound -> ResponseEntity.ok().body(recordFound))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@NotNull @Positive @PathVariable("id") Long id) {
		if(courseService.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
