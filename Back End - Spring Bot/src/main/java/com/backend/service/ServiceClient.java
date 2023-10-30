package com.backend.service;

import com.backend.entity.Client;
import com.backend.entity.Person;
import com.backend.entity.dto.ClientDTO;
import com.backend.repository.ClientRepository;
import com.backend.repository.PersonRepository;
import com.backend.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.service.exceptions.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceClient {

    //! --------------------------------------------   Injection Dependence  -------------------------------------------
    @Autowired
    private ClientRepository repositoryClient;

    @Autowired
    private PersonRepository repositoryPerson;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //! ------------------------------------------------   Methods  ----------------------------------------------------
    // * FIND BY ID
    public Client findById(Integer id) {
        Optional<Client> objById = repositoryClient.findById(Integer.valueOf(String.valueOf(id)));
        return objById.orElseThrow(() -> new ObjectNotFoundException("Client not found! / Técnico não encontrado! + id: " + id));
    }

    // * FIND ALL
    public List<Client> findAll() {
        return repositoryClient.findAll();
    }

//    // * FIND BY EMAIL
//    public Client findByEmail(String email) {
//        Optional<Client> objByEmail = repositoryClient.findByEmail(email);
//        return objByEmail.orElse(null);
//    }

    // * CREATE
    public Client create(ClientDTO objDTO) {
        objDTO.setId(null);                                                              //--> Para garantir que o objeto seja criado com um novo ID
        objDTO.setPassword(bCryptPasswordEncoder.encode(objDTO.getPassword()));         //--> Para criptografar a senha
        validateCPFandEmail(objDTO);                                                     //-->  Método para Valida o CPF e Email
        Client newCreate = new Client(objDTO);
        return repositoryClient.save(newCreate);
    }

    // * VALIDATE CPF AND EMAIL
    private void validateCPFandEmail(ClientDTO objDTO) {
        // Valida se o CPF já existe
        Optional<Person> objByCPFandEmail = repositoryPerson.findByCpf(objDTO.getCpf());
        if (objByCPFandEmail.isPresent() && objByCPFandEmail.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF already exists! / CPF já existe! " + objDTO.getCpf());
        }

        // * Valida se o EMAIL já existe
        objByCPFandEmail = repositoryPerson.findByEmail(objDTO.getEmail());
        if (objByCPFandEmail.isPresent() && !Objects.equals(objByCPFandEmail.get().getId(), objDTO.getId())) {
            throw new DataIntegrityViolationException("Email already exists! / Email já existe! " + objDTO.getEmail());
        }
    }

    // * UPDATE
    public Client update(Integer id, @Valid ClientDTO updateClientDTO) {
        updateClientDTO.setId(id);
        Client newUpdate = findById(id);

        if (!updateClientDTO.getPassword().equals(newUpdate.getPassword())) {
            updateClientDTO.setPassword(bCryptPasswordEncoder.encode(updateClientDTO.getPassword()));
        }
        validateCPFandEmail(updateClientDTO);                                                     //-->  Método para Valida o CPF e Email
        newUpdate = new Client(updateClientDTO);
        return repositoryClient.save(newUpdate);

    }

    // * DELETE
    public void delete(Integer id) {

        Client newDeleteClient = findById(id);

        // * Valida se o Técnico possui chamados
        if (!newDeleteClient.getCalls().isEmpty()) {
            throw new DataIntegrityViolationException("Client cannot be deleted! -> Has Service Orders |" +
                    "  Técnico não pode ser deletado! -> Possui Ordens de Serviço" + id);
        }
        repositoryClient.deleteById(Integer.valueOf(String.valueOf(id)));


    }
}
