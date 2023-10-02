package com.backend.entity.dto;

import com.backend.entity.Client;
import com.backend.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO implements Serializable {


    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "O campo NOME é obrigatório")    //-> Validação do campo nome
    protected String name;

    @NotNull(message = "O campo CPF é obrigatório")     //-> Validação do campo CPF
    protected String cpf;

    @NotNull(message = "O campo E-MAL é obrigatório")   //-> Validação do campo email
    protected String email;

    @NotNull(message = "O campo SENHA é obrigatório")   //-> Validação do campo senha
    protected String password;
    protected Set<Integer> profile = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")                                   //-> Formatação da data
    protected LocalDate dateRegister = LocalDate.now();

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public ClientDTO() {
        super();
        addProfile(Profile.CUSTOMER);                                                       //-> Adiciona o perfil de cliente para o técnico
    }

    public ClientDTO(Client clientDTO) {
        this.id = clientDTO.getId();
        this.name = clientDTO.getName();
        this.cpf = clientDTO.getCpf();
        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();
        this.profile = clientDTO.getProfile().stream().map(Profile::getCod).collect(Collectors.toSet());
        this.dateRegister = clientDTO.getDateRegister();
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
