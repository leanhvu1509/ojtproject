package com.lavu.ojtsource;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.lavu.ojtsource.config.StorageProperties;
import com.lavu.ojtsource.service.StorageService;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties(StorageProperties.class)
public class OjtsourceApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(OjtsourceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args ->{
			storageService.init();
		});
	}
}
