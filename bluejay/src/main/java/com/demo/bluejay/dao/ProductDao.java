package com.demo.bluejay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.demo.bluejay.entities.Products;

public interface ProductDao extends JpaRepository<Products, Long>{

	

}
