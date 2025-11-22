package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.CategoryCreateDTO;
import com.financial.system.financial.system.dto.CategoryListingDTO;
import com.financial.system.financial.system.model.Category;
import com.financial.system.financial.system.repository.CategoryRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRep categoryRep;

    @Transactional
    public Category create(CategoryCreateDTO data){
        var category = new Category(data);
        return categoryRep.save(category);
    }

    public Page<CategoryListingDTO> read(Pageable pageable){
        return categoryRep.findAll(pageable).map(CategoryListingDTO::new);
    }
}
