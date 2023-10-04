package com.backend.repository;

import com.backend.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, String> {

    //? --------------------------------------------   Methods   -------------------------------------------------------
    // FIND BY EMAIL
    Optional<Technician> findByEmail(String email);


}
