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

public class SettlementFile {

	
public SettlementFile() {
		super();
		this.netamount = netamount;
		this.network = network;
		this.mode = mode;
		this.settlementfiledate = settlementfiledate;
		this.transactionId = transactionId;
	}



  


    @Column(name="netamount")
	    private Double netamount;


    @Column(name="network")
	    private String network;
    

    @Column(name="mode")
	    private String mode;
    

    @Column(name="settlementfiledate")
	    private String settlementfiledate;
    
    @Id
    @Column(name="transactionIdFromSettlement")
	    private Long transactionId;

	public Double getNetamount() {
		return netamount;
	}

	public void setNetamount(Double netamount) {
		this.netamount = netamount;
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

	public String getSettlementfiledate() {
		return settlementfiledate;
	}

	public void setSettlementfiledate(String settlementfiledate) {
		this.settlementfiledate = settlementfiledate;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}




	
	}



