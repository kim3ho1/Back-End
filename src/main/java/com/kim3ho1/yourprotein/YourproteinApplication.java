package com.kim3ho1.yourprotein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YourproteinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YourproteinApplication.class, args);
	}

}
