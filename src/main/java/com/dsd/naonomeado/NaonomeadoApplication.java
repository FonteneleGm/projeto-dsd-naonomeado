package com.dsd.naonomeado;

import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NaonomeadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaonomeadoApplication.class, args);
		Endpoint.publish("http://localhost:8080/conversoap", new ConversorService());
        System.out.println("Servidor 'Protex' rodando em http://localhost:8080/conversoap");
	}

}
