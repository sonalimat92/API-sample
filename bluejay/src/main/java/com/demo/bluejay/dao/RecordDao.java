package com.demo.bluejay.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bluejay.entities.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Long> {

	    
	}


