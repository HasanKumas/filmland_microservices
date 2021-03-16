package com.example.filmland_category_microservice.controller;

import com.example.filmland_category_microservice.dto.CategoryRequest;
import com.example.filmland_category_microservice.model.Category;
import com.example.filmland_category_microservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmland")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest category){
        categoryService.addCategory(category);
        return new ResponseEntity<>("category added", HttpStatus.OK);
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity<String> deleteCategory(@RequestParam String categoryName){
        categoryService.deleteCategory(categoryName);
        return new ResponseEntity<>(categoryName + " deleted", HttpStatus.OK);
    }

    @GetMapping("/get-category")
    public ResponseEntity<Category> getCategory(@RequestParam String categoryName){
        return  new ResponseEntity<>(categoryService.getCategory(categoryName), HttpStatus.OK);
    }
    @GetMapping("/get-categoryID")
    public ResponseEntity<Long> getCategoryID(@RequestParam String categoryName){
        return  new ResponseEntity<>(categoryService.getCategoryID(categoryName), HttpStatus.OK);
    }
    @GetMapping("/get-all-categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return  new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
