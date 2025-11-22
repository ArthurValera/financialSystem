package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.TransactionCreateDTO;
import com.financial.system.financial.system.dto.TransactionDetailDTO;
import com.financial.system.financial.system.dto.TransactionListingDTO;
import com.financial.system.financial.system.dto.TransactionUpdateDTO;
import com.financial.system.financial.system.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDetailDTO> create(@RequestBody @Valid TransactionCreateDTO data, UriComponentsBuilder uriBuilder){
        var transaction = transactionService.create(data);
        var uri = uriBuilder.path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransactionDetailDTO(transaction));
    }

    @GetMapping
    public ResponseEntity<Page<TransactionListingDTO>> read(@PageableDefault(size = 10, sort = {"description"}) Pageable pageable){
        var page = transactionService.read(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<TransactionDetailDTO> update(@RequestBody @Valid TransactionUpdateDTO data){
        var transaction = transactionService.update(data);
        return ResponseEntity.ok(new TransactionDetailDTO(transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
