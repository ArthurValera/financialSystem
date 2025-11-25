package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersonRep extends JpaRepository<Person, Long> {
    Page<Person> findByActiveTrue(Pageable pageable);

    Optional <Person> findByEmail(String email);
}
