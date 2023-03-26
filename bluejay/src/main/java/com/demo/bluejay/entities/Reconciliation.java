package com.demo.bluejay.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "reconciliation")
	public class Reconciliation {


		public Reconciliation(Long transactionId, Long settlementId, BigDecimal transactionAmount,
				BigDecimal settlementAmount, String reconciliationStatus) {
			super();
			this.transactionId = transactionId;
			this.settlementId = settlementId;
			this.transactionAmount = transactionAmount;
			this.settlementAmount = settlementAmount;
			this.reconciliationStatus = reconciliationStatus;
		}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "transaction_id")
	    private Long transactionId;
	    
	    @Column(name = "settlement_id")
	    private Long settlementId;
	    
	    @Column(name = "transaction_amount")
	    private BigDecimal transactionAmount;
	    
	    @Column(name = "settlement_amount")
	    private BigDecimal settlementAmount;
	    
	    @Column(name = "reconciliation_status")
	    private String reconciliationStatus;
	    
	    // getters and setters
	}



