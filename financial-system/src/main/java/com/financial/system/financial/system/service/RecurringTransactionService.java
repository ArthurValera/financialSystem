package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.RecurringTransactionCreateDTO;
import com.financial.system.financial.system.dto.RecurringTransactionListingDTO;
import com.financial.system.financial.system.dto.RecurringTransactionUpdateDTO;
import com.financial.system.financial.system.model.Category;
import com.financial.system.financial.system.model.Person;
import com.financial.system.financial.system.model.RecurringTransaction;
import com.financial.system.financial.system.repository.CategoryRep;
import com.financial.system.financial.system.repository.PersonRep;
import com.financial.system.financial.system.repository.RecurringTransactionRep;
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
    public RecurringTransaction create(RecurringTransactionCreateDTO data) {

        if (data.endDate().isBefore(data.startDate())) {
            throw new ValidationException("A data final deve ser após a data inicial.");
        }

        var category = categoryRep.findById(data.categoryId())
                .orElseThrow(() -> new ValidationException("Categoria não encontrada."));

        var person = personRep.findById(data.personId())
                .orElseThrow(() -> new ValidationException("Pessoa não encontrada."));

        var recurringTransaction = new RecurringTransaction(data, category, person);

        return recurringRep.save(recurringTransaction);
    }

    public Page<RecurringTransactionListingDTO> read(Pageable pageable) {
        return recurringRep.findByActiveTrue(pageable)
                .map(RecurringTransactionListingDTO::new);
    }

    @Transactional
    public RecurringTransaction update(Long id, RecurringTransactionUpdateDTO data) {

        var recurringTransaction = recurringRep.getReferenceById(id);

        Category category = null;
        if (data.categoryId() != null) {
            category = categoryRep.findById(data.categoryId())
                    .orElseThrow(() -> new ValidationException("Categoria não encontrada."));
        }

        Person person = null;
        if (data.personId() != null) {
            person = personRep.findById(data.personId())
                    .orElseThrow(() -> new ValidationException("Pessoa não encontrada."));
        }

        recurringTransaction.update(data, person, category);

        return recurringRep.save(recurringTransaction);
    }

    @Transactional
    public void delete(Long id) {
        var recurringTransaction = recurringRep.getReferenceById(id);
        recurringTransaction.delete();
    }
}
