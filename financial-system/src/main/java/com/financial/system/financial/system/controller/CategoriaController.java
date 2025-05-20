package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.CategoriaCreateDTO;
import com.financial.system.financial.system.dto.CategoriaListagemDTO;
import com.financial.system.financial.system.dto.PessoaDetalhamentoDTO;
import com.financial.system.financial.system.dto.PessoaListagemDTO;
import com.financial.system.financial.system.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CategoriaCreateDTO data, UriComponentsBuilder uriBuilder){
        var categoria = categoriaService.create(data);
        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaListagemDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaListagemDTO>> read(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var list = categoriaService.read(pageable);
        return ResponseEntity.ok(list);
    }
}
