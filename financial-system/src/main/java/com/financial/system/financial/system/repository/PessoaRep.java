package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface PessoaRep extends JpaRepository<Pessoa, Long> {
    Page<Pessoa> findByAtivoTrue(Pageable pageable);
}
