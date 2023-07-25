package com.backend.entity.dto;

import com.backend.entity.Technician;
import com.backend.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String name;
    protected String cpf;
    protected String email;
    protected String password;
    protected Set<Integer> profile = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")                                   //-> Formatação da data
    protected LocalDate dateRegister = LocalDate.now();

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public TechnicianDTO() {
        super();
        addProfile(Profile.CUSTOMER);                                                       //-> Adiciona o perfil de cliente para o técnico
    }

    public TechnicianDTO(Technician technicianDTO) {
        this.id = technicianDTO.getId();
        this.name = technicianDTO.getName();
        this.cpf = technicianDTO.getCpf();
        this.email = technicianDTO.getEmail();
        this.password = technicianDTO.getPassword();
        this.profile = technicianDTO.getProfile().stream().map(Profile::getCod).collect(Collectors.toSet());
        this.dateRegister = technicianDTO.getDateRegister();
    }

    //?--------------------------------------------------  Getters and Setters  ----------------------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getCod());
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }
}
