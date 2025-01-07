package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.util.GerenciadorDeTecla;
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

    ExpressoesPadroes calculadoraPadrao;

    @FXML
    private void pressionarApagar(){
        ControladorDeTeclas.aplicarTeclaApagarTudo(entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarApagarTudo(){
        ControladorDeTeclas.aplicarTeclaApagarTudo(entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarZero(){
        ControladorDeTeclas.aplicarTecla(zero.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarUm(){
        ControladorDeTeclas.aplicarTecla(um.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarDois(){
        ControladorDeTeclas.aplicarTecla(dois.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarTres(){
        ControladorDeTeclas.aplicarTecla(tres.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarQuatro(){
        ControladorDeTeclas.aplicarTecla(quatro.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarCinco(){
        ControladorDeTeclas.aplicarTecla(cinco.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarSeis(){
        ControladorDeTeclas.aplicarTecla(seis.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarSete(){
        ControladorDeTeclas.aplicarTecla(sete.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarOito(){
        ControladorDeTeclas.aplicarTecla(oito.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarNove(){
        ControladorDeTeclas.aplicarTecla(nove.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarSoma(){
        ControladorDeTeclas.aplicarTecla(soma.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarSubtracao(){
        ControladorDeTeclas.aplicarTecla(subtracao.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarMultiplicacao(){
        ControladorDeTeclas.aplicarTecla(multiplicacao.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarDivisao(){
        ControladorDeTeclas.aplicarTecla(divisao.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarIgual(){
        ControladorDeTeclas.aplicarTeclaIgual(entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarVirgula(){
        ControladorDeTeclas.aplicarTecla(virgula.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void pressionarPorcentagem(){
        ControladorDeTeclas.aplicarTecla(porcentagem.getText(), entrada, calculadoraPadrao);
    }

    @FXML
    private void trocarParaCalculadoraCientifica() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-cientifica");
    }

    @FXML
    public void initialize(){
        calculadoraPadrao = new ExpressoesPadroes(new StringBuilder());

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