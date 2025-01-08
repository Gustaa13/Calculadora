package com.github.gustaa13.controller;

import com.github.gustaa13.model.calculators.CalculadorDeExpressaoCientifico;
import com.github.gustaa13.model.calculators.CalculadorDeExpressaoPadrao;
import com.github.gustaa13.util.FomatadorDeExpressao;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;
import com.github.gustaa13.util.inputHandlers.ExpressoesCientificas;
import com.github.gustaa13.util.inputHandlers.ExpressoesPadroes;
import com.github.gustaa13.util.inputHandlers.TratadorDeEntradas;

import javafx.scene.control.TextField;

public class ControladorDeTeclas {
    
    public static void focadorDeTexto(TextField entrada){
        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    public static void aplicarTecla(String caracter, TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas expressaoDeEntrada){

        try {
            expressaoDeEntrada.adicionarCaracterNaExpressao(caracter);
        } catch (DivisaoPorZeroException e) {
            entrada.setText("Não é possível dividir por zero");
            entrada.positionCaret(entrada.getLength());
            return;
        }
        
        if(!expressaoDeEntrada.expressaoExiste()){
            entrada.setText("0");
        }else{
            entrada.setText(FomatadorDeExpressao.formatar(expressaoDeEntrada.getExpressao().toString()));
        }

        focadorDeTexto(entrada);
    }

    private static void aplicarResultado(TextField entrada, TratadorDeEntradas expressaoDeEntrada, CalculadorDeExpressaoPadrao calculador){
        try {
            expressaoDeEntrada.concluirExpressao();
            calculador.calcularResultadoTotal();

            entrada.setText(FomatadorDeExpressao.formatar(calculador.getResultado()));
        } catch (DivisaoPorZeroException e) {
            entrada.setText("Não é possível dividir por zero");
            entrada.positionCaret(entrada.getLength());
        }
        
        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaIgual(TextField entrada, @SuppressWarnings("exports") ExpressoesPadroes expressaoDeEntrada){
        if(!expressaoDeEntrada.expressaoExiste()) return;
        CalculadorDeExpressaoPadrao calculador = new CalculadorDeExpressaoPadrao(expressaoDeEntrada.getExpressaoFinal().toString());

        aplicarResultado(entrada, expressaoDeEntrada, calculador);
    }

    public static void aplicarTeclaIgual(TextField entrada, @SuppressWarnings("exports") ExpressoesCientificas expressaoDeEntrada){
        if(!expressaoDeEntrada.expressaoExiste()) return;
        CalculadorDeExpressaoCientifico calculador = new CalculadorDeExpressaoCientifico(expressaoDeEntrada.getExpressaoFinal().toString());

        aplicarResultado(entrada, expressaoDeEntrada, calculador);
    }

    public static void aplicarTeclaApagar(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas expressaoDeEntrada){
        expressaoDeEntrada.apagarCaractereDaExpresssao();
        entrada.setText(expressaoDeEntrada.getExpressao().length() > 0 ? FomatadorDeExpressao.formatar(expressaoDeEntrada.getExpressao().toString()) : "0");     

        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaApagarTudo(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradas expressaoDeEntrada){
        expressaoDeEntrada.apagarExpressao();
        entrada.setText("0");
        
        focadorDeTexto(entrada);
    }
}
