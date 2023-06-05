package com.backend.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "clients")
public class Client extends Person implements Serializable {

    public static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "client")
    private List<Called> calls = new ArrayList<>();

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public Client() {
        super();
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    //?--------------------------------------------------  Getters and Setters  ----------------------------------------
    public List<Called> getCalls() {
        return calls;
    }

    public void setCalls(List<Called> calls) {
        this.calls = calls;
    }
}