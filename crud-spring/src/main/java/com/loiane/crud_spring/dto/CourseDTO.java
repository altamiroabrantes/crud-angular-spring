package com.loiane.crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CourseDTO {
	
	private Long _id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String name;
	
	@NotBlank
	@Size(max = 10)
	@Pattern(regexp = "Back-end|Front-end")
	private String category;
	
	@NotBlank
	@Size(max = 10)
	@Pattern(regexp = "Ativo|Inativo")
	private String status = "Ativo";
}
