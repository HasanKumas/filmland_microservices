package com.example.filmland_subscription_microservice;

//import com.example.filmland_category_microservice.model.Category;
import com.example.filmland_subscription_microservice.dto.CategoryDTO;
//import com.example.filmland_user_micro_service.model.UserAccount;
import com.example.filmland_subscription_microservice.repository.SubscriptionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SubscriptionRepository.class)
public class FilmlandSubscriptionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlandSubscriptionMicroserviceApplication.class, args);
	}
	@FeignClient("category-service")
	public interface CategoryClient {
		@GetMapping("filmland/get-category")
		CategoryDTO getCategory(@RequestParam String categoryName);

		@GetMapping("filmland/get-categoryID")
		Long getCategoryID(@RequestParam String categoryName);
	}
	@FeignClient("user-service")
	public interface UserAccountClient {
		@GetMapping("filmland/get-userID")
		Long getUserID(@RequestParam String email);
	}

}
