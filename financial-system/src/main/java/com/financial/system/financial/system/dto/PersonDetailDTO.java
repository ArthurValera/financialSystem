package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Address;
import com.financial.system.financial.system.model.Person;

public record PersonDetailDTO(Long id, String nome, boolean ativo, Address address) {

    public PersonDetailDTO(Person person){
        this(person.getId(),
                person.getName(),
                person.isActive(),
                person.getAddress());
    }
}
