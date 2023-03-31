package com.demo.bluejay.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bluejay.entities.Movies;
import com.demo.bluejay.entities.Record;
import com.demo.bluejay.entities.TransactionFile;

@Repository
public interface MoviesDao extends JpaRepository<Movies, Long> {
  //  List<Movies> findByCategory(String category);

	    
	}


