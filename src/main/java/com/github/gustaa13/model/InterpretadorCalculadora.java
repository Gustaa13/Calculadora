package com.github.gustaa13.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InterpretadorCalculadora {
    private String expressao;
    private String resultado;

    public InterpretadorCalculadora(String expressao) {
        this.expressao = expressao;
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void calcularResultadoTotal(){
        List<String> partes = separadorDeExpressao();
        int index;

        while(partes.contains("%")){
            index = partes.indexOf("%");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            String valor = Calculadora.porcentagem(num1).toString();

            partes.set(index - 1, valor);

            partes.remove(index);
        }

        while(partes.contains("x")){

            index = partes.indexOf("x");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.multiplicacao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);
        }

        while(partes.contains("÷")){

            index = partes.indexOf("÷");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.divisao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);       
        }

        while(partes.contains("+")){

            index = partes.indexOf("+");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.soma(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);         
        }

        while(partes.contains("-")){

            index = partes.indexOf("-");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.subtracao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);       
        }

        if(partes.get(0).contains(".")){
            partes.set(0, partes.get(0).replaceAll("\\.?0*$", ""));
            partes.set(0, partes.get(0).replace(".", ","));
        }
        
        resultado = partes.get(0);
    }

    private List<String> separadorDeExpressao(){
        List<String> partes = new ArrayList<>();
        StringBuilder numero = new StringBuilder();

        for(char c : expressao.toCharArray()){
            if(Character.isDigit(c)){
                numero.append(c);
            }else if(c == ','){
                numero.append(".");
            }else if("+-x÷%".indexOf(c) != -1){
                if(numero.length() > 0){
                    partes.add(numero.toString());
                    numero.setLength(0);
                }
                partes.add(String.valueOf(c));
            }
        }

        if(numero.length() > 0){
            partes.add(numero.toString());
        }else{
            partes.add("0");
        }

        return partes;
    }
}
