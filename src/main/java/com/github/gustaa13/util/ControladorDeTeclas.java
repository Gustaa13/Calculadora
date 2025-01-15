package com.github.gustaa13.util;

import com.github.gustaa13.model.calculators.CalculadorDeExpressaoCientifico;
import com.github.gustaa13.model.calculators.CalculadorDeExpressaoPadrao;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;
import com.github.gustaa13.util.inputHandlers.TratadorDeEntradaCientifico;
import com.github.gustaa13.util.inputHandlers.TratadorDeEntradaPadrao;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControladorDeTeclas {
    
    public static void focadorDeTexto(TextField entrada){
        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    public static void aplicarTecla(String caracter, TextField entrada, @SuppressWarnings("exports") TratadorDeEntradaPadrao expressaoDeEntrada){

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

    private static void aplicarResultado(TextField entrada, TratadorDeEntradaPadrao expressaoDeEntrada, CalculadorDeExpressaoPadrao calculador){
        
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

    public static void aplicarTeclaIgual(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradaPadrao expressaoDeEntrada){
        if(!expressaoDeEntrada.expressaoExiste()) return;
        CalculadorDeExpressaoPadrao calculador = new CalculadorDeExpressaoPadrao(expressaoDeEntrada.getExpressaoFinal().toString());

        aplicarResultado(entrada, expressaoDeEntrada, calculador);
    }

    public static void aplicarTeclaIgual(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradaCientifico expressaoDeEntrada){
        if(!expressaoDeEntrada.expressaoExiste()) return;
        CalculadorDeExpressaoCientifico calculador = new CalculadorDeExpressaoCientifico(expressaoDeEntrada.getExpressaoFinal().toString());

        aplicarResultado(entrada, expressaoDeEntrada, calculador);
    }

    public static void aplicarTeclaApagar(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradaPadrao expressaoDeEntrada){
        expressaoDeEntrada.apagarCaracterDaExpressao();
        entrada.setText(expressaoDeEntrada.getExpressao().length() > 0 ? FomatadorDeExpressao.formatar(expressaoDeEntrada.getExpressao().toString()) : "0");     

        focadorDeTexto(entrada);
    }

    public static void aplicarTeclaApagarTudo(TextField entrada, @SuppressWarnings("exports") TratadorDeEntradaPadrao expressaoDeEntrada){
        expressaoDeEntrada.apagarExpressao();;
        entrada.setText("0");
        
        focadorDeTexto(entrada);
    }

    public static void adicionarEventoAoPressionar(Button botao, KeyCode codigo_tecla) {
        adicionarEventoAoPressionar(botao, codigo_tecla, false);
    }

    public static void adicionarEventoAoPressionar(Button botao, KeyCode codigoTecla, boolean shiftativado){
        botao.getScene().addEventFilter(KeyEvent.KEY_PRESSED, evento -> {
            if (evento.getCode() == codigoTecla) {
                if (shiftativado && evento.isShiftDown()) {
                    botao.arm();
                    botao.fire();
                }else if(!shiftativado && !evento.isShiftDown()){
                    botao.arm();
                    botao.fire();
                }
            }
        });

        botao.getScene().addEventFilter(KeyEvent.KEY_RELEASED, evento -> {
            if (evento.getCode() == codigoTecla) {
                botao.disarm();
            }
        });
    }
}
