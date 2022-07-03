package com.nonesofar.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nonesofar.springboot.entity.Category;
import com.nonesofar.springboot.repository.CategoryRepo;

@RestController
public class CategoryController {
	@Autowired
	CategoryRepo catRepo;

	
	
	//Create new Categories
	@PostMapping("/categories")
	public ResponseEntity<Category> save(@RequestBody Category category) {
		try {
			return new ResponseEntity<>(catRepo.save(category), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//Get all categories
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllcategories() {
		try {
			List<Category> cattList = catRepo.findAll();
			if (cattList.isEmpty() || cattList.size() == 0) {
				return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Category>>(cattList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	//Update Categories
	@PutMapping("/categories/{uID}/{categoryName}/{description}")
	public void updateItemName(@PathVariable long uID, @PathVariable String categoryName,
			@PathVariable String description) {
		Category aCategory = catRepo.findById(uID).get();
		aCategory.setCategoryName(categoryName);
		aCategory.setDescription(description);
	}

}
