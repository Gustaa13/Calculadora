package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.model.InterpretadorCalculadora;
import com.github.gustaa13.util.GerenciadorDeTecla;
import com.github.gustaa13.util.inputHandlers.ExpressoesPadroes;
import com.github.gustaa13.util.FomatadorDeExpressao;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CalculadoraPadraoControlador {

    @FXML
    private Button apagar;

    @FXML
    private Button apagarTudo;

    @FXML
    private Button cinco;

    @FXML
    private Button divisao;

    @FXML
    private Button dois;

    @FXML
    private TextField entrada;

    @FXML
    private Button igual;

    @FXML
    private Button maisOUmenos;

    @FXML
    private Button multiplicacao;

    @FXML
    private Button nove;

    @FXML
    private Button oito;

    @FXML
    private Button porcentagem;

    @FXML
    private Button quatro;

    @FXML
    private Button seis;

    @FXML
    private Button sete;

    @FXML
    private Button soma;

    @FXML
    private Button subtracao;

    @FXML
    private Button tres;

    @FXML
    private Button um;

    @FXML
    private Button virgula;

    @FXML
    private Button zero;

    ExpressoesPadroes calculadoraPadrao;

    public void aplicarTecla(String caracter){

        calculadoraPadrao.adicionarCaracterNaExpressao(caracter);

        if(!calculadoraPadrao.expressaoExiste()){
            entrada.setText("0");
        }else{
            entrada.setText(FomatadorDeExpressao.formatar(calculadoraPadrao.getExpressao().toString()));
        }

        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagar(){
        calculadoraPadrao.apagarCaractereDaExpresssao();
        entrada.setText(calculadoraPadrao.getExpressao().length() > 0 ? FomatadorDeExpressao.formatar(calculadoraPadrao.getExpressao().toString()) : "0");     

        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagarTudo(){
        calculadoraPadrao.apagarExpressao();
        entrada.setText("0");
        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarZero(){
        aplicarTecla(zero.getText());
    }

    @FXML
    private void pressionarUm(){
        aplicarTecla(um.getText());
    }

    @FXML
    private void pressionarDois(){
        aplicarTecla(dois.getText());
    }

    @FXML
    private void pressionarTres(){
        aplicarTecla(tres.getText());
    }

    @FXML
    private void pressionarQuatro(){
        aplicarTecla(quatro.getText());
    }

    @FXML
    private void pressionarCinco(){
        aplicarTecla(cinco.getText());
    }

    @FXML
    private void pressionarSeis(){
        aplicarTecla(seis.getText());
    }

    @FXML
    private void pressionarSete(){
        aplicarTecla(sete.getText());
    }

    @FXML
    private void pressionarOito(){
        aplicarTecla(oito.getText());
    }

    @FXML
    private void pressionarNove(){
        aplicarTecla(nove.getText());
    }

    @FXML
    private void pressionarSoma(){
        aplicarTecla(soma.getText());
    }

    @FXML
    private void pressionarSubtracao(){
        aplicarTecla(subtracao.getText());
    }

    @FXML
    private void pressionarMultiplicacao(){
        aplicarTecla(multiplicacao.getText());
    }

    @FXML
    private void pressionarDivisao(){
        aplicarTecla(divisao.getText());
    }

    @FXML
    private void pressionarIgual(){
        if(!calculadoraPadrao.expressaoExiste()) return;
        InterpretadorCalculadora interpretador = new InterpretadorCalculadora(calculadoraPadrao.getExpressao().toString());

        if(calculadoraPadrao.concluirExpressao()){
            interpretador.calcularResultadoTotal();
    
            entrada.setText(FomatadorDeExpressao.formatar(interpretador.getResultado()));
            
            entrada.requestFocus();
            entrada.positionCaret(0); 
        } 
    }

    @FXML
    private void pressionarVirgula(){
        aplicarTecla(virgula.getText());
    }

    @FXML
    private void pressionarPorcentagem(){
        aplicarTecla(porcentagem.getText());
    }

    @FXML
    private void trocarParaCalculadoraCientifica() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-cientifica");
    }

    @FXML
    public void initialize(){
        calculadoraPadrao = new ExpressoesPadroes(0, true, true, new StringBuilder());

        Platform.runLater(() -> {
            entrada.getParent().requestFocus();
            
            GerenciadorDeTecla.adicionarEventoAoPressionar(apagar, KeyCode.BACK_SPACE);

            GerenciadorDeTecla.adicionarEventoAoPressionar(apagarTudo, KeyCode.DELETE);

            GerenciadorDeTecla.adicionarEventoAoPressionar(zero, KeyCode.DIGIT0);
            GerenciadorDeTecla.adicionarEventoAoPressionar(zero, KeyCode.NUMPAD0);

            GerenciadorDeTecla.adicionarEventoAoPressionar(um, KeyCode.DIGIT1);
            GerenciadorDeTecla.adicionarEventoAoPressionar(um, KeyCode.NUMPAD1);

            GerenciadorDeTecla.adicionarEventoAoPressionar(dois, KeyCode.DIGIT2);
            GerenciadorDeTecla.adicionarEventoAoPressionar(dois, KeyCode.NUMPAD2);

            GerenciadorDeTecla.adicionarEventoAoPressionar(tres, KeyCode.DIGIT3);
            GerenciadorDeTecla.adicionarEventoAoPressionar(tres, KeyCode.NUMPAD3);

            GerenciadorDeTecla.adicionarEventoAoPressionar(quatro, KeyCode.DIGIT4);
            GerenciadorDeTecla.adicionarEventoAoPressionar(quatro, KeyCode.NUMPAD4);

            GerenciadorDeTecla.adicionarEventoAoPressionar(cinco, KeyCode.DIGIT5);
            GerenciadorDeTecla.adicionarEventoAoPressionar(cinco, KeyCode.NUMPAD5);

            GerenciadorDeTecla.adicionarEventoAoPressionar(seis, KeyCode.DIGIT6);
            GerenciadorDeTecla.adicionarEventoAoPressionar(seis, KeyCode.NUMPAD6);

            GerenciadorDeTecla.adicionarEventoAoPressionar(sete, KeyCode.DIGIT7);
            GerenciadorDeTecla.adicionarEventoAoPressionar(sete, KeyCode.NUMPAD7); 

            GerenciadorDeTecla.adicionarEventoAoPressionar(oito, KeyCode.DIGIT8);
            GerenciadorDeTecla.adicionarEventoAoPressionar(oito, KeyCode.NUMPAD8);

            GerenciadorDeTecla.adicionarEventoAoPressionar(nove, KeyCode.DIGIT9);
            GerenciadorDeTecla.adicionarEventoAoPressionar(nove, KeyCode.NUMPAD9);

            GerenciadorDeTecla.adicionarEventoAoPressionar(soma, KeyCode.ADD);
            GerenciadorDeTecla.adicionarEventoAoPressionar(soma, KeyCode.EQUALS, true);

            GerenciadorDeTecla.adicionarEventoAoPressionar(subtracao, KeyCode.SUBTRACT);
            GerenciadorDeTecla.adicionarEventoAoPressionar(subtracao, KeyCode.MINUS);

            GerenciadorDeTecla.adicionarEventoAoPressionar(multiplicacao, KeyCode.MULTIPLY);
            GerenciadorDeTecla.adicionarEventoAoPressionar(multiplicacao, KeyCode.DIGIT8, true);

            GerenciadorDeTecla.adicionarEventoAoPressionar(divisao, KeyCode.DIVIDE);
            GerenciadorDeTecla.adicionarEventoAoPressionar(divisao, KeyCode.SLASH);

            GerenciadorDeTecla.adicionarEventoAoPressionar(igual, KeyCode.ENTER);
            GerenciadorDeTecla.adicionarEventoAoPressionar(igual, KeyCode.EQUALS);

            GerenciadorDeTecla.adicionarEventoAoPressionar(virgula, KeyCode.COMMA);
            GerenciadorDeTecla.adicionarEventoAoPressionar(virgula, KeyCode.DECIMAL);

            GerenciadorDeTecla.adicionarEventoAoPressionar(porcentagem, KeyCode.DIGIT5, true);
        });
    }
}