package com.backend.entity;


import com.backend.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "person")
public abstract class Person implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                    //-> Auto incremento no banco de dados
    protected Integer id;

    protected String name;

    @CPF
    @Column(unique = true)                                                //-> Não pode ter cpf repetido
    protected String cpf;

    @Column(unique = true)                                                //-> Não pode ter email repetido
    protected String email;
    protected String password;

    @JsonFormat(pattern = "dd/MM/yyyy")                                   //-> Formatação da data
    protected LocalDate dateRegister = LocalDate.now();

    @ElementCollection(fetch = FetchType.EAGER)                           //-> Garante que não vai repetir o mesmo valor
    @CollectionTable(name = "profile")                                    //-> Nome da tabela que vai ser criada
    protected Set<Integer> profile = new HashSet<>();

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public Person() {
        super();
        addProfile(Profile.CUSTOMER);                             //->Todos os usuários cadastrado é um cliente
    }

    public Person(Integer id, String name, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addProfile(Profile.CUSTOMER);                             //->Todos os usuários cadastrado é um cliente
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

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Set<Profile> getProfile() {
        return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profile.add(profile.getCod());
    }

    //?-------------------------------------------------  HashCode and Equals  -----------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
