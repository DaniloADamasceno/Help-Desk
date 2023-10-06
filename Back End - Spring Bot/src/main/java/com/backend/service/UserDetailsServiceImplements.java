package com.backend.service;

import com.backend.entity.Person;
import com.backend.repository.PersonRepository;
import com.backend.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    //! --------------------------------------- DEPENDENCY INJECTION ---------------------------------------------------

    @Autowired
    private PersonRepository repositoryPerson;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = repositoryPerson.findByEmail(email);
        if (person.isPresent()) {
            return new UserSpringSecurity(person.get().getId(),
                    person.get().getEmail(),
                    person.get().getPassword(),
                    person.get().getProfile());
        }
        throw new UsernameNotFoundException(email);

    }
}
