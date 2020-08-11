package com.cepengagementservice.category;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.cepengagementservice.Controllers.CategoryController;
import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CategoryTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Mock
	CategoryController catController = mock(CategoryController.class);
	
	@Mock
	CategoryService catServ = mock(CategoryService.class);

	Category category;

	@Test
	public void testGetById() {
		Category category = new Category();

		category.setCategoryId(1);

		assertEquals(1, category.getCategoryId());

	}

	@Test
	public void testCatIds() {
		Category category = new Category();
		Category category1 = new Category();
		Category category2 = new Category();

		category.setCategoryId(1);
		category.setSkillCategory("Java");
		category.setActive(false);

		category1.setCategoryId(2);
		category1.setSkillCategory("C++");
		category1.setActive(true);

		category2.setCategoryId(3);
		category2.setSkillCategory("Python");
		category2.setActive(false);

		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category);
		categoryList.add(category1);
		categoryList.add(category2);

		int[] catId = { category.getCategoryId(), category1.getCategoryId(), category2.getCategoryId() };

		when(catController.getCatIds(catId)).thenReturn(categoryList);

		assertSame(categoryList, catController.getCatIds(catId));

	}

	@Test
	public void testGetAll() {
		Category category = new Category();
		Category category1 = new Category();
		Category category2 = new Category();

		category.setCategoryId(1);
		category.setSkillCategory("Java");
		category.setActive(false);

		category1.setCategoryId(2);
		category1.setSkillCategory("C++");
		category1.setActive(true);

		category2.setCategoryId(3);
		category2.setSkillCategory("Python");
		category2.setActive(false);

		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category);
		categoryList.add(category1);
		categoryList.add(category2);
		
        when(catController.allCategories()).thenReturn(categoryList);
        
        assertEquals(categoryList,catController.allCategories());


	}

}
