package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.CategoryCreateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Category() {}

    public Category(CategoryCreateDTO data) {
        this.name = data.name();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
