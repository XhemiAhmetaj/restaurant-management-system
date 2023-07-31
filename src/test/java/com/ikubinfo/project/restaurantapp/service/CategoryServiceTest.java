package com.ikubinfo.project.restaurantapp.service;

import com.ikubinfo.project.restaurantapp.domain.dto.CategoryDTO;
import com.ikubinfo.project.restaurantapp.domain.dto.UserDTO;
import com.ikubinfo.project.restaurantapp.domain.entity.Category;
import com.ikubinfo.project.restaurantapp.domain.entity.User;
import com.ikubinfo.project.restaurantapp.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void CategoryService_CreateCategory_ReturnCategory(){
        Category category = new Category();
        category.setName("Category Test");
        category.setCategoryParent(null);

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Category Test")
                .build();

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDTO savedCategory = categoryService.addCategory(null,categoryDTO);
        assertNotNull(savedCategory);

    }

    @Test
    public void CategoryService_GetAllCategory_ReturnCategory(){
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setName("Category 1 Test");
        category1.setCategoryParent(null);

        Category category2 = new Category();
        category2.setName("Category 2 Test");
        category2.setCategoryParent(null);

        Category category3 = new Category();
        category3.setName("Category 3 Test");
        category3.setCategoryParent(category2);

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> out =categoryService.listCategories();
        assertNotNull(out);
    }

    @Test
    public void CategoryService_GetCategoryById_ReturnCategory(){
        Category category1 = new Category();
        category1.setName("Category 1 Test");
        category1.setCategoryParent(null);

        Category category2 = new Category();
        category2.setName("Category 2 Test");
        category2.setCategoryParent(null);

        Category category3 = new Category();
        category3.setName("Category 3 Test");
        category3.setCategoryParent(category2);

        when(categoryRepository.findById(3L)).thenReturn(Optional.of(category3));

        Category cat = categoryService.findCategoryById(3L);
        assertNotNull(cat);
    }

    @Test
    public void CategoryService_DeleteCategory_ReturnCategory(){
        Category category1 = new Category();
        category1.setName("Category 1 Test");
        category1.setCategoryParent(null);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));

        categoryService.deleteCategory(1L);

        assertNotNull(category1);

    }

}
