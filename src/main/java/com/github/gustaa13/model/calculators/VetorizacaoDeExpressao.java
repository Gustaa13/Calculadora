package com.github.gustaa13.model.calculators;

import java.util.ArrayList;
import java.util.List;

public class VetorizacaoDeExpressao {
    
    public static List<String> vetorizar(String expressao, String operadores){
        StringBuilder numero = new StringBuilder();
        List<String> expressaoVetorizada = new ArrayList<>();

        for(char caracter : expressao.toCharArray()){
            if(Character.isDigit(caracter)){
                numero.append(caracter);
            }else if(caracter == ','){
                numero.append("."); 
            }else if(operadores.indexOf(caracter) != -1){
                if(numero.length() > 0){
                    expressaoVetorizada.add(numero.toString());
                    numero.setLength(0);
                }
                expressaoVetorizada.add(String.valueOf(caracter));
            }
        }

        if(numero.length() > 0){
            expressaoVetorizada.add(numero.toString());
        }

        return expressaoVetorizada;
    }
}
