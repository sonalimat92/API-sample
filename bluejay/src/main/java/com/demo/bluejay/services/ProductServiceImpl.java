package com.demo.bluejay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.bluejay.dao.ProductDao;
import com.demo.bluejay.entities.Products;


@Service 
public class ProductServiceImpl implements ProductService {
	
	
	@Autowired
	private ProductDao productDao;
	
	List<Products> listtemp;
	
	public  ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		
		listtemp=new ArrayList();
		listtemp.add(new Products(12,"test",890));
		listtemp.add(new Products(12,"test2",890));
		listtemp.add(new Products(12,"test3",890));

	}

	public List<Products> getProducts() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	public Products getProduct(long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Products addProducts(Products product) {
		// TODO Auto-generated method stub
		productDao.save(product);
		return product;
	}
	
  
	
//	@Override
//	public List<Products> getCourses() {
//		// TODO Auto-generated method stub
//		return courseDao.findAll();
//	}
//
//	@Override
//	public Products getCourse(long courseId) {
//		// TODO Auto-generated method stub
//		Products c=null;
//		for(Products course:listtemp) {
//			if(course.getId()==courseId) {
//				c=course ;
//			break;
//				
//			}}
//		return c;
//	}
//		
//	
//
//
//	
//	@Override
//	public Products addCourses(Products course) {
//		// TODO Auto-generated method stub
//		listtemp.add(course);
//		return course;
//	}
//	
//	@Override
//	public Products updateCourse(Products course) {
//		// TODO Auto-generated method stub
//		Products c=null;
//		for(Products courseiter:listtemp) {
//		if(courseiter.getId()==course.getId()) {
//			courseiter.setProductString(course.getProductString());
//			courseiter.setPrice(course.getPrice());
//			c=courseiter;
//			break;
//			}
//		}
//		return course;
//	}
//


	
}
