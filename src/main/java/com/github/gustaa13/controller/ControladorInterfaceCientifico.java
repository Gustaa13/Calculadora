package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.util.ControladorDeTeclas;
import com.github.gustaa13.util.inputHandlers.ExpressoesCientificas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class ControladorInterfaceCientifico {

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

    ExpressoesCientificas expressaoCientifica;

    @FXML
    void pressionarApagar(){
        ControladorDeTeclas.aplicarTeclaApagar(entrada, expressaoCientifica);
    }

    @FXML
    void pressionarApagarTudo(){
        ControladorDeTeclas.aplicarTeclaApagarTudo(entrada, expressaoCientifica);
    }

    @FXML
    void pressionarCinco(){
        ControladorDeTeclas.aplicarTecla(cinco.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarDivisao(){
        ControladorDeTeclas.aplicarTecla(divisao.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarDois(){
        ControladorDeTeclas.aplicarTecla(dois.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarExponenciacao(){
        ControladorDeTeclas.aplicarTecla(exponenciacao.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarIgual(){
        ControladorDeTeclas.aplicarTeclaIgual(entrada, expressaoCientifica);
    }

    @FXML
    void pressionarMaisOUmenos(){
        ControladorDeTeclas.aplicarTecla(maisOUmenos.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarValorAbsoluto(){
        ControladorDeTeclas.aplicarTecla(valorAbsoluto.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarMultiplicacao(){
        ControladorDeTeclas.aplicarTecla(multiplicacao.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarNove(){
        ControladorDeTeclas.aplicarTecla(nove.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarOito(){
        ControladorDeTeclas.aplicarTecla(oito.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarParenteses(){
        ControladorDeTeclas.aplicarTecla(parenteses.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarPorcentagem(){
        ControladorDeTeclas.aplicarTecla(porcentagem.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarQuatro(){
        ControladorDeTeclas.aplicarTecla(quatro.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarRadiciacao(){
        ControladorDeTeclas.aplicarTecla(radiciacao.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarSeis(){
        ControladorDeTeclas.aplicarTecla(seis.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarSete(){
        ControladorDeTeclas.aplicarTecla(sete.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarSoma(){
        ControladorDeTeclas.aplicarTecla(soma.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarSubtracao(){
        ControladorDeTeclas.aplicarTecla(subtracao.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarTres(){
        ControladorDeTeclas.aplicarTecla(tres.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarUm(){
        ControladorDeTeclas.aplicarTecla(um.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarVirgula(){
        ControladorDeTeclas.aplicarTecla(virgula.getText(), entrada, expressaoCientifica);
    }

    @FXML
    void pressionarZero(){
        ControladorDeTeclas.aplicarTecla(zero.getText(), entrada, expressaoCientifica);
    }

    @FXML
    private void trocarParaCalculadoraPadrao() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-padrao");
    }

    @FXML
    public void initialize(){
        expressaoCientifica = new ExpressoesCientificas(new StringBuilder());

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

            ControladorDeTeclas.adicionarEventoAoPressionar(parenteses, KeyCode.DIGIT9, true);
            ControladorDeTeclas.adicionarEventoAoPressionar(parenteses, KeyCode.DIGIT0, true);
        });
    }
}
