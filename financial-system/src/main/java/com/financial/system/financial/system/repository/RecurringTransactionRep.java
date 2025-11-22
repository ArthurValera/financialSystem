package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.RecurringTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RecurringTransactionRep extends JpaRepository<RecurringTransaction, Long> {

    Page<RecurringTransaction> findByActiveTrue(Pageable pageable);

    List<RecurringTransaction> findByPersonIdAndActiveTrue(Long personId);
}