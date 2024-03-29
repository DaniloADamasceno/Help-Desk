package com.backend.repository;

import com.backend.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, String> {

    //! --------------------------------------------   Methods   -------------------------------------------------------
}
