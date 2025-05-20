package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.LancamentoCreateDTO;
import com.financial.system.financial.system.dto.LancamentoListagemDTO;
import com.financial.system.financial.system.dto.LancamentoUpdateDTO;
import com.financial.system.financial.system.model.Categoria;
import com.financial.system.financial.system.model.Lancamento;
import com.financial.system.financial.system.model.Pessoa;
import com.financial.system.financial.system.repository.CategoriaRep;
import com.financial.system.financial.system.repository.LancamentoRep;
import com.financial.system.financial.system.repository.PessoaRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRep lancamentoRep;

    @Autowired
    private PessoaRep pessoaRep;

    @Autowired
    private CategoriaRep categoriaRep;

    @Transactional
    public Lancamento create(LancamentoCreateDTO data){
        var categoria = categoriaRep.findById(data.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        var pessoa = pessoaRep.findById(data.pessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));

        var lancamento = new Lancamento(data, categoria, pessoa);
        return lancamentoRep.save(lancamento);
    }

    public Page<LancamentoListagemDTO> read(Pageable pageable){
        return lancamentoRep.findByAtivoTrue(pageable).map(LancamentoListagemDTO::new);
    }

    public Lancamento update(LancamentoUpdateDTO data){
        var lancamento = lancamentoRep.getReferenceById(data.id());

        Categoria categoria = null;
        if(data.categoriaId() != null){
            categoria = categoriaRep.findById(data.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        }

        Pessoa pessoa = null;
        if (data.pessoaId() != null) {
            pessoa = pessoaRep.findById(data.pessoaId()).orElseThrow(() -> new RuntimeException("Pessoa não encontrada."));
        }
        lancamento.update(data, pessoa, categoria);
        return lancamentoRep.save(lancamento);
    }

    public void delete(Long id){
        var lancamento = lancamentoRep.getReferenceById(id);
        lancamento.delete();
    }
}
