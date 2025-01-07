package com.github.gustaa13.model.interpreters;

import java.util.ArrayList;
import java.util.List;

public abstract class InterpretadorCalculadora {
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

    public abstract void calcularResultadoTotal();

    protected List<String> separadorDeExpressao(){
        List<String> partesDaExpressao = new ArrayList<>();
        StringBuilder numero = new StringBuilder();

        for(char caracter : expressao.toCharArray()){
            if(Character.isDigit(caracter)){
                numero.append(caracter);
            }else if(caracter == ','){
                numero.append("."); 
            }else if("+-x÷%()^√a".indexOf(caracter) != -1 ){
                if(numero.length() > 0){
                    partesDaExpressao.add(numero.toString());
                    numero.setLength(0);
                }
                partesDaExpressao.add(String.valueOf(caracter));
            }
        }

        if(numero.length() > 0){
            partesDaExpressao.add(numero.toString());
        }else{
            if(partesDaExpressao.get(partesDaExpressao.size() - 1).matches("[x÷]")){
                partesDaExpressao.add("1");
            }else if(partesDaExpressao.get(partesDaExpressao.size() - 1).matches("[)]")){
                return partesDaExpressao;
            }else{
                partesDaExpressao.add("0");
            }
        }

        return partesDaExpressao;
    }
}
