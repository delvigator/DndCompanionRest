package com.example.dndCompanion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@EnableJpaRepositories("com.example.dndCompanion.*")
@ComponentScan(basePackages = { "com.example.dndCompanion.*" })
@EntityScan("com.example.dndCompanion.*")
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		SpringApplication.run(Main.class, args);
	}

}
