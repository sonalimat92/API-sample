package com.demo.bluejay.services;

import java.util.List;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.bluejay.dao.ReconciliationDao;
import com.demo.bluejay.dao.SettlementDao;
import com.demo.bluejay.dao.TransactionDao;
import com.demo.bluejay.entities.Reconciliation;
import com.demo.bluejay.entities.SettlementFile;
import com.demo.bluejay.entities.TransactionFile;

@Service
	public class ReconciliationService {

	    @Autowired
	    private TransactionDao transactionRepository;

	    @Autowired
	    private SettlementDao settlementRepository;

	    @Autowired
	    private ReconciliationDao reconciliationRepository;

	    @Transactional
	    public void reconcile() {
	        List<TransactionFile> transactions = transactionRepository.findAll();
	        List<SettlementFile> settlements = settlementRepository.findAll();
	        
	        // Match transactions and settlements based on ID
	        Map<Long, TransactionFile> transactionMap = transactions.stream().collect(Collectors.toMap(TransactionFile::getTransactionId, Function.identity()));
	        Map<Long, SettlementFile> settlementMap = settlements.stream().collect(Collectors.toMap(SettlementFile::getTransactionId, Function.identity()));
	        Set<Long> allIds = new HashSet<>(transactionMap.keySet());
	        allIds.addAll(settlementMap.keySet());
	        List<Reconciliation> reconciliations = new ArrayList<>();
	        for (Long id : allIds) {
	        	TransactionFile transaction = transactionMap.get(id);
	        	SettlementFile settlement = settlementMap.get(id);
	            if (transaction != null && settlement != null) {
	                // Matched transaction and settlement
	                BigDecimal transactionAmount = transaction.getAmount();
	                BigDecimal settlementAmount = settlement.getNetamount();
	                reconciliations.add(new Reconciliation(transaction.getTransactionId(), settlement.getTransactionId(), transactionAmount, settlementAmount,"matched"));
	                transactionMap.remove(id);
	                settlementMap.remove(id);
	            } else if (transaction != null) {
	                // Unmatched transaction
	                BigDecimal transactionAmount = transaction.getAmount();
	                BigDecimal settlementAmount = BigDecimal.ZERO;
	                reconciliations.add(new Reconciliation(transaction.getTransactionId(),null, transactionAmount, settlementAmount, "unmatched"));
	            } else if (settlement != null) {
	                // Unmatched settlement
	                BigDecimal transactionAmount = BigDecimal.ZERO;
	                BigDecimal settlementAmount =settlement.getNetamount();
	                reconciliations.add(new Reconciliation(null, settlement.getTransactionId(), transactionAmount, settlementAmount, "unmatched"));
	            }
	        }
	        // Save reconciliations
	        for (Reconciliation reconciliation : reconciliations) {
	            reconciliationRepository.save(reconciliation);
	        }
	    }
	}


