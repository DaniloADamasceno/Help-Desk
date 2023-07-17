package com.backend.service;

import com.backend.entity.Technician;
import com.backend.repository.repositoryTechnician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceTechnician {

    //? --------------------------------------------   Injection Dependence  -------------------------------------------
    @Autowired
    private repositoryTechnician technicianRepository;


    //? ------------------------------------------------   Methods  ----------------------------------------------------
    // FIND BY ID
    public Technician findById(Integer id) {
        Optional<Technician> objById = technicianRepository.findById(String.valueOf(id));
        return objById.orElse(null);
    }

    // FIND BY EMAIL
    public Technician findByEmail(String email) {
        Optional<Technician> objByEmail = technicianRepository.findByEmail(email);
        return objByEmail.orElse(null);
    }

//    // FIND BY CPF
//    public Technician findByCPF(String cpf) {
//        Optional<Technician> objByCPF = technicianRepository.findByCPF(cpf);
//        return objByCPF.orElse(null);
//    }

}
