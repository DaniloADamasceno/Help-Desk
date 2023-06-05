package com.backend.entity;


import com.backend.entity.enums.Priority;
import com.backend.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "chamados")
public abstract class Called implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)             //-> Auto incremento no banco de dados
    protected Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")                             //-> Formatação da data
    protected LocalDate dateOpened = LocalDate.now();               //-> Data de abertura do chamado

    @JsonFormat(pattern = "dd/MM/yyyy")                             //-> Formatação da data
    protected LocalDate dateClosed;                                 //-> Data de fechamento do chamado
    protected Priority priority;
    protected Status status;
    protected String title;
    protected String observation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public Called() {
        super();
    }

    public Called(Integer id, Priority priority, Status status, String title, String observation, Client client, Technician technician) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.observation = observation;
        this.client = client;
        this.technician = technician;
    }


    //?--------------------------------------------------  Getters and Setters  ----------------------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    //?--------------------------------------------------  hashCode and equals  ----------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Called)) return false;
        Called called = (Called) o;
        return Objects.equals(getId(), called.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}