package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.PessoaCreateDTO;
import com.financial.system.financial.system.dto.PessoaDetalhamentoDTO;
import com.financial.system.financial.system.dto.PessoaListagemDTO;
import com.financial.system.financial.system.dto.PessoaUpdateDTO;
import com.financial.system.financial.system.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
   @Autowired
   private PessoaService pessoaService;

   @PostMapping
    public ResponseEntity create(@RequestBody @Valid PessoaCreateDTO data, UriComponentsBuilder uriBuilder){
       var pessoa = pessoaService.create(data);
       var uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
       return ResponseEntity.created(uri).body(new PessoaDetalhamentoDTO(pessoa));
   }

   @GetMapping
   public ResponseEntity<Page<PessoaListagemDTO>> read(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
      var page = pessoaService.read(pageable);
      return ResponseEntity.ok(page);
   }

   @PutMapping
   public ResponseEntity update(@RequestBody @Valid PessoaUpdateDTO data){
      var pessoa = pessoaService.update(data);
      return ResponseEntity.ok(new PessoaDetalhamentoDTO(pessoa));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity delete(@PathVariable Long id){
      pessoaService.delete(id);
      return ResponseEntity.noContent().build();
   }
}
