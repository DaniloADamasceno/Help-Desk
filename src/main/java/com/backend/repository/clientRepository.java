package com.backend.repository;

import com.backend.entity.Client;
import com.backend.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);       //--> Busca por EMAIL
}

