package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.CategoryCreateDTO;
import com.financial.system.financial.system.dto.CategoryListingDTO;
import com.financial.system.financial.system.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categories")
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CategoryCreateDTO data, UriComponentsBuilder uriBuilder){
        var category = categoryService.create(data);
        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryListingDTO(category));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryListingDTO>> read(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var list = categoryService.read(pageable);
        return ResponseEntity.ok(list);
    }
}
