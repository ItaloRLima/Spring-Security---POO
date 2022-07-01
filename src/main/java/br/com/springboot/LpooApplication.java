package br.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LpooApplication {

	public static void main(String[] args) {
		SpringApplication.run(LpooApplication.class, args);
	}

}
