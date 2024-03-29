package com.backend.repository;

import com.backend.entity.Called;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, Integer> {

    //! --------------------------------------------   Methods   -------------------------------------------------------
}
