package com.backend.service;

import com.backend.entity.Called;
import com.backend.entity.Client;
import com.backend.entity.Technician;
import com.backend.entity.enums.Priority;
import com.backend.entity.enums.Profile;
import com.backend.entity.enums.Status;
import com.backend.repository.CalledRepository;
import com.backend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataBaseService {

    //! -------------------------------------------   Dependency Injection   -------------------------------------------
    @Autowired
    private  CalledRepository repositoryCalled;

    @Autowired
    private PersonRepository repositoryPerson;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //! ------------------------------------------------   Methods  ----------------------------------------------------
    public void startDataBase() {

        // ADD Perfil de ADMIN para o Técnico TEST
        Technician tecnicoTest = new Technician(null, "Tecnico TEST", "00000000000", "TecnicoTEST@Gmail.com", bCryptPasswordEncoder.encode("123456"));
        tecnicoTest.addProfile(Profile.ADMIN);

        // ADD Perfis de Técnicos
        Technician tecnico1 = new Technician(null, "Antonio", "482.858.590-70", "antonio@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Technician tecnico2 = new Technician(null, "Bernadete", "403.738.190-72", "Bernadete@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Technician tecnico3 = new Technician(null, "Carlos", "884.631.920-68", "Carlos@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Technician tecnico4 = new Technician(null, "Daniel", "006.955.340-84", "Daniel@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Technician tecnico5 = new Technician(null, "Emanuel", "353.081.300-19", "Emanuel@gmail.com", bCryptPasswordEncoder.encode("123456"));

        // ADD Client TEST
        Client clienteTest = new Client(null, "Cliente TEST", "00000000022", "ClientTEST@Gmail.com", bCryptPasswordEncoder.encode("123456"));

        // ADD Perfis de Clientes
        Client cliente1 = new Client(null, "Fabio Quirino", "375.171.390-53", "FabioQuirino@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Client cliente2 = new Client(null, "Gean", "800.898.680-80", "Gean@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Client cliente3 = new Client(null, "Keil Henrique", "404.857.620-80", "KeilHenrique@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Client cliente4 = new Client(null, "Hugo Moura", "470.011.020-16", "HugoMoura@gmail.com", bCryptPasswordEncoder.encode("123456"));
        Client cliente5 = new Client(null, "Leonardo Juliano", "968.846.490-20", "LeonardoJuliano@gmail.com", bCryptPasswordEncoder.encode("123456"));

        // ADD Chamado TEST
        Called chamadoTest = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado de TEST", "Chamado TEST", clienteTest, tecnicoTest);

        // ADD Chamados
        Called chamado1 = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado N° 2023.0001",
                "ALTA PRIORIDADE --> EM PROGRESSO", cliente1, tecnico1);

        Called chamado2 = new Called(null, Priority.LOW, Status.OPEN, "Chamado N° 2023.0002",
                "Baixa --> ABERTO ", cliente2, tecnico2);

        Called chamado3 = new Called(null, Priority.MEDIUM, Status.CLOSED, "Chamado N° 2023.0003",
                "Média --> FECHADO", cliente3, tecnico4);

        Called chamado4 = new Called(null, Priority.HIGH, Status.PROGRESS, "Chamado N° 2023.0004",
                "ALTA PRIORIDADE --> EM PROGRESSO", cliente4, tecnico4);

        Called chamado5 = new Called(null, Priority.LOW, Status.OPEN, "Chamado N° 2023.0005",
                "Baixa --> ABERTO", cliente1, tecnico5);

        Called chamado6 = new Called(null, Priority.LOW, Status.OPEN, "Chamado N° 2023.0006",
                "Baixa --> ABERTO", cliente3, tecnico2);

        // * SAVE
        repositoryPerson.saveAll(Arrays.asList(tecnicoTest, tecnico1, tecnico2, tecnico3, tecnico4, tecnico5)); // --> Salva os Técnicos
        repositoryPerson.saveAll(Arrays.asList(clienteTest, cliente1, cliente2, cliente3, cliente4, cliente5)); // --> Salva os Clientes
        repositoryCalled.saveAll(Arrays.asList(chamadoTest, chamado1, chamado2, chamado3, chamado4, chamado5, chamado6)); // --> Salva os Chamados
    }
}
