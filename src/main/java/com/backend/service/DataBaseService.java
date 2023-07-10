package com.backend.service;

import com.backend.entity.Called;
import com.backend.entity.Client;
import com.backend.entity.Technician;
import com.backend.entity.enums.Priority;
import com.backend.entity.enums.Profile;
import com.backend.entity.enums.Status;
import com.backend.repository.calledRepository;
import com.backend.repository.clientRepository;
import com.backend.repository.technicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataBaseService {

    //--------------------------------------------   INJECTIONS   ------------------------------------------------------
    @Autowired
    private technicianRepository technicianRepository;

    @Autowired
    private clientRepository clientRepository;

    @Autowired
    private calledRepository calledRepository;


    public void startDataBase() {

        // ADD Perfil de ADMIN para o Técnico TEST
        Technician tecnicoTest = new Technician(null, "Tecnico TEST", "00000000011", "TecnicoTEST@Gmail.com", "123456");
        tecnicoTest.addProfile(Profile.ADMIN);

        // ADD Client TEST
        Client clienteTest = new Client(null, "Cliente TEST", "00000000022", "ClientTEST@Gmail.com", "123456");

        // ADD Chamado TEST
        Called chamadoTest = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado de TEST", "Chamado TEST", clienteTest, tecnicoTest);

        technicianRepository.saveAll(Arrays.asList(tecnicoTest));
        clientRepository.saveAll(Arrays.asList(clienteTest));
        calledRepository.saveAll(Arrays.asList(chamadoTest));
    }
}
