package com.pemila.aries.web.nodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author pemila
 * @date 2020/1/19 15:35
 **/
@EnableWebMvc
@SpringBootApplication
public class LoginNoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginNoDbApplication.class, args);
	}

}
