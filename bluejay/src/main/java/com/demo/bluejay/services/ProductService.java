package com.demo.bluejay.services;

import java.util.List;

import com.demo.bluejay.entities.Courses;

public interface ProductService {
public List<Courses> getCourses(); 
public Courses getCourse(long courseId); 
public Courses addCourses(Courses course);
public Courses updateCourse(Courses course);
}
