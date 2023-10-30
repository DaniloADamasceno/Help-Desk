package com.backend.resources;

import com.backend.entity.Client;
import com.backend.entity.dto.ClientDTO;
import com.backend.service.ServiceClient;
import javassist.tools.rmi.ObjectNotFoundException;
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

public class ClientResources {

    private static final Logger logger = LoggerFactory.getLogger(ClientResources.class); //--> para imprimir no console

    //! --------------------------------------------   Dependency Injection  -------------------------------------------
    @Autowired
    private ServiceClient clientService;

    @Autowired
    private Environment environment;                                                                    //--> Para imprimir no console


    //! --------------------------------------------   Methods -> End-Points  ------------------------------------------

    // * --> FIND ALL
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {                                                 //  --> Retorna uma lista de TODOS os Técnicos
        List<Client> listClientFindAll = clientService.findAll();
        List<ClientDTO> listClientDTO = listClientFindAll.stream().map
                        (ClientDTO::new).
                collect(Collectors.toList());                                                   // --> Converte a lista de Técnicos para uma lista de TécnicosDTO
        return ResponseEntity.ok(listClientDTO);
    }

    // * --> FIND BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findByIdClient(@PathVariable Integer id) {                //  --> Busca por ID
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                 //--> para imprimir no console
        Client clientFindById = clientService.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(clientFindById));
    }


    // * --> CREATE
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO createClientDTO) {     //  --> Cria um Técnico
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        Client newCreateClient = clientService.create(createClientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCreateClient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    // * --> UPDATE
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO updateClientDTO) {     //  --> Atualiza um Técnico
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        Client newUpdateClient = clientService.update(id, updateClientDTO);
        return ResponseEntity.ok().body(new ClientDTO(newUpdateClient));
    }

    // * --> DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException {     //  --> Deleta um Técnico
        logger.info("PORT / PORTA = " + environment.getProperty("local.server.port"));                //--> para imprimir no console
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
