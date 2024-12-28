package com.github.gustaa13.controller;

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

    public void adicionarTexto(String caracter){

        calculadoraPadrao.adicionarCaracterNaExpressao(caracter);

        entrada.setText(FomatadorDeExpressao.formatar(calculadoraPadrao.getExpressao().toString()));
        
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagar(){
        calculadoraPadrao.apagarCaractereDaExpresssao();
        entrada.setText(calculadoraPadrao.getExpressao().length() > 0 ? FomatadorDeExpressao.formatar(calculadoraPadrao.getExpressao().toString()) : "0");     

        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagarTudo(){
        calculadoraPadrao.apagarExpressao();
        entrada.setText("0");
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarZero(){
        adicionarTexto(zero.getText());
    }

    @FXML
    private void pressionarUm(){
        adicionarTexto(um.getText());
    }

    @FXML
    private void pressionarDois(){
        adicionarTexto(dois.getText());
    }

    @FXML
    private void pressionarTres(){
        adicionarTexto(tres.getText());
    }

    @FXML
    private void pressionarQuatro(){
        adicionarTexto(quatro.getText());
    }

    @FXML
    private void pressionarCinco(){
        adicionarTexto(cinco.getText());
    }

    @FXML
    private void pressionarSeis(){
        adicionarTexto(seis.getText());
    }

    @FXML
    private void pressionarSete(){
        adicionarTexto(sete.getText());
    }

    @FXML
    private void pressionarOito(){
        adicionarTexto(oito.getText());
    }

    @FXML
    private void pressionarNove(){
        adicionarTexto(nove.getText());
    }

    @FXML
    private void pressionarSoma(){
        adicionarTexto(soma.getText());
    }

    @FXML
    private void pressionarSubtracao(){
        adicionarTexto(subtracao.getText());
    }

    @FXML
    private void pressionarMultiplicacao(){
        adicionarTexto(multiplicacao.getText());
    }

    @FXML
    private void pressionarDivisao(){
        adicionarTexto(divisao.getText());
    }

    @FXML
    private void pressionarIgual(){
        InterpretadorCalculadora interpretador = new InterpretadorCalculadora(calculadoraPadrao.getExpressao().toString());

        if(calculadoraPadrao.concluirExpressao()){
            interpretador.calcularResultadoTotal();
    
            entrada.setText(interpretador.getResultado());
            
            entrada.positionCaret(0); 
        } 
    }

    @FXML
    private void pressionarVirgula(){
        adicionarTexto(virgula.getText());
    }

    @FXML
    private void pressionarPorcentagem(){
        adicionarTexto(porcentagem.getText());
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