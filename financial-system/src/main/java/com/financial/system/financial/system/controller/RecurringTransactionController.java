package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.RecurringTransactionCreateDTO;
import com.financial.system.financial.system.dto.RecurringTransactionListingDTO;
import com.financial.system.financial.system.dto.RecurringTransactionUpdateDTO;
import com.financial.system.financial.system.service.RecurringTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recurring-transactions")
public class RecurringTransactionController {

    @Autowired
    private RecurringTransactionService service;

    @PostMapping
    public ResponseEntity<RecurringTransactionListingDTO> create(
            @RequestBody @Valid RecurringTransactionCreateDTO data) {

        var entity = service.create(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RecurringTransactionListingDTO(entity));
    }

    @GetMapping
    public ResponseEntity<Page<RecurringTransactionListingDTO>> list(
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        var page = service.read(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecurringTransactionListingDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid RecurringTransactionUpdateDTO dto) {

        var updated = service.update(id, dto);
        return ResponseEntity.ok(new RecurringTransactionListingDTO(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
