package com.example.filmland_user_micro_service;

import com.example.filmland_user_micro_service.repository.UserAccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserAccountRepository.class)
public class FilmlandUserMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlandUserMicroServiceApplication.class, args);
	}

	@FeignClient("subscription-service")
	public interface SubscriptionClient {
		@GetMapping("filmland/all-subscriptions")
		String getAllSubscriptions();

		@GetMapping("filmland/all-user-subscriptions")
		List<String> getAllUserSubscriptions(@RequestParam String email);
	}
}
