package com.example.filmland_category_microservice.service;

import com.example.filmland_category_microservice.dto.CategoryRequest;
import com.example.filmland_category_microservice.model.Category;
import com.example.filmland_category_microservice.repository.CategoryRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void when_add_user_it_should_return_id(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Dutch Films");
        categoryRequest.setAvailableContent(5);
        categoryRequest.setPrice(10.0);

        Category category = new Category();
        category.setName("Dutch Films");
        category.setAvailableContent(5);
        category.setPrice(10.0);
        category.setId(1L);

        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);

        long id = categoryService.addCategory(categoryRequest);
        assertEquals(1L, id);

    }
}