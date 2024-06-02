package com.toolrent.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Rental API", version = "1.0", description = "Rental Microservice", termsOfService = "termsOfService", contact = @Contact(name = "Priya", email = "priya2vijayan@gmail.com")))
public class DemoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
//Swagger url : http://localhost:8080/swagger-ui/index.html
//api doc : http://localhost:8080/registration-docs(.yaml)
//actuator : http://localhost:8080/actuator