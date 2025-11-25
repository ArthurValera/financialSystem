package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.PersonCreateDTO;
import com.financial.system.financial.system.dto.PersonDetailDTO;
import com.financial.system.financial.system.dto.PersonListingDTO;
import com.financial.system.financial.system.dto.PersonUpdateDTO;
import com.financial.system.financial.system.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<PersonDetailDTO> create(@RequestBody @Valid PersonCreateDTO data, UriComponentsBuilder uriBuilder){
        var encryptedPassword = encoder.encode(data.password());
        var person = personService.create(data);
        var uri = uriBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDetailDTO(person));
    }

    @GetMapping
    public ResponseEntity<Page<PersonListingDTO>> read(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = personService.read(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<PersonDetailDTO> update(@RequestBody @Valid PersonUpdateDTO data){
        var person = personService.update(data);
        return ResponseEntity.ok(new PersonDetailDTO(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
