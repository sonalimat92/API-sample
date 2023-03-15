package com.demo.bluejay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bluejay.entities.Record;
import com.demo.bluejay.entities.TransactionFile;

@Repository
public interface TransactionDao extends JpaRepository<TransactionFile, Long> {

	    
	}


