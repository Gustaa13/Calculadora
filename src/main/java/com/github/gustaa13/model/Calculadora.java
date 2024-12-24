package com.github.gustaa13.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {
    
    public static BigDecimal soma(BigDecimal num1, BigDecimal num2){
        return num1.add(num2);
    }

    public static BigDecimal subtracao(BigDecimal num1, BigDecimal num2){
        return num1.subtract(num2);
    }

    public static BigDecimal multiplicacao(BigDecimal num1, BigDecimal num2){
        return num1.multiply(num2);
    }

    public static BigDecimal divisao(BigDecimal num1, BigDecimal num2){
        BigDecimal resultadoBig = num1.divide(num2,3, RoundingMode.HALF_UP);

        String resultado = resultadoBig.toString();

        if(resultado.contains(".")){
            resultado = resultado.replaceAll("\\.?0*$", "");
        }

        return new BigDecimal(resultado);
    }
}
