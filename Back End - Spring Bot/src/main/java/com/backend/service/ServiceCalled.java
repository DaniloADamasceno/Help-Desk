package com.backend.service;

import com.backend.entity.Called;
import com.backend.entity.Client;
import com.backend.entity.Technician;
import com.backend.entity.dto.CalledDTO;
import com.backend.entity.enums.Priority;
import com.backend.entity.enums.Status;
import com.backend.repository.CalledRepository;
import com.backend.service.exceptions.ObjectNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCalled {

    //! --------------------------------------------   Injection Dependence  -------------------------------------------
    @Autowired
    private CalledRepository repositoryCalled;

    @Autowired
    private ServiceTechnician technicianService;

    @Autowired
    private ServiceClient clientService;

    //! --------------------------------------------   Methods -> End-Points  ------------------------------------------

    // * FIND BY ID
    public Called findById(Integer id) {
        Optional<Called> objFindByIdCalled = repositoryCalled.findById(id);
        return objFindByIdCalled.orElseThrow(() ->
                new ObjectNotFoundException("Id not found! Try again! | ID Não Encontrado! Tente novamente ID:  " + id));
    }

    // * FIND ALL
    public List<Called> findAll() {
        return repositoryCalled.findAll();
    }

    // * CREATE
    @SneakyThrows
    public Called createCalled(CalledDTO objCalledDTO)  {
        return repositoryCalled.save(createNewCalled(objCalledDTO));
    }

    // * UPDATE
    public Called updateCalled(Integer id, CalledDTO calledUpdate){
        calledUpdate.setId(id);                                                                     //--> Seta o ID
        Called oldCalledForUpdate = findById(id);                                               //--> Busca o Chamado pelo ID
        oldCalledForUpdate = createNewCalled(calledUpdate);                            //--> Cria um novo Chamado
        return repositoryCalled.save(oldCalledForUpdate);                                       //--> Salva o Chamado
    }


    // * NEW CALLED
    @SneakyThrows
    public Called createNewCalled(CalledDTO objCalledDTO) {
        Technician technicianCalled = technicianService.findById(objCalledDTO.getTechnician());     //--> Busca o Técnico pelo ID
        Client clientCalled = clientService.findById(objCalledDTO.getClient());                    //--> Busca o Cliente pelo ID
        Called calledClientTechnician = new Called();                                                  //--> Instancia um novo objeto do tipo Chamado

        if (objCalledDTO.getId() != null) {
            calledClientTechnician.setId(objCalledDTO.getId());
        }

        // *--> Se o status for igual a 2 (Fechado)
        if (objCalledDTO.getStatus().equals(2)){                                                    //--> Se o status for igual a 2 (Fechado)
            calledClientTechnician.setDateClosed(LocalDate.now());
        }

        calledClientTechnician.setTechnician(technicianCalled);                                     //--> Seta o Técnico
        calledClientTechnician.setClient(clientCalled);                                             //--> Seta o Cliente
        calledClientTechnician.setDateOpened(objCalledDTO.getDateOpened());                         //--> Seta a data de abertura
        calledClientTechnician.setDateClosed(objCalledDTO.getDateClosed());                         //--> Seta a data de fechamento
        calledClientTechnician.setPriority(Priority.toEnum(objCalledDTO.getPriority()));       //--> Seta a prioridade
        calledClientTechnician.setStatus(Status.toEnum(objCalledDTO.getStatus()));             //--> Seta o status
        calledClientTechnician.setTitle(objCalledDTO.getTitle());                                   //--> Seta o título
        calledClientTechnician.setObservation(objCalledDTO.getObservation());                       //--> Seta a observação

        return calledClientTechnician;
    }


}
