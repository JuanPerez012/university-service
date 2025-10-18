package com.universitymicroservice;

import org.springframework.boot.SpringApplication;

public class TestUniversitymicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UniversitymicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
