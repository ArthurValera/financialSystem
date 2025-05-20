package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.LancamentoCreateDTO;
import com.financial.system.financial.system.dto.LancamentoDetalhamentoDTO;
import com.financial.system.financial.system.dto.LancamentoListagemDTO;
import com.financial.system.financial.system.dto.LancamentoUpdateDTO;
import com.financial.system.financial.system.repository.LancamentoRep;
import com.financial.system.financial.system.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<LancamentoDetalhamentoDTO> create(@RequestBody @Valid LancamentoCreateDTO data, UriComponentsBuilder uriBuilder){
        var lancamento = lancamentoService.create(data);
        var uri = uriBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new LancamentoDetalhamentoDTO(lancamento));
    }

    @GetMapping
    public ResponseEntity<Page<LancamentoListagemDTO>> read(@PageableDefault(size = 10, sort = {"nome"})Pageable pageable){
        var page = lancamentoService.read(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid LancamentoUpdateDTO data){
        var lancamento = lancamentoService.update(data);
        return ResponseEntity.ok(new LancamentoDetalhamentoDTO(lancamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        lancamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
