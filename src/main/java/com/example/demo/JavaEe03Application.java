package com.example.demo;

import com.example.demo.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaEe03Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaEe03Application.class, args);
	}

	@Bean
	void runRepository() {

	}

}
