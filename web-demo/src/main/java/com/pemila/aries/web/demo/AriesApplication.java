package com.pemila.aries.web.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class AriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AriesApplication.class, args);
	}

}
