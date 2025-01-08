package com.github.gustaa13.model.interpreters;

import java.util.ArrayList;
import java.util.List;

public abstract class InterpretadorCalculadora {
    private String expressao;
    private List<String> expressaoSeparada = new ArrayList<>();
    private String resultado;
    private String operadores;

    public List<String> getExpressaoSeparada() {
        return expressaoSeparada;
    }

    public void setExpressaoSeparada(List<String> expressaoSeparada) {
        this.expressaoSeparada = expressaoSeparada;
    }

    public String getOperadores() {
        return operadores;
    }

    public void setOperadores(String operadores) {
        this.operadores = operadores;
    }

    public InterpretadorCalculadora(String expressao, String operadores) {
        this.expressao = expressao;
        this.operadores = operadores;
        this.expressaoSeparada = separadorDeExpressao();
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

    public abstract void calcularResultadoTotal();

    private List<String> separadorDeExpressao(){
        StringBuilder numero = new StringBuilder();

        for(char caracter : expressao.toCharArray()){
            if(Character.isDigit(caracter)){
                numero.append(caracter);
            }else if(caracter == ','){
                numero.append("."); 
            }else if(operadores.indexOf(caracter) != -1){
                if(numero.length() > 0){
                    expressaoSeparada.add(numero.toString());
                    numero.setLength(0);
                }
                expressaoSeparada.add(String.valueOf(caracter));
            }
        }

        if(numero.length() > 0){
            expressaoSeparada.add(numero.toString());
        }else{
            if(expressaoSeparada.get(expressaoSeparada.size() - 1).matches("[x÷]")){
                expressaoSeparada.add("1");
            }else if(expressaoSeparada.get(expressaoSeparada.size() - 1).matches("[)]")){
                return expressaoSeparada;
            }
        }

        return expressaoSeparada;
    }
}
