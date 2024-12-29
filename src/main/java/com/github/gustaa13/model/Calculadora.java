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
        return num1.divide(num2,14, RoundingMode.HALF_UP);
    }

    public static BigDecimal porcentagem(BigDecimal num1){
        return divisao(num1, new BigDecimal(100));
    }
}
