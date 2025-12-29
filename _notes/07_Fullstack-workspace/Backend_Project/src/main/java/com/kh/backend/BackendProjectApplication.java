package com.kh.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BackendProjectApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BackendProjectApplication.class, args);
		// System.out.println("?");
	}

}
