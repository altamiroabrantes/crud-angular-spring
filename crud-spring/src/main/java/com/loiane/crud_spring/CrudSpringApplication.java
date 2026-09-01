package com.loiane.crud_spring;

import com.loiane.crud_spring.model.Lesson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loiane.crud_spring.enums.CategoryEnum;
import com.loiane.crud_spring.model.Course;
import com.loiane.crud_spring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(CategoryEnum.BACK_END);

			Lesson l = new Lesson();
			l.setCourse(c);
			l.setName("Introducao");
			l.setYoutubeUrl("watch?v=1");
			c.getLessons().add(l);

			courseRepository.save(c);
		};
	}

}
