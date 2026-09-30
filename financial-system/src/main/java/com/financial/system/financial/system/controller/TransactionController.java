package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.TransactionCreateDTO;
import com.financial.system.financial.system.dto.TransactionDetailDTO;
import com.financial.system.financial.system.dto.TransactionListingDTO;
import com.financial.system.financial.system.dto.TransactionUpdateDTO;
import com.financial.system.financial.system.infra.security.user.PersonDetails;
import com.financial.system.financial.system.service.TransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
@SecurityRequirement(name = "bearer-key")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDetailDTO> create(@RequestBody @Valid TransactionCreateDTO data,
                                                         @AuthenticationPrincipal PersonDetails principal,
                                                         UriComponentsBuilder uriBuilder){
        var transaction = transactionService.create(data, principal.getId());
        var uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransactionDetailDTO(transaction));
    }

    @GetMapping
    public ResponseEntity<Page<TransactionListingDTO>> read(@AuthenticationPrincipal PersonDetails principal,
                                                              @PageableDefault(size = 10, sort = {"description"}) Pageable pageable){
        var page = transactionService.read(principal.getId(), pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<TransactionDetailDTO> update(@RequestBody @Valid TransactionUpdateDTO data,
                                                         @AuthenticationPrincipal PersonDetails principal){
        var transaction = transactionService.update(data, principal.getId());
        return ResponseEntity.ok(new TransactionDetailDTO(transaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal PersonDetails principal){
        transactionService.delete(id, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
