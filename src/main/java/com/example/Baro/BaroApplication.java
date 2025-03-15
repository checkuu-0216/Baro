package com.example.Baro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class BaroApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaroApplication.class, args);
	}

}
