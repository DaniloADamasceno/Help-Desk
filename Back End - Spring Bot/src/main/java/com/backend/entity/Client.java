package com.backend.entity;

import com.backend.entity.dto.ClientDTO;
import com.backend.entity.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "clients")
public class Client extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Called> calls = new ArrayList<>();

    //! -------------------------------------------------  Constructor  ------------------------------------------------
    public Client(@Valid ClientDTO updateClientDTO) {
        super();
        addProfile(Profile.CUSTOMER);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CUSTOMER);

    }

    public Client() {

    }

    //! -------------------------------------------------  Getters and Setters  ----------------------------------------
    public List<Called> getCalls() {
        return calls;
    }

    public void setCalls(List<Called> calls) {
        this.calls = calls;
    }
}