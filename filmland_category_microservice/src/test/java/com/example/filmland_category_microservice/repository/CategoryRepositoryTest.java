package com.example.filmland_category_microservice.repository;

import com.example.filmland_category_microservice.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    CategoryRepository categoryRepository;


    @Test
    public void it_should_save_category() {
        Category category = new Category();
        category.setName("Dutch Films");
        category.setPrice(10.0);
        category.setAvailableContent(5);
        entityManager.persist(category);
        entityManager.flush();

        assertEquals(categoryRepository.findOneByNameIgnoreCase("Dutch Films").getName(), category.getName());
    }

}