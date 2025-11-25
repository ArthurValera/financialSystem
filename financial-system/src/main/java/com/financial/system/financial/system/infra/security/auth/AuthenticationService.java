package com.financial.system.financial.system.infra.security.auth;

import com.financial.system.financial.system.infra.security.user.PersonDetails;
import com.financial.system.financial.system.model.Person;
import com.financial.system.financial.system.repository.PersonRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

        @Autowired
        private PersonRep personRep;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            Person person = personRep.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new PersonDetails(person);
        }
}
