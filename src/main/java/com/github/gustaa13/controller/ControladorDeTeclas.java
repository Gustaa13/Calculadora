package com.github.gustaa13.controller;

import com.github.gustaa13.model.interpreters.CalculadorDeExpressaoCientifico;
import com.github.gustaa13.util.FomatadorDeExpressao;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;
import com.github.gustaa13.util.inputHandlers.TratadorDeEntradas;

import javafx.scene.control.TextField;

public class ControladorDeTeclas {
    
    public static void focadorDeTexto(TextField entrada){
        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    public static void aplicarTecla(String caracter, TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas calculadora){

        try {
            calculadora.adicionarCaracterNaExpressao(caracter);
        } catch (DivisaoPorZeroException e) {
            entrada.setText("Não é possível dividir por zero");
            entrada.positionCaret(entrada.getLength());
            return;
        }
        
        if(!calculadora.expressaoExiste()){
            entrada.setText("0");
        }else{
            entrada.setText(FomatadorDeExpressao.formatar(calculadora.getExpressao().toString()));
        }

        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaIgual(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas calculadora){
        if(!calculadora.expressaoExiste()) return;
        CalculadorDeExpressaoCientifico interpretador = new CalculadorDeExpressaoCientifico(calculadora.getExpressaoFinal().toString());

        try {
            calculadora.concluirExpressao();
            interpretador.calcularResultadoTotal();

            entrada.setText(FomatadorDeExpressao.formatar(interpretador.getResultado()));
        } catch (DivisaoPorZeroException e) {
            entrada.setText("Não é possível dividir por zero");
            entrada.positionCaret(entrada.getLength());
        }
        
        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaApagar(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas calculadora){
        calculadora.apagarCaractereDaExpresssao();
        entrada.setText(calculadora.getExpressao().length() > 0 ? FomatadorDeExpressao.formatar(calculadora.getExpressao().toString()) : "0");     

        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaApagarTudo(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas calculadora){
        calculadora.apagarExpressao();
        entrada.setText("0");
        
        focadorDeTexto(entrada);
    }
}
