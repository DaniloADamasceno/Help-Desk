package com.backend.resources;

import com.backend.entity.Technician;
import com.backend.entity.dto.TechnicianDTO;
import com.backend.service.ServiceTechnician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResources {

    private static final Logger logger = LoggerFactory.getLogger(TechnicianResources.class); //--> para imprimir no console

    //? --------------------------------------------   Methods -> End-Points  ------------------------------------------
    @Autowired
    private ServiceTechnician technicianService;

    @Autowired
    private Environment environment;                                                                    //--> Para imprimir no console

    //? --------------------------------------------   Methods -> End-Points  ------------------------------------------
    // FIND ALL
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {                                                 //  --> Retorna uma lista de TODOS os Técnicos
        List<Technician> listTechnicianFindAll = technicianService.findAll();
        List<TechnicianDTO> listTechnicianDTO = listTechnicianFindAll.stream().map
                        (TechnicianDTO::new).
                collect(Collectors.toList());                                                   // --> Converte a lista de Técnicos para uma lista de TécnicosDTO
        return ResponseEntity.ok(listTechnicianDTO);
    }

    //FIND BY ID
    @RequestMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findByIdTechnician(@PathVariable Integer id) {                //  --> Busca por ID
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Technician technicianFindById = technicianService.findById(id);
        return ResponseEntity.ok(new TechnicianDTO(technicianFindById));
    }

    //FIND BY EMAIL
    @GetMapping(value = "/search")
    public ResponseEntity<Technician> findByEmail(@PathVariable String email) {                       //  --> Busca por EMAIL
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        Technician technicianFindByEmail = technicianService.findByEmail(email);
        return ResponseEntity.ok(technicianFindByEmail);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO createTechnicianDTO) {     //  --> Cria um Técnico
        Technician newCreateTechnician = technicianService.create(createTechnicianDTO);
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCreateTechnician.getId()).toUri();
        return ResponseEntity.created(uri).build();
        //body(new TechnicianDTO(newCreateTechnician));
    }

    // UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO updateTechnicianDTO) {     //  --> Atualiza um Técnico
        Technician newUpdateTechnician = technicianService.update(id, updateTechnicianDTO);
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUpdateTechnician.getId()).toUri();
        return ResponseEntity.created(uri).build();
        //body(new TechnicianDTO(newUpdateTechnician));
    }

    // DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {     //  --> Deleta um Técnico
        technicianService.delete(id);
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        return ResponseEntity.noContent().build();
    }

//    //FIND BY CPF
//    @GetMapping(value = "/search")
//    public ResponseEntity<Technician> findByCPF(@PathVariable String cpf) {                           //  --> Busca por CPF
//        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
//        Technician technicianFindByCPF = serviceTechnician.findByCPF(cpf);
//        return ResponseEntity.ok(technicianFindByCPF);
//    }
}
