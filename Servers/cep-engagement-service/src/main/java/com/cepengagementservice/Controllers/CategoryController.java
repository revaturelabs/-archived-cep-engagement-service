package com.cepengagementservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cepengagementservice.Models.Category;
import com.cepengagementservice.Services.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	
	private CategoryService catServ;
	
	/**
	 * No args constructor
	 */
	public CategoryController() {
		
	}
	
	/**
	 * 
	 * @param catServ, arg constructor
	 */
	@Autowired
	public CategoryController(CategoryService catServ) {
		this.catServ = catServ;
	}
	
	public CategoryService getCatServ() {
		return catServ;
	}

	public void setCatServ(CategoryService catServ) {
		this.catServ = catServ;
	}

	/**
	 * 
	 * @param id
	 * @return the category that you gave
	 */
	@GetMapping (value = "/getById")
	public Category getById(@RequestParam("categoryId") int id) {
		return  catServ.getCategoryById(id);
	}
	/**
	 * 
	 * @param ids, this is how we tested it in postman : http://localhost:9015/getCatIds?categoryIds=1,2,3
	 * @return the categories whose id you entered
	 */
	@GetMapping (value = "/getCatIds")
	public List<Category> getCatIds(@RequestParam("categoryIds") int[] ids) {
		return catServ.getCategoriesByIds(ids);
	}
	/**
	 * 
	 * @param catServ is the CategoryService
	 * @return a list of categories
	 */
	@GetMapping (value = "/allCategories")
	public List<Category> allCategories(CategoryService catServ){
		return catServ.getAllCategories();
	}
}
