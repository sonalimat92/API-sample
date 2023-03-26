package com.demo.bluejay.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.bluejay.entities.Reconciliation;


public interface ReconciliationDao extends JpaRepository<Reconciliation, Long> {
    // custom methods here
	 List<Reconciliation> findByTransactionId(Long transactionId);
	    
	    List<Reconciliation> findBySettlementId(Long settlementId);
	    
	    List<Reconciliation> findByReconciliationStatus(String reconciliationStatus);
	    
	    @Query("SELECT r FROM Reconciliation r WHERE r.transactionAmount = :transactionAmount AND r.settlementAmount = :settlementAmount")
	    List<Reconciliation> findByTransactionAndSettlementAmounts(@Param("transactionAmount") BigDecimal transactionAmount, @Param("settlementAmount") BigDecimal settlementAmount);
	    
	    @Modifying
	    @Query("UPDATE Reconciliation r SET r.reconciliationStatus = :reconciliationStatus WHERE r.id = :id")
	    void updateReconciliationStatusById(@Param("id") Long id, @Param("reconciliationStatus") String reconciliationStatus);

}

