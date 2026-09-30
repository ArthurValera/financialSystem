package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.RecurringTransactionCreateDTO;
import com.financial.system.financial.system.dto.RecurringTransactionListingDTO;
import com.financial.system.financial.system.dto.RecurringTransactionUpdateDTO;
import com.financial.system.financial.system.model.Category;
import com.financial.system.financial.system.model.RecurringTransaction;
import com.financial.system.financial.system.repository.CategoryRep;
import com.financial.system.financial.system.repository.PersonRep;
import com.financial.system.financial.system.repository.RecurringTransactionRep;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RecurringTransactionService {

    @Autowired
    private RecurringTransactionRep recurringRep;

    @Autowired
    private PersonRep personRep;

    @Autowired
    private CategoryRep categoryRep;

    @Transactional
    public RecurringTransaction create(RecurringTransactionCreateDTO data, Long ownerId) {

        if (data.endDate().isBefore(data.startDate())) {
            throw new ValidationException("A data final deve ser após a data inicial.");
        }

        var category = categoryRep.findById(data.categoryId())
                .orElseThrow(() -> new ValidationException("Categoria não encontrada."));

        // The owner is always the authenticated caller - never a client-supplied id.
        var owner = personRep.getReferenceById(ownerId);

        var recurringTransaction = new RecurringTransaction(data, category, owner);

        return recurringRep.save(recurringTransaction);
    }

    public Page<RecurringTransactionListingDTO> read(Long ownerId, Pageable pageable) {
        return recurringRep.findByActiveTrueAndPersonId(ownerId, pageable)
                .map(RecurringTransactionListingDTO::new);
    }

    @Transactional
    public RecurringTransaction update(Long id, RecurringTransactionUpdateDTO data, Long ownerId) {

        var recurringTransaction = recurringRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento recorrente não encontrado."));

        if (!recurringTransaction.getPerson().getId().equals(ownerId)) {
            throw new EntityNotFoundException("Lançamento recorrente não encontrado.");
        }

        Category category = null;
        if (data.categoryId() != null) {
            category = categoryRep.findById(data.categoryId())
                    .orElseThrow(() -> new ValidationException("Categoria não encontrada."));
        }

        recurringTransaction.update(data, category);

        return recurringTransaction;
    }

    @Transactional
    public void delete(Long id, Long ownerId) {
        var recurringTransaction = recurringRep.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lançamento recorrente não encontrado."));

        if (!recurringTransaction.getPerson().getId().equals(ownerId)) {
            throw new EntityNotFoundException("Lançamento recorrente não encontrado.");
        }

        recurringTransaction.delete();
    }
}
