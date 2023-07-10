package com.backend.repository;

import com.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personRepository extends JpaRepository<Person, Integer> {
}
