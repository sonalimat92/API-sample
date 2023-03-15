package com.demo.bluejay.entities;

import java.text.DateFormat;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity

public class TransactionFile {

	
public TransactionFile() {
		super();
		this.amount = amount;
		this.network = network;
		this.mode = mode;
		this.transactionDate = transactionDate;
		this.transactionId = transactionId;
	}



  


    @Column(name="amount")
	    private Double amount;


    @Column(name="network")
	    private String network;
    

    @Column(name="mode")
	    private String mode;
    

    @Column(name="transactionfiledate")
	    private String transactionDate;
    
    @Id
    @Column(name="transactionfileId")
	    private Long transactionId;




	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getNetwork() {
		return network;
	}


	public void setNetwork(String network) {
		this.network = network;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	
	}



