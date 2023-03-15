package com.demo.bluejay.services;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bluejay.entities.Record;


@Repository
public interface TransactionService extends JpaRepository<Record, Long> {
    
}