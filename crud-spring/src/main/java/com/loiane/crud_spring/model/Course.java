package com.loiane.crud_spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.enums.StatusEnum;
import com.loiane.crud_spring.enums.converters.CategoryConverter;
import com.loiane.crud_spring.enums.converters.StatusConverter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@SQLRestriction("status = 'Inativo'") // Substituto do @Where
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("_id")
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	@Column( length = 100, nullable = false)
	private String name;

	@Column(nullable = false)
	@Convert(converter = CategoryConverter.class)
	private CategoryEnum category;

	@Column(nullable = false)
	@Convert(converter = StatusConverter.class)
	private StatusEnum status = StatusEnum.ACTIVE;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
	private List<Lesson> lessons = new ArrayList<>();
}
