package com.backend.resources;

import com.backend.entity.Technician;
import com.backend.entity.dto.TechnicianDTO;
import com.backend.service.ServiceTechnician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //! --------------------------------------------   Dependency Injection  -------------------------------------------
    @Autowired
    private ServiceTechnician technicianService;

    @Autowired
    private Environment environment;                                                                    //--> Para imprimir no console


    //! --------------------------------------------   Methods -> End-Points  ------------------------------------------

    // * --> FIND ALL
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {                                                 //  --> Retorna uma lista de TODOS os Técnicos
        List<Technician> listTechnicianFindAll = technicianService.findAll();
        List<TechnicianDTO> listTechnicianDTO = listTechnicianFindAll.stream().map
                        (objListFindAll -> new TechnicianDTO(objListFindAll)).
                collect(Collectors.toList());                                                   // --> Converte a lista de Técnicos para uma lista de TécnicosDTO
        return ResponseEntity.ok().body(listTechnicianDTO);
    }

    // * --> FIND BY ID
    @RequestMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findByIdTechnician(@PathVariable Integer id) {                //  --> Busca por ID
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Technician technicianFindById = technicianService.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(technicianFindById));
    }


    // * --> CREATE
    @PreAuthorize("hasAnyRole('ADMIN')")
    //--> Permite que apenas o ADMIN crie um Técnico
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO createTechnicianDTO) {     //  --> Cria um Técnico
        Technician newCreateTechnician = technicianService.create(createTechnicianDTO);
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCreateTechnician.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // * --> UPDATE
    @PreAuthorize("hasAnyRole('ADMIN')")
    //--> Permite que apenas o ADMIN atualize um Técnico
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO updateTechnicianDTO) {     //  --> Atualiza um Técnico
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        Technician newUpdateTechnician = technicianService.update(id, updateTechnicianDTO);
        return ResponseEntity.ok().body(new TechnicianDTO(newUpdateTechnician));
    }

    // * --> DELETE
    @PreAuthorize("hasAnyRole('ADMIN')")
    //--> Permite que apenas o ADMIN delete um Técnico
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {     //  --> Deleta um Técnico
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
