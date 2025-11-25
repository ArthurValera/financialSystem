package com.financial.system.financial.system.infra.security.user;

import com.financial.system.financial.system.model.Person;
import com.financial.system.financial.system.repository.PersonRep;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRep personRep;

    public PersonDetailsService(PersonRep personRep) {
        this.personRep = personRep;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRep.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new PersonDetails(person);
    }
}