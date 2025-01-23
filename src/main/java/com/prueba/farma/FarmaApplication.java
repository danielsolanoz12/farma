package com.prueba.farma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.prueba.farma")
public class FarmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaApplication.class, args);
	}

}
