package com.backend.repository;

import com.backend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    //? --------------------------------------------   Methods   -------------------------------------------------------
    // FIND BY EMAIL
    Optional<Client> findByEmail(String email);
}

