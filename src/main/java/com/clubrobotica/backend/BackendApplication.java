package com.clubrobotica.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // <-- 1. Importación añadida

@SpringBootApplication
@EnableScheduling // <-- 2. Activador de los CRON Jobs (Robot)
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}