package com.example.filmland_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FilmlandMicroservicesApplication {

	public static void main(String[] args) {
		//System.setProperty("spring.config.name", "eureka-server");
		SpringApplication.run(FilmlandMicroservicesApplication.class, args);
	}

}
