package com.backend.resources;


import com.backend.entity.Called;
import com.backend.entity.dto.CalledDTO;
import com.backend.repository.CalledRepository;
import com.backend.service.ServiceCalled;
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

@RestController                                                                                         //  --> Para informar que é um controlador Rest
@RequestMapping(value = "/called")                                                                   //  --> Para informar o caminho do End-Point
public class CalledResources {

    private static final Logger logger = LoggerFactory.getLogger(CalledResources.class);        //  --> para imprimir no console

    //! --------------------------------------------   Dependency Injection  ------------------------------------------
    @Autowired
    private CalledRepository repositoryCalled;

    @Autowired
    private ServiceCalled calledService;

    @Autowired
    private Environment environment;                                                                    //  --> Para imprimir no console

    //! --------------------------------------------   Methods -> End-Points  ------------------------------------------

    // FIND BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<CalledDTO> findById(@PathVariable Integer id) {
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Called calledFindById = calledService.findById(id);
        return ResponseEntity.ok(new CalledDTO(calledFindById));
    }

    // FIND ALL
    @GetMapping
    public ResponseEntity<List<CalledDTO>> findAll() {                                                 //  --> Retorna uma lista de TODOS os Chamados
        List<Called> listCalledFindAll = repositoryCalled.findAll();
        List<CalledDTO> listCalledDTO = listCalledFindAll.stream().map
                        (CalledDTO::new).
                collect(Collectors.toList());                                               //  --> Converte a lista de Chamados para uma lista de CalledDTO
        return ResponseEntity.ok().body(listCalledDTO);
    }

    // * --> CREATE
    @PostMapping
    public ResponseEntity<CalledDTO> create(@Valid @RequestBody CalledDTO calledCreate){              //  --> Cria um novo Chamado
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //  --> para imprimir no console
        Called newCalledCreate = calledService.createCalled(calledCreate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newCalledCreate.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // * --> UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity<CalledDTO> update(@PathVariable Integer id, @Valid @RequestBody CalledDTO calledUpdate) { //  --> Atualiza um Chamado
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Called newCalledUpdate = calledService.updateCalled(id, calledUpdate);
        return ResponseEntity.ok().body(new CalledDTO(newCalledUpdate));
    }

}
