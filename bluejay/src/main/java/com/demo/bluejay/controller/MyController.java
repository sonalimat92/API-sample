package com.demo.bluejay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bluejay.entities.Courses;
import com.demo.bluejay.services.ProductService;

@RestController
public class MyController {

	@Autowired
	private ProductService productServ;
	
	
	@GetMapping("/home")
	public String Home() {
		return "Test First API Creation";
	}
	
	//get the courses
	@GetMapping("/products")
	public List<Courses> getProducts(){
		return productServ.getCourses();
	}
	
	@GetMapping("/products/{productId}")
	public Courses getProduct(@PathVariable String productId){
		return productServ.getCourse(Long.parseLong(productId));
	}
	
	//post the courses
		@PostMapping("/products")
		public Courses addProducts(@RequestBody Courses course){
			return productServ.addCourses(course);
		}
		
		//update the courses
				@PutMapping("/products")
				public ResponseEntity<HttpStatus> updateProducts(@RequestBody Courses course){
				try {
					this.productServ.updateCourse(course);
      				return new ResponseEntity<>(HttpStatus.OK);
				}
				catch (Exception e) {
					// TODO: handle exception
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				}
	
}
