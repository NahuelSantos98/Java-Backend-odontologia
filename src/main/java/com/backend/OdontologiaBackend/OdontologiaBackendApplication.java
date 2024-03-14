package com.backend.OdontologiaBackend;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class OdontologiaBackendApplication {

	private static Logger logger = LoggerFactory.getLogger(OdontologiaBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OdontologiaBackendApplication.class, args);
		logger.info("OdontologiaBackendApplication esta corriendo...");
	}

	@Bean
	public ModelMapper modelMapper(){		//Permite que podamos usar un modelMapper
		return modelMapper();
	}


}

