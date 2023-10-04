package com.backend.entity.dto;

import com.backend.entity.Called;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
public class CalledDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    protected Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")                             //-> Formatação da data
    protected LocalDate dateOpened = LocalDate.now();               //-> Data de abertura do chamado

    @JsonFormat(pattern = "dd/MM/yyyy")                             //-> Formatação da data
    protected LocalDate dateClosed;                                 //-> Data de fechamento do chamado

    @NotNull(message = "PRIORITY cannot be null! | PRIORIDADE não pode ser nula")                  //-> Prioridade não pode ser nula
    protected Integer priority;

    @NotNull(message = "STATUS cannot be null! | STATUS não pode ser nulo")                        //-> Status não pode ser nulo
    protected Integer status;

    @NotNull(message = "TITLE cannot be null! | TÌTULO não pode ser nulo")                         //-> Título não pode ser nulo
    protected String title;

    @NotNull(message = "OBSERVATION cannot be null! | OBSERVAÇÃO não pode ser nula")               //-> Observação não pode ser nula
    protected String observation;

    @NotNull(message = "CLIENT cannot be null! | CLIENTE não pode ser nulo")                       //-> Cliente não pode ser nulo
    private Integer client;

    @NotNull(message = "Technician cannot be null! | Técnico não pode ser nulo")                   //-> Técnico não pode ser nulo
    private Integer technician;

    private String NameClient;                                      //-> Nome do cliente
    private String NameTechnician;                                  //-> Nome do técnico

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public CalledDTO(Optional<Called> calledFindById) {
        super();
    }

    public CalledDTO(Called calledDTO) {
        this.id = calledDTO.getId();
        this.dateOpened = calledDTO.getDateOpened();
        this.dateClosed = calledDTO.getDateClosed();
        this.priority = calledDTO.getPriority().getCod();
        this.status = calledDTO.getStatus().getCod();
        this.title = calledDTO.getTitle();
        this.observation = calledDTO.getObservation();
        this.client = calledDTO.getClient().getId();
        this.technician = calledDTO.getTechnician().getId();
        this.NameClient = calledDTO.getClient().getName();
        this.NameTechnician = calledDTO.getTechnician().getName();
    }

}

