package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.CategoriaCreateDTO;
import com.financial.system.financial.system.dto.CategoriaListagemDTO;
import com.financial.system.financial.system.model.Categoria;
import com.financial.system.financial.system.repository.CategoriaRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRep categoriaRep;

    @Transactional
    public Categoria create(CategoriaCreateDTO data){
        var categoria = new Categoria(data);
        return categoriaRep.save(categoria);
    }

    public Page<CategoriaListagemDTO> read(Pageable pageable){
        return categoriaRep.findAll(pageable).map(CategoriaListagemDTO::new);
    }
}
