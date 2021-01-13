package com.example.filmland_category_microservice.repository;

import com.example.filmland_category_microservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findOneByNameIgnoreCase(String name);
}
