package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;


@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
	public  void addCatrgory(Category category) {
		categoryRepository.save(category);
	}
	public void removeCategoryById(int id) {
		categoryRepository.deleteById(id);
	}
	
	/* create method to update using find by id */
	
	public Optional<Category> getCategoryById(int id) {
		return categoryRepository.findById(id);
	}

}
