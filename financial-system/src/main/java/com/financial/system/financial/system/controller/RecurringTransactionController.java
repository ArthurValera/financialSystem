package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.RecurringTransactionCreateDTO;
import com.financial.system.financial.system.dto.RecurringTransactionListingDTO;
import com.financial.system.financial.system.dto.RecurringTransactionUpdateDTO;
import com.financial.system.financial.system.infra.security.user.PersonDetails;
import com.financial.system.financial.system.service.RecurringTransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recurring-transactions")
@SecurityRequirement(name = "bearer-key")
public class RecurringTransactionController {

    @Autowired
    private RecurringTransactionService service;

    @PostMapping
    public ResponseEntity<RecurringTransactionListingDTO> create(
            @RequestBody @Valid RecurringTransactionCreateDTO data,
            @AuthenticationPrincipal PersonDetails principal) {

        var entity = service.create(data, principal.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RecurringTransactionListingDTO(entity));
    }

    @GetMapping
    public ResponseEntity<Page<RecurringTransactionListingDTO>> list(
            @AuthenticationPrincipal PersonDetails principal,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        var page = service.read(principal.getId(), pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecurringTransactionListingDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid RecurringTransactionUpdateDTO dto,
            @AuthenticationPrincipal PersonDetails principal) {

        var updated = service.update(id, dto, principal.getId());
        return ResponseEntity.ok(new RecurringTransactionListingDTO(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal PersonDetails principal) {
        service.delete(id, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
