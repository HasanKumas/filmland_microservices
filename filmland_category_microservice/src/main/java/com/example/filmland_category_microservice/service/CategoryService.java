package com.example.filmland_category_microservice.service;

import com.example.filmland_category_microservice.dto.CategoryRequest;
import com.example.filmland_category_microservice.model.Category;
import com.example.filmland_category_microservice.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * adds a new category to database
     * @param category category
     */
    public Long addCategory(CategoryRequest category) {

        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setAvailableContent(category.getAvailableContent());
        newCategory.setPrice(category.getPrice());
        return categoryRepository.save(newCategory).getId();
    }
    /**
     * deletes a category from database
     * @param categoryName categoryName
     */
    public void deleteCategory(String categoryName) {

        Category existingCategory = categoryRepository.findOneByNameIgnoreCase(categoryName);
        categoryRepository.delete(existingCategory);
    }
    /**
     * finds a category from database
     * @param categoryName categoryName
     */
    public Category getCategory(String categoryName) {

        Category existingCategory = categoryRepository.findOneByNameIgnoreCase(categoryName);
        return existingCategory;
    }
    /**
     * finds a categoryID from database
     * @param categoryName categoryName
     */
    public Long getCategoryID(String categoryName) {

        Category existingCategory = categoryRepository.findOneByNameIgnoreCase(categoryName);
        return existingCategory.getId();
    }
    /**
     * finds all categories from database
     */
    public List<Category> getAllCategories() {
        List<Category> categories;

        categories = categoryRepository.findAll();
        return categories;
    }
}
