package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.TransactionCreateDTO;
import com.financial.system.financial.system.dto.TransactionListingDTO;
import com.financial.system.financial.system.dto.TransactionUpdateDTO;
import com.financial.system.financial.system.model.Category;
import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.repository.CategoryRep;
import com.financial.system.financial.system.repository.PersonRep;
import com.financial.system.financial.system.repository.TransactionRep;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRep transactionRep;

    @Autowired
    private PersonRep personRep;

    @Autowired
    private CategoryRep categoryRep;

    @Transactional
    public Transaction create(TransactionCreateDTO data, Long ownerId){
        var category = categoryRep.findById(data.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        // The owner is always the authenticated caller - never a client-supplied id.
        var owner = personRep.getReferenceById(ownerId);

        var transaction = new Transaction(data, category, owner);
        return transactionRep.save(transaction);
    }

    public Page<TransactionListingDTO> read(Long ownerId, Pageable pageable){
        return transactionRep.findByActiveTrueAndPersonId(ownerId, pageable).map(TransactionListingDTO::new);
    }

    @Transactional
    public Transaction update(TransactionUpdateDTO data, Long ownerId){
        var transaction = transactionRep.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada."));

        if (!transaction.getPerson().getId().equals(ownerId)) {
            throw new EntityNotFoundException("Transação não encontrada.");
        }

        Category category = null;
        if(data.categoryId() != null){
            category = categoryRep.findById(data.categoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada."));
        }

        transaction.update(data, category);
        return transaction;
    }

    @Transactional
    public void delete(Long id, Long ownerId){
        var transaction = transactionRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada."));

        if (!transaction.getPerson().getId().equals(ownerId)) {
            throw new EntityNotFoundException("Transação não encontrada.");
        }

        transaction.delete();
    }
}
