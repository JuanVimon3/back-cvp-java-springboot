package com.compraventap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
// 1. Obliga a JPA a buscar solo en la carpeta relacional
@EnableJpaRepositories(basePackages = "com.compraventap.repository.relational")
// 2. Obliga a Mongo a buscar solo en la carpeta nosql
@EnableMongoRepositories(basePackages = "com.compraventap.repository.nosql")
public class PropiedadRaizApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropiedadRaizApiApplication.class, args);
	}

}
