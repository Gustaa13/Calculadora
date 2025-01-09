package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.util.ControladorDeTeclas;
import com.github.gustaa13.util.inputHandlers.ExpressoesPadroes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ControladorInterfacePadrao {

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

    ExpressoesPadroes expressaoPadrao;

    @FXML
    private void pressionarApagar(){
        ControladorDeTeclas.aplicarTeclaApagar(entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarApagarTudo(){
        ControladorDeTeclas.aplicarTeclaApagarTudo(entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarZero(){
        ControladorDeTeclas.aplicarTecla(zero.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarUm(){
        ControladorDeTeclas.aplicarTecla(um.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarDois(){
        ControladorDeTeclas.aplicarTecla(dois.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarTres(){
        ControladorDeTeclas.aplicarTecla(tres.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarQuatro(){
        ControladorDeTeclas.aplicarTecla(quatro.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarCinco(){
        ControladorDeTeclas.aplicarTecla(cinco.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarSeis(){
        ControladorDeTeclas.aplicarTecla(seis.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarSete(){
        ControladorDeTeclas.aplicarTecla(sete.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarOito(){
        ControladorDeTeclas.aplicarTecla(oito.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarNove(){
        ControladorDeTeclas.aplicarTecla(nove.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarSoma(){
        ControladorDeTeclas.aplicarTecla(soma.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarSubtracao(){
        ControladorDeTeclas.aplicarTecla(subtracao.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarMultiplicacao(){
        ControladorDeTeclas.aplicarTecla(multiplicacao.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarDivisao(){
        ControladorDeTeclas.aplicarTecla(divisao.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarIgual(){
        ControladorDeTeclas.aplicarTeclaIgual(entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarVirgula(){
        ControladorDeTeclas.aplicarTecla(virgula.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void pressionarPorcentagem(){
        ControladorDeTeclas.aplicarTecla(porcentagem.getText(), entrada, expressaoPadrao);
    }

    @FXML
    private void trocarParaCalculadoraCientifica() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-cientifica");
    }

    @FXML
    public void initialize(){
        expressaoPadrao = new ExpressoesPadroes(new StringBuilder());

        Platform.runLater(() -> {
            entrada.getParent().requestFocus();
            
            ControladorDeTeclas.adicionarEventoAoPressionar(apagar, KeyCode.BACK_SPACE);

            ControladorDeTeclas.adicionarEventoAoPressionar(apagarTudo, KeyCode.DELETE);

            ControladorDeTeclas.adicionarEventoAoPressionar(zero, KeyCode.DIGIT0);
            ControladorDeTeclas.adicionarEventoAoPressionar(zero, KeyCode.NUMPAD0);

            ControladorDeTeclas.adicionarEventoAoPressionar(um, KeyCode.DIGIT1);
            ControladorDeTeclas.adicionarEventoAoPressionar(um, KeyCode.NUMPAD1);

            ControladorDeTeclas.adicionarEventoAoPressionar(dois, KeyCode.DIGIT2);
            ControladorDeTeclas.adicionarEventoAoPressionar(dois, KeyCode.NUMPAD2);

            ControladorDeTeclas.adicionarEventoAoPressionar(tres, KeyCode.DIGIT3);
            ControladorDeTeclas.adicionarEventoAoPressionar(tres, KeyCode.NUMPAD3);

            ControladorDeTeclas.adicionarEventoAoPressionar(quatro, KeyCode.DIGIT4);
            ControladorDeTeclas.adicionarEventoAoPressionar(quatro, KeyCode.NUMPAD4);

            ControladorDeTeclas.adicionarEventoAoPressionar(cinco, KeyCode.DIGIT5);
            ControladorDeTeclas.adicionarEventoAoPressionar(cinco, KeyCode.NUMPAD5);

            ControladorDeTeclas.adicionarEventoAoPressionar(seis, KeyCode.DIGIT6);
            ControladorDeTeclas.adicionarEventoAoPressionar(seis, KeyCode.NUMPAD6);

            ControladorDeTeclas.adicionarEventoAoPressionar(sete, KeyCode.DIGIT7);
            ControladorDeTeclas.adicionarEventoAoPressionar(sete, KeyCode.NUMPAD7); 

            ControladorDeTeclas.adicionarEventoAoPressionar(oito, KeyCode.DIGIT8);
            ControladorDeTeclas.adicionarEventoAoPressionar(oito, KeyCode.NUMPAD8);

            ControladorDeTeclas.adicionarEventoAoPressionar(nove, KeyCode.DIGIT9);
            ControladorDeTeclas.adicionarEventoAoPressionar(nove, KeyCode.NUMPAD9);

            ControladorDeTeclas.adicionarEventoAoPressionar(soma, KeyCode.ADD);
            ControladorDeTeclas.adicionarEventoAoPressionar(soma, KeyCode.EQUALS, true);

            ControladorDeTeclas.adicionarEventoAoPressionar(subtracao, KeyCode.SUBTRACT);
            ControladorDeTeclas.adicionarEventoAoPressionar(subtracao, KeyCode.MINUS);

            ControladorDeTeclas.adicionarEventoAoPressionar(multiplicacao, KeyCode.MULTIPLY);
            ControladorDeTeclas.adicionarEventoAoPressionar(multiplicacao, KeyCode.DIGIT8, true);

            ControladorDeTeclas.adicionarEventoAoPressionar(divisao, KeyCode.DIVIDE);
            ControladorDeTeclas.adicionarEventoAoPressionar(divisao, KeyCode.SLASH);

            ControladorDeTeclas.adicionarEventoAoPressionar(igual, KeyCode.ENTER);
            ControladorDeTeclas.adicionarEventoAoPressionar(igual, KeyCode.EQUALS);

            ControladorDeTeclas.adicionarEventoAoPressionar(virgula, KeyCode.COMMA);
            ControladorDeTeclas.adicionarEventoAoPressionar(virgula, KeyCode.DECIMAL);

            ControladorDeTeclas.adicionarEventoAoPressionar(porcentagem, KeyCode.DIGIT5, true);
        });
    }
}