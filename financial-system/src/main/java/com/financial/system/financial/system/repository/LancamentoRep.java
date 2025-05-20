package com.financial.system.financial.system.repository;

import com.financial.system.financial.system.model.Lancamento;
import com.financial.system.financial.system.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRep extends JpaRepository<Lancamento, Long> {
    Page<Lancamento> findByAtivoTrue(Pageable pageable);
}
