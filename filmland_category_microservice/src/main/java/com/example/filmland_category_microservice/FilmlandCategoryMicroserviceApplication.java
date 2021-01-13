package com.example.filmland_category_microservice;

import com.example.filmland_category_microservice.repository.CategoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = CategoryRepository.class)
public class FilmlandCategoryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlandCategoryMicroserviceApplication.class, args);
	}

}
