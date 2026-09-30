package com.financial.system.financial.system.controller;

import com.financial.system.financial.system.dto.PersonCreateDTO;
import com.financial.system.financial.system.dto.PersonDetailDTO;
import com.financial.system.financial.system.dto.PersonListingDTO;
import com.financial.system.financial.system.dto.PersonUpdateDTO;
import com.financial.system.financial.system.infra.security.user.PersonDetails;
import com.financial.system.financial.system.service.PersonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDetailDTO> create(
            @RequestBody @Valid PersonCreateDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        var person = personService.create(data);
        var uri = uriBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDetailDTO(person));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<PersonListingDTO>> read(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable
    ) {
        var page = personService.read(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<PersonDetailDTO> update(@RequestBody @Valid PersonUpdateDTO data,
                                                    @AuthenticationPrincipal PersonDetails principal) {
        var person = personService.update(data, principal.getId());
        return ResponseEntity.ok(new PersonDetailDTO(person));
    }

    @DeleteMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal PersonDetails principal) {
        personService.delete(principal.getId());
        return ResponseEntity.noContent().build();
    }
}
