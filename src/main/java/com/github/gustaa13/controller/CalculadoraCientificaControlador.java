package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculadoraCientificaControlador {

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
    private Button exponenciacao;

    @FXML
    private Button igual;

    @FXML
    private Button maisOUmenos;

    @FXML
    private Button modulo;

    @FXML
    private Button multiplicacao;

    @FXML
    private Button nove;

    @FXML
    private Button oito;

    @FXML
    private Button parenteses;

    @FXML
    private Button porcentagem;

    @FXML
    private Button quatro;

    @FXML
    private Button radiciacao;

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

    @FXML
    void pressionarApagar(ActionEvent event) {

    }

    @FXML
    void pressionarApagarTudo(ActionEvent event) {

    }

    @FXML
    void pressionarCinco(ActionEvent event) {

    }

    @FXML
    void pressionarDivisao(ActionEvent event) {

    }

    @FXML
    void pressionarDois(ActionEvent event) {

    }

    @FXML
    void pressionarExponenciacao(ActionEvent event) {

    }

    @FXML
    void pressionarIgual(ActionEvent event) {

    }

    @FXML
    void pressionarMaisOUmenos(ActionEvent event) {

    }

    @FXML
    void pressionarModulo(ActionEvent event) {

    }

    @FXML
    void pressionarMultiplicacao(ActionEvent event) {

    }

    @FXML
    void pressionarNove(ActionEvent event) {

    }

    @FXML
    void pressionarOito(ActionEvent event) {

    }

    @FXML
    void pressionarParenteses(ActionEvent event) {

    }

    @FXML
    void pressionarPorcentagem(ActionEvent event) {

    }

    @FXML
    void pressionarQuatro(ActionEvent event) {

    }

    @FXML
    void pressionarRadiciacao(ActionEvent event) {

    }

    @FXML
    void pressionarSeis(ActionEvent event) {

    }

    @FXML
    void pressionarSete(ActionEvent event) {

    }

    @FXML
    void pressionarSoma(ActionEvent event) {

    }

    @FXML
    void pressionarSubtracao(ActionEvent event) {

    }

    @FXML
    void pressionarTres(ActionEvent event) {

    }

    @FXML
    void pressionarUm(ActionEvent event) {

    }

    @FXML
    void pressionarVirgula(ActionEvent event) {

    }

    @FXML
    void pressionarZero(ActionEvent event) {

    }

    @FXML
    private void trocarParaCalculadoraPadrao() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-padrao");
    }

    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            entrada.getParent().requestFocus();
        });
    }
}
