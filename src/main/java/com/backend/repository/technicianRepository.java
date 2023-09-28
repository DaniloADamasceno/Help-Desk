package com.backend.repository;

import com.backend.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface technicianRepository extends JpaRepository<Technician, String> {

    Optional<Technician> findByEmail(String email);       //--> Busca por EMAIL


}
