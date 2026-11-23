package com.dsd.naonomeado;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService 
public class ConversorService {

    @WebMethod 
    public double celsiusFahrenheit(double c){
        if(c<-273.15){
            throw new IllegalArgumentException("Essa temperatura é abaixo do zero absoluto.");
        }
        return (c * 1.8) + 32;
    }

    @WebMethod 
    public double fahrenheitCelsius(double f){
        if(f<-459.67){
            throw new IllegalArgumentException("Essa temperatura é abaixo do zero absoluto.");
        }
        return (f - 32) / 1.8;
    }
}
