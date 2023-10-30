package com.backend.service;

import com.backend.entity.Person;
import com.backend.entity.Technician;
import com.backend.entity.dto.TechnicianDTO;
import com.backend.repository.PersonRepository;
import com.backend.repository.TechnicianRepository;
import com.backend.service.exceptions.ObjectNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import com.backend.service.exceptions.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceTechnician {

    //! --------------------------------------------   Injection Dependence  -------------------------------------------
    @Autowired
    private TechnicianRepository repositoryTechnician;
    @Autowired
    private PersonRepository repositoryPerson;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //! ------------------------------------------------   Methods  ----------------------------------------------------

    // * FIND BY ID
    public Technician findById(Integer id) {
        Optional<Technician> objById = repositoryTechnician.findById(String.valueOf((id)));
        return objById.orElseThrow(() -> new ObjectNotFoundException("Technician not found! / Técnico não encontrado! + id: " + id));
    }

    // * FIND ALL
    public List<Technician> findAll() {
        return repositoryTechnician.findAll();
    }

//    // * FIND BY EMAIL
//    public Technician findByEmail(String email) {
//        Optional<Technician> objByEmail = repositoryTechnician.findByEmail(email);
//        return objByEmail.orElse(null);
//    }

    // * CREATE
    public Technician create(TechnicianDTO objTechnicianDTO) {
        objTechnicianDTO.setId(null);                                                              //--> Para garantir que o objeto seja criado com um novo ID
        objTechnicianDTO.setPassword(bCryptPasswordEncoder.encode(objTechnicianDTO.getPassword()));         //--> Para criptografar a senha
        validateCPFandEmail(objTechnicianDTO);                                                     //-->  Método para Valida o CPF e Email
        Technician newCreate = new Technician(objTechnicianDTO);
        return repositoryTechnician.save(newCreate);
    }

    // * VALIDATE CPF AND EMAIL
    private void validateCPFandEmail(TechnicianDTO objDTOValidaCPF) {
        // Valida se o CPF já existe
        Optional<Person> objByCPFandEmail = repositoryPerson.findByCpf(objDTOValidaCPF.getCpf());
        if (objByCPFandEmail.isPresent() && objByCPFandEmail.get().getId() != objDTOValidaCPF.getId()) {
            throw new DataIntegrityViolationException("CPF already exists! / CPF já existe! ");
        }

        // Valida se o EMAIL já existe
        objByCPFandEmail = repositoryPerson.findByEmail(objDTOValidaCPF.getEmail());
        if (objByCPFandEmail.isPresent() && !Objects.equals(objByCPFandEmail.get().getId(), objDTOValidaCPF.getId())) {
            throw new DataIntegrityViolationException("Email already exists! / Email já existe! ");
        }
    }

    // UPDATE
    public Technician update(Integer id, @Valid TechnicianDTO updateTechnicianDTO) {
        updateTechnicianDTO.setId(id);
        Technician newUpdate = findById(id);
        validateCPFandEmail(updateTechnicianDTO);                                                     //-->  Método para Valida o CPF e Email
        newUpdate = new Technician(updateTechnicianDTO);
        return repositoryTechnician.save(newUpdate);
    }

    // DELETE
    @SneakyThrows
    public void delete(Integer id) {
        Technician newDeleteTechnician = findById(id);

        // Valida se o Técnico possui chamados
        if (!newDeleteTechnician.getCalls().isEmpty()) {
            throw new DataIntegrityViolationException("Technician cannot be deleted! -> Has Service Orders |  " +
                    "Técnico não pode ser deletado! -> Possui Ordens de Serviço" + id);
        }
        repositoryTechnician.deleteById(String.valueOf(id));


    }
}
