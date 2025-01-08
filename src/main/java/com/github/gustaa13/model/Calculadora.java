package com.github.gustaa13.model;

import java.math.BigDecimal;
import java.math.MathContext;

import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public class Calculadora {
    static MathContext mc = new MathContext(16);
        
    public static BigDecimal soma(BigDecimal num1, BigDecimal num2){
        return num1.add(num2, mc);
    }

    public static BigDecimal subtracao(BigDecimal num1, BigDecimal num2){
        return num1.subtract(num2, mc);
    }

    public static BigDecimal multiplicacao(BigDecimal num1, BigDecimal num2){
        return num1.multiply(num2, mc);
    }

    public static BigDecimal divisao(BigDecimal num1, BigDecimal num2){
        if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();
        return num1.divide(num2, mc);
    }

    public static BigDecimal porcentagem(BigDecimal num1){
        return divisao(num1, new BigDecimal(100));
    }

    public static BigDecimal exponenciacao(Double num1, Double expoente){
        return new BigDecimal(Math.pow(num1, expoente), mc);
    }

    public static BigDecimal radiciacaoQuadrada(BigDecimal num1){
        return num1.sqrt(mc);
    }

    public static BigDecimal valorAbsoluto(BigDecimal num1){
        return num1.abs(mc);
    }
}
