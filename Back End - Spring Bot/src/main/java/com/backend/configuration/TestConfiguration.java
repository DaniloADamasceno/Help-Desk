package com.backend.configuration;

import com.backend.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfiguration {


    //!--------------------------------------------   INJECTIONS   -----------------------------------------------------
    @Autowired
    private DataBaseService serviceDataBaseTest;

    @Bean
    public void startDataBase() {
        this.serviceDataBaseTest.startDataBase();                           // --> Método que Inicializa o Banco de Dados
    }
}
