package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.PessoaCreateDTO;
import com.financial.system.financial.system.dto.PessoaListagemDTO;
import com.financial.system.financial.system.dto.PessoaUpdateDTO;
import com.financial.system.financial.system.model.Pessoa;
import com.financial.system.financial.system.repository.PessoaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PessoaService {

    @Autowired
    private PessoaRep pessoaRep;

    @Transactional
    public Pessoa create(PessoaCreateDTO data) {
        var pessoa = new Pessoa(data);
        return pessoaRep.save(pessoa);
    }

    public Page<PessoaListagemDTO> read(Pageable pageable){
        return pessoaRep.findByAtivoTrue(pageable).map(PessoaListagemDTO::new);
    }

    @Transactional
    public Pessoa update(PessoaUpdateDTO data){
        var pessoa = pessoaRep.getReferenceById(data.id());
        pessoa.update(data);
        return pessoa;
    }

    @Transactional
    public void delete(Long id){
        var pessoa = pessoaRep.getReferenceById(id);
        pessoa.delete();
    }
}
