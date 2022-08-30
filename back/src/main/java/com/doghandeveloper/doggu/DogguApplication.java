package com.doghandeveloper.doggu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DogguApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogguApplication.class, args);
	}

}
