package com.example.filmland_category_microservice.controller;

import com.example.filmland_category_microservice.dto.CategoryRequest;
import com.example.filmland_category_microservice.model.Category;
import com.example.filmland_category_microservice.service.CategoryService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
    @MockBean
    CategoryService categoryService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void it_should_return_string_category_added() throws Exception{
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("Dutch Films");
        categoryRequest.setAvailableContent(5);

        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setAvailableContent(categoryRequest.getAvailableContent());
        category.setPrice(categoryRequest.getPrice());
        category.setId(1L);

        when(categoryService.addCategory(Mockito.any(CategoryRequest.class))).thenReturn(1L);

        this.mockMvc.perform(post("/filmland/add-category")
                .content(objectMapper.writeValueAsString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", is("category added")))
                .andExpect(status().isOk());
    }


}