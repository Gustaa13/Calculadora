package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.util.GerenciadorDeTecla;
import com.github.gustaa13.util.inputHandlers.ExpressoesCientificas;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ControladorInterfaceCientico {

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
    private Button valorAbsoluto;

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

    ExpressoesCientificas calculadoraCientifica;

    @FXML
    void pressionarApagar(){
        ControladorDeTeclas.aplicarTeclaApagar(entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarApagarTudo(){
        ControladorDeTeclas.aplicarTeclaApagarTudo(entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarCinco(){
        ControladorDeTeclas.aplicarTecla(cinco.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarDivisao(){
        ControladorDeTeclas.aplicarTecla(divisao.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarDois(){
        ControladorDeTeclas.aplicarTecla(dois.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarExponenciacao(){
        ControladorDeTeclas.aplicarTecla(exponenciacao.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarIgual(){
        ControladorDeTeclas.aplicarTeclaIgual(entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarMaisOUmenos(){
        ControladorDeTeclas.aplicarTecla(maisOUmenos.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarValorAbsoluto(){
        ControladorDeTeclas.aplicarTecla(valorAbsoluto.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarMultiplicacao(){
        ControladorDeTeclas.aplicarTecla(multiplicacao.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarNove(){
        ControladorDeTeclas.aplicarTecla(nove.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarOito(){
        ControladorDeTeclas.aplicarTecla(oito.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarParenteses(){
        ControladorDeTeclas.aplicarTecla(parenteses.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarPorcentagem(){
        ControladorDeTeclas.aplicarTecla(porcentagem.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarQuatro(){
        ControladorDeTeclas.aplicarTecla(quatro.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarRadiciacao(){
        ControladorDeTeclas.aplicarTecla(radiciacao.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarSeis(){
        ControladorDeTeclas.aplicarTecla(seis.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarSete(){
        ControladorDeTeclas.aplicarTecla(sete.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarSoma(){
        ControladorDeTeclas.aplicarTecla(soma.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarSubtracao(){
        ControladorDeTeclas.aplicarTecla(subtracao.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarTres(){
        ControladorDeTeclas.aplicarTecla(tres.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarUm(){
        ControladorDeTeclas.aplicarTecla(um.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarVirgula(){
        ControladorDeTeclas.aplicarTecla(virgula.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    void pressionarZero(ActionEvent event){
        ControladorDeTeclas.aplicarTecla(zero.getText(), entrada, calculadoraCientifica);
    }

    @FXML
    private void trocarParaCalculadoraPadrao() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-padrao");
    }

    @FXML
    public void initialize(){
        calculadoraCientifica = new ExpressoesCientificas(new StringBuilder());

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

            GerenciadorDeTecla.adicionarEventoAoPressionar(parenteses, KeyCode.DIGIT9, true);
            GerenciadorDeTecla.adicionarEventoAoPressionar(parenteses, KeyCode.DIGIT0, true);
        });
    }
}
