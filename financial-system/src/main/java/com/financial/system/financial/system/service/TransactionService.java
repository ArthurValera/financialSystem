package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.TransactionCreateDTO;
import com.financial.system.financial.system.dto.TransactionListingDTO;
import com.financial.system.financial.system.dto.TransactionUpdateDTO;
import com.financial.system.financial.system.model.Category;
import com.financial.system.financial.system.model.Person;
import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.model.TransactionType;
import com.financial.system.financial.system.repository.CategoryRep;
import com.financial.system.financial.system.repository.PersonRep;
import com.financial.system.financial.system.repository.TransactionRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRep transactionRep;

    @Autowired
    private PersonRep personRep;

    @Autowired
    private CategoryRep categoryRep;

    @Transactional
    public Transaction create(TransactionCreateDTO data){
        var category = categoryRep.findById(data.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        var person = personRep.findById(data.peopleId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));

        var transaction = new Transaction(data, category, person);
        return transactionRep.save(transaction);
    }

    public Page<TransactionListingDTO> read(Pageable pageable){
        return transactionRep.findByActiveTrue(pageable).map(TransactionListingDTO::new);
    }

    public Transaction update(TransactionUpdateDTO data){
        var transaction = transactionRep.getReferenceById(data.id());

        Category category = null;
        if(data.categoryId() != null){
            category = categoryRep.findById(data.categoryId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        }

        Person person = null;
        if (data.personId() != null) {
            person = personRep.findById(data.personId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
        }
        transaction.update(data, person, category);
        return transactionRep.save(transaction);
    }

    public void delete(Long id){
        var transaction = transactionRep.getReferenceById(id);
        transaction.delete();
    }
}
