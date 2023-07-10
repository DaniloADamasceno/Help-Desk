package com.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpDeskBackEndApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println();
		System.out.println("✅ ✅");
		System.out.println("Help Desk - Sistema de chamados");
		System.out.println("Desenvolvido por: Danilo A. Damasceno");
		System.out.println("LinkedIn: https://www.linkedin.com/in/daniloadamasceno/");
		System.out.println("GitHub: https://github.com/DaniloADamasceno");
		System.out.println("Swagger: http://localhost:8080/swagger-ui.html");
		System.out.println("✅ ✅");
	}
}
