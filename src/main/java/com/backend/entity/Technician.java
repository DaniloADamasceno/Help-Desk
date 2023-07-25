package com.backend.entity;

import com.backend.entity.dto.TechnicianDTO;
import com.backend.entity.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "technicians")
public class Technician extends Person implements Serializable {

    public static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "technician")
    private List<Called> calls = new ArrayList<>();

    //?--------------------------------------------------  Constructor  ------------------------------------------------
    public Technician() {
        super();
        addProfile(Profile.CUSTOMER);                                                       //-> Adiciona o perfil de cliente para o técnico
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CUSTOMER);                                                       //-> Adiciona o perfil de cliente para o técnico

    }
    public Technician(TechnicianDTO technicianDTO) {                                                //-> Construtor para converter um TechnicianDTO para um Technician
        this.id = technicianDTO.getId();
        this.name = technicianDTO.getName();
        this.cpf = technicianDTO.getCpf();
        this.email = technicianDTO.getEmail();
        this.password = technicianDTO.getPassword();
        this.profile = technicianDTO.getProfile().stream().map(Profile::getCod).collect(Collectors.toSet());
        this.dateRegister = technicianDTO.getDateRegister();
    }

    //?--------------------------------------------------  Getters and Setters  ----------------------------------------
    public List<Called> getCalls() {
        return calls;
    }

    public void setCalls(List<Called> calls) {
        this.calls = calls;
    }

}