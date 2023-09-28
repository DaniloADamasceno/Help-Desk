package com.backend.service;

import com.backend.entity.Person;
import com.backend.entity.Technician;
import com.backend.entity.dto.TechnicianDTO;
import com.backend.repository.personRepository;
import com.backend.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceTechnician {

    //? --------------------------------------------   Injection Dependence  -------------------------------------------
    @Autowired
    private com.backend.repository.technicianRepository technicianRepository;

    @Autowired
    private personRepository personRepository;


    //? ------------------------------------------------   Methods  ----------------------------------------------------
    // FIND BY ID
    public Technician findById(Integer id) {
        Optional<Technician> objById = technicianRepository.findById(String.valueOf(id));
        return objById.orElseThrow(() -> new ObjectNotFoundException("Technician not found! / Técnico não encontrado! + id: " + id));
    }

    //FIND ALL
    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    // FIND BY EMAIL
    public Technician findByEmail(String email) {
        Optional<Technician> objByEmail = technicianRepository.findByEmail(email);
        return objByEmail.orElse(null);
    }

    // CREATE
    public Technician create(TechnicianDTO objDTO) {
        objDTO.setId(null);                                                              //--> Para garantir que o objeto seja criado com um novo ID
        validateCPFandEmail(objDTO);                                                     //-->  Método pra Valida o CPF e Email
        Technician newCreate = new Technician(objDTO);
        return technicianRepository.save(newCreate);
    }

    // VALIDATE CPF AND EMAIL
    private void validateCPFandEmail(TechnicianDTO objDTO) {
        // Valida se o CPF já existe
        Optional<Person> objByCPFandEmail = personRepository.findByCpf(objDTO.getCpf());
        if (objByCPFandEmail.isPresent() && objByCPFandEmail.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already exists! / CPF já existe! " + objDTO.getCpf());
        }

        // Valida se o EMAIL já existe
        objByCPFandEmail = personRepository.findByEmail(objDTO.getEmail());
        if (objByCPFandEmail.isPresent() && !Objects.equals(objByCPFandEmail.get().getId(), objDTO.getId())) {
            throw new DataIntegrityViolationException("Email already exists! / Email já existe! " + objDTO.getEmail());
        }
    }



}
