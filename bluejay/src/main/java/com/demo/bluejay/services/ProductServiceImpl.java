package com.demo.bluejay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.demo.bluejay.entities.Courses;


@Service 
public class ProductServiceImpl implements ProductService {
	
	List<Courses> listtemp;
	
	public  ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		
		listtemp=new ArrayList();
		listtemp.add(new Courses(12,"test",890));
		listtemp.add(new Courses(12,"test2",890));
		listtemp.add(new Courses(12,"test3",890));

	}
	
  
	
	@Override
	public List<Courses> getCourses() {
		// TODO Auto-generated method stub
		return listtemp;
	}


	public Courses getCourse(long courseId) {
		// TODO Auto-generated method stub
		Courses c=null;
		for(Courses course:listtemp) {
			if(course.getId()==courseId) {
				c=course ;
			break;
				
			}}
		return c;
	}
		
	


	
	@Override
	public Courses addCourses(Courses course) {
		// TODO Auto-generated method stub
		listtemp.add(course);
		return course;
	}
	
	@Override
	public Courses updateCourse(Courses course) {
		// TODO Auto-generated method stub
		Courses c=null;
		for(Courses courseiter:listtemp) {
		if(courseiter.getId()==course.getId()) {
			courseiter.setProductString(course.getProductString());
			courseiter.setPrice(course.getPrice());
			c=courseiter;
			break;
			}
		}
		return course;
	}



	
}
