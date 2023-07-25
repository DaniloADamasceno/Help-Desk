package com.backend.resources;

import com.backend.entity.Technician;
import com.backend.entity.dto.TechnicianDTO;
import com.backend.service.ServiceTechnician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.repository.repositoryTechnician;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResources {

    private static final Logger logger = LoggerFactory.getLogger(TechnicianResources.class); //--> para imprimir no console

    //? --------------------------------------------   Methods -> End-Points  ------------------------------------------
    @Autowired
    private repositoryTechnician technicianRepository;

    @Autowired
    private ServiceTechnician serviceTechnician;

    @Autowired
    private Environment environment;                                                                    //--> Para imprimir no console

    //? --------------------------------------------   Methods -> End-Points  ------------------------------------------
    // FIND ALL
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {                                                 //  --> Retorna uma lista de TODOS os Técnicos
        List<Technician> listTechnicianFindAll = serviceTechnician.findAll();
        List<TechnicianDTO> listTechnicianDTO = listTechnicianFindAll.stream().map
                (listTechnicianDTOConvert -> new TechnicianDTO(listTechnicianDTOConvert)).
                collect(Collectors.toList());                                                   // --> Converte a lista de Técnicos para uma lista de TécnicosDTO
        return ResponseEntity.ok(listTechnicianDTO);
    }

    //FIND BY ID
    @RequestMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findByIdTechnician(@PathVariable Integer id) {                //  --> Busca por ID
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Technician technicianFindById = serviceTechnician.findById(id);
        return ResponseEntity.ok( new TechnicianDTO(technicianFindById));
    }

    //FIND BY EMAIL
    @GetMapping(value = "/search")
    public ResponseEntity<Technician> findByEmail(@PathVariable String email) {                       //  --> Busca por EMAIL
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        Technician technicianFindByEmail = serviceTechnician.findByEmail(email);
        return ResponseEntity.ok(technicianFindByEmail);
    }

//    //FIND BY CPF
//    @GetMapping(value = "/search")
//    public ResponseEntity<Technician> findByCPF(@PathVariable String cpf) {                           //  --> Busca por EMAIL
//        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
//        Technician technicianFindByCPF = serviceTechnician.findByCPF(cpf);
//        return ResponseEntity.ok(technicianFindByCPF);
//    }
}
