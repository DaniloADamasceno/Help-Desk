package com.backend.repository;

import com.backend.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface repositoryTechnician extends JpaRepository<Technician, String> {


   // Optional<Technician> findById(Integer id);             //--> Busca por ID
    Optional<Technician> findByEmail(String email);       //--> Busca por EMAIL
    //Optional<Technician> findByCPF(String cpf);           //--> Busca por CPF


}
