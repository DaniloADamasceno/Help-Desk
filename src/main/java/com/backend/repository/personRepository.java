package com.backend.repository;

import com.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface personRepository extends JpaRepository<Person, Integer> {


    //? --------------------------------------------   Methods   -------------------------------------------------------
    // FIND BY CPF
    Optional<Person> findByCpf(String cpf);

    // FIND BY EMAIL
    Optional<Person> findByEmail(String email);

}
