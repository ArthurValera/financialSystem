package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Person;

public record PersonListingDTO(Long id,
                               String name) {

    public PersonListingDTO(Person person){
        this(person.getId(), person.getName());
    }
}
