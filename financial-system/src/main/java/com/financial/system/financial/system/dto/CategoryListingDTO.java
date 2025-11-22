package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Category;

public record CategoryListingDTO(Long id, String name) {
    public CategoryListingDTO(Category category) {
        this(category.getId(),
                category.getName());
    }
}
