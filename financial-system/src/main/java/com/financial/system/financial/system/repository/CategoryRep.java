package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRep extends JpaRepository<Category, Long> {
}
