package com.loiane.crud_spring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.enums.StatusEnum;
import com.loiane.crud_spring.enums.converters.CategoryConverter;
import com.loiane.crud_spring.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
	
	@NotBlank
	@Column(nullable = false)
	@Convert(converter = CategoryConverter.class)
	private CategoryEnum category;
	
	@NotBlank
	@Column(nullable = false)
	@Convert(converter = StatusConverter.class)
	private StatusEnum status = StatusEnum.ACTIVE;
}
