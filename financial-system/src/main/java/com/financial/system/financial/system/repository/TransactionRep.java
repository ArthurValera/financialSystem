package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRep extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByActiveTrueAndPersonId(Long personId, Pageable pageable);

    List<Transaction> findByPersonId(Long personId);

    @Query("""
        SELECT
            COALESCE(SUM(
                CASE
                    WHEN t.type = 'INCOME' THEN t.amount
                    ELSE -t.amount
                END
            ), 0)
        FROM Transaction t
        WHERE t.person.id = :personId
        AND t.active = true
        AND t.paymentDate <= :today
    """)
    BigDecimal sumBalanceOfPerson(@Param("personId") Long personId, @Param("today") LocalDate today);


    @Query("""
        SELECT t
        FROM Transaction t
        WHERE t.person.id = :personId
        AND t.active = true
        AND t.paymentDate > :today
        AND t.paymentDate <= :until
    """)
    List<Transaction> findScheduledByPerson(@Param("personId") Long personId,
                                             @Param("today") LocalDate today,
                                             @Param("until") LocalDate until);

}
