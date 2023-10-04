package com.backend.configuration;

import com.backend.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class DevConfiguration {

    //?--------------------------------------------   INJECTIONS   -----------------------------------------------------
    @Autowired
    private DataBaseService serviceDataBaseDev;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instantiateDataBase() {
        if (value.equals("create")) {
            this.serviceDataBaseDev.startDataBase();
        }
        return false;
    }
}