package com.backend.service;

import com.backend.entity.Technician;
import com.backend.repository.repositoryTechnician;
import com.backend.service.exceptions.ObjectNotFoundException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

//    // FIND BY CPF
//    public Technician findByCPF(String cpf) {
//        Optional<Technician> objByCPF = technicianRepository.findByCPF(cpf);
//        return objByCPF.orElse(null);
//    }

}
