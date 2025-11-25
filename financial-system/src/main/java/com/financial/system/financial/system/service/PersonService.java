package com.financial.system.financial.system.service;

import com.financial.system.financial.system.dto.PersonCreateDTO;
import com.financial.system.financial.system.dto.PersonListingDTO;
import com.financial.system.financial.system.dto.PersonUpdateDTO;
import com.financial.system.financial.system.model.Person;
import com.financial.system.financial.system.repository.PersonRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PersonService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PersonRep personRep;

    @Transactional
    public Person create(PersonCreateDTO data) {
        var encryptedPassword = encoder.encode(data.password());
        var person = new Person(data, encryptedPassword);
        return personRep.save(person);
    }


    public Page<PersonListingDTO> read(Pageable pageable){
        return personRep.findByActiveTrue(pageable).map(PersonListingDTO::new);
    }

    @Transactional
    public Person update(PersonUpdateDTO data){
        var person = personRep.getReferenceById(data.id());
        person.update(data);
        return person;
    }

    @Transactional
    public void delete(Long id){
        var person = personRep.getReferenceById(id);
        person.delete();
    }
}
