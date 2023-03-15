package com.demo.bluejay.services;

import java.util.List;

import com.demo.bluejay.entities.Products;

public interface ProductService {
public List<Products> getProducts(); 
public Products getProduct(long courseId); 
//public Products getProducts(Products course);
public Products addProducts(Products course);
}
