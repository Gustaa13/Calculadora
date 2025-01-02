package com.github.gustaa13.controller;

import java.io.IOException;

import com.github.gustaa13.application.CalculadoraApp;
import com.github.gustaa13.model.interpreters.InterpretadorCientifico;
import com.github.gustaa13.util.FomatadorDeExpressao;
import com.github.gustaa13.util.GerenciadorDeTecla;
import com.github.gustaa13.util.inputHandlers.ExpressoesCientificas;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

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

    public void aplicarTecla(String caracter){

        calculadoraCientifica.adicionarCaracterNaExpressao(caracter);

        if(!calculadoraCientifica.expressaoExiste()){
            entrada.setText("0");
        }else{
            entrada.setText(FomatadorDeExpressao.formatar(calculadoraCientifica.getExpressao().toString()));
        }

        entrada.requestFocus();
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    void pressionarApagar(){

    }

    @FXML
    void pressionarApagarTudo(){

    }

    @FXML
    void pressionarCinco(){
        aplicarTecla(cinco.getText());
    }

    @FXML
    void pressionarDivisao(){
        aplicarTecla(divisao.getText());
    }

    @FXML
    void pressionarDois(){
        aplicarTecla(dois.getText());
    }

    @FXML
    void pressionarExponenciacao(){

    }

    @FXML
    void pressionarIgual(){
        if(!calculadoraCientifica.expressaoExiste()) return;
        InterpretadorCientifico interpretador = new InterpretadorCientifico(calculadoraCientifica.getExpressao().toString());

        if(calculadoraCientifica.concluirExpressao()){
            interpretador.calcularResultadoTotal();
    
            entrada.setText(FomatadorDeExpressao.formatar(interpretador.getResultado()));
            
            entrada.requestFocus();
            entrada.positionCaret(0); 
        } 
    }

    @FXML
    void pressionarMaisOUmenos(){

    }

    @FXML
    void pressionarValorAbsoluto(){

    }

    @FXML
    void pressionarMultiplicacao(){
        aplicarTecla(multiplicacao.getText());
    }

    @FXML
    void pressionarNove(){
        aplicarTecla(nove.getText());
    }

    @FXML
    void pressionarOito(){
        aplicarTecla(oito.getText());
    }

    @FXML
    void pressionarParenteses(){

    }

    @FXML
    void pressionarPorcentagem(){
        aplicarTecla(porcentagem.getText());
    }

    @FXML
    void pressionarQuatro(){
        aplicarTecla(quatro.getText());
    }

    @FXML
    void pressionarRadiciacao(){
        aplicarTecla(radiciacao.getText());
    }

    @FXML
    void pressionarSeis(){
        aplicarTecla(seis.getText());
    }

    @FXML
    void pressionarSete(){
        aplicarTecla(sete.getText());
    }

    @FXML
    void pressionarSoma(){
        aplicarTecla(soma.getText());
    }

    @FXML
    void pressionarSubtracao(){
        aplicarTecla(subtracao.getText());
    }

    @FXML
    void pressionarTres(){
        aplicarTecla(tres.getText());
    }

    @FXML
    void pressionarUm(){
        aplicarTecla(um.getText());
    }

    @FXML
    void pressionarVirgula(){
        aplicarTecla(virgula.getText());
    }

    @FXML
    void pressionarZero(ActionEvent event){
        aplicarTecla(zero.getText());
    }

    @FXML
    private void trocarParaCalculadoraPadrao() throws IOException{
        CalculadoraApp.setRoot("/com/github/gustaa13/calculadora-padrao");
    }

    @FXML
    public void initialize(){
        calculadoraCientifica = new ExpressoesCientificas(0, true, true, new StringBuilder());

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
