package com.dsd.naonomeado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.xml.ws.Endpoint;

@SpringBootApplication
public class NaonomeadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaonomeadoApplication.class, args);
		Endpoint.publish("http://localhost:8088/conversoap", new ConversorService());
        System.out.println("Servidor 'Protex' rodando em http://localhost:8088/conversoap");
	}

}
