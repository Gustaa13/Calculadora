package com.github.gustaa13.controller;

import com.github.gustaa13.model.InterpretadorCalculadora;
import com.github.gustaa13.util.AlertaUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

    private static final String OPERADORES = "+-x÷%,";
    private Integer contadorDeAlgarismos;
    private boolean permitirVirgula; 

    private StringBuilder expressao = new StringBuilder();

    private boolean podeAdicionarCaracter(){

        return expressao.length() > 0 && !OPERADORES.contains(String.valueOf(expressao.charAt(expressao.length() - 1)));

    }

    private int indexDoUltimoNumero(){
        int index;

        if(permitirVirgula){
            index = expressao.length() - 1 - contadorDeAlgarismos;
        }else{
            index = expressao.length() - 2 - contadorDeAlgarismos;
        }

        return index;
    }

    private boolean numeroAnteriorIgualaZero(){
        String ultimoNumero = expressao.substring(Math.max(0, indexDoUltimoNumero()));
        
        if(!ultimoNumero.matches(".*[1-9].*")){
            return true;
        }else{
            return false;
        }
    }

    private boolean erroDivisaoPorZero(){
        if(numeroAnteriorIgualaZero() && expressao.charAt(indexDoUltimoNumero()) == '÷'){
            AlertaUtil erroDivisaoPorZero = new AlertaUtil(AlertType.ERROR, "Erro de Divisão por Zero", "Não é possível dividir por zero.", 2.0);

            erroDivisaoPorZero.janelaDeAlerta();

            return true;
        }else{
            return false;
        }
    }

    public void adicionarTexto(String caracter){

        if(expressao.length() >= 100){
            AlertaUtil alertaDeCaracteres = new AlertaUtil(AlertType.INFORMATION, "Alerta de caraceteres", "Não é possível inserir mais de 100 caracteres.", 1.5);

            alertaDeCaracteres.janelaDeAlerta();
            return;
        }

        if(caracter.matches("[0-9]")){ 
            if(contadorDeAlgarismos >= 15){
                AlertaUtil alertaDeAlgarismos = new AlertaUtil(AlertType.INFORMATION, "Alerta de dígitos", "Não é possível inserir mais de 15 dígitos.", 1.5);
    
                alertaDeAlgarismos.janelaDeAlerta();
            }else{
                expressao.append(caracter);
                contadorDeAlgarismos++;
            }
        }
        else if(caracter.equals(",")){
            if(permitirVirgula && contadorDeAlgarismos == 0){
                expressao.append("0,");
                permitirVirgula = false; 
                contadorDeAlgarismos ++;
            }else if(permitirVirgula){
                expressao.append(caracter);
                permitirVirgula = false; 
            }
        }
        else if(caracter.matches("[+\\-x÷]")){ 
            if(podeAdicionarCaracter() && !erroDivisaoPorZero()){    
                expressao.append(caracter);
                permitirVirgula = true; 
                contadorDeAlgarismos = 0;
            }
        }

        entrada.setText(expressao.toString());

        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagar(){
        if(expressao.length() > 0){

            if(Character.isDigit(expressao.charAt(expressao.length() - 1))) contadorDeAlgarismos--;
            if(expressao.charAt(expressao.length() - 1) == ',') permitirVirgula = true;
    
            expressao.deleteCharAt(expressao.length() - 1);
            entrada.setText(expressao.length() > 0 ? expressao.toString() : "0");     

            entrada.positionCaret(entrada.getLength());
        }
    }

    @FXML
    private void pressionarApagarTudo(){
        contadorDeAlgarismos = 0;
        permitirVirgula = true;
        expressao.setLength(0);
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

        if(erroDivisaoPorZero()) return;

        InterpretadorCalculadora interpretador = new InterpretadorCalculadora(expressao.toString());
        interpretador.calcularResultadoTotal();

        entrada.setText(interpretador.getResultado());
        
        contadorDeAlgarismos = 0;
        permitirVirgula = true;
        expressao.setLength(0);
        entrada.positionCaret(0);
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
        permitirVirgula = true;
        contadorDeAlgarismos = 0;

        Platform.runLater(() -> {
            entrada.getParent().requestFocus();
            
            adicionarEventoAoPressionarTecla(apagar, KeyCode.BACK_SPACE);

            adicionarEventoAoPressionarTecla(apagarTudo, KeyCode.DELETE);

            adicionarEventoAoPressionarTecla(zero, KeyCode.DIGIT0);
            adicionarEventoAoPressionarTecla(zero, KeyCode.NUMPAD0);

            adicionarEventoAoPressionarTecla(um, KeyCode.DIGIT1);
            adicionarEventoAoPressionarTecla(um, KeyCode.NUMPAD1);

            adicionarEventoAoPressionarTecla(dois, KeyCode.DIGIT2);
            adicionarEventoAoPressionarTecla(dois, KeyCode.NUMPAD2);

            adicionarEventoAoPressionarTecla(tres, KeyCode.DIGIT3);
            adicionarEventoAoPressionarTecla(tres, KeyCode.NUMPAD3);

            adicionarEventoAoPressionarTecla(quatro, KeyCode.DIGIT4);
            adicionarEventoAoPressionarTecla(quatro, KeyCode.NUMPAD4);

            adicionarEventoAoPressionarTecla(cinco, KeyCode.DIGIT5);
            adicionarEventoAoPressionarTecla(cinco, KeyCode.NUMPAD5);

            adicionarEventoAoPressionarTecla(seis, KeyCode.DIGIT6);
            adicionarEventoAoPressionarTecla(seis, KeyCode.NUMPAD6);

            adicionarEventoAoPressionarTecla(sete, KeyCode.DIGIT7);
            adicionarEventoAoPressionarTecla(sete, KeyCode.NUMPAD7); 

            adicionarEventoAoPressionarTecla(oito, KeyCode.DIGIT8);
            adicionarEventoAoPressionarTecla(oito, KeyCode.NUMPAD8);

            adicionarEventoAoPressionarTecla(nove, KeyCode.DIGIT9);
            adicionarEventoAoPressionarTecla(nove, KeyCode.NUMPAD9);

            adicionarEventoAoPressionarTecla(soma, KeyCode.ADD);
            adicionarEventoAoPressionarTecla(soma, KeyCode.EQUALS, true);

            adicionarEventoAoPressionarTecla(subtracao, KeyCode.SUBTRACT);
            adicionarEventoAoPressionarTecla(subtracao, KeyCode.MINUS);

            adicionarEventoAoPressionarTecla(multiplicacao, KeyCode.MULTIPLY);
            adicionarEventoAoPressionarTecla(multiplicacao, KeyCode.DIGIT8, true);

            adicionarEventoAoPressionarTecla(divisao, KeyCode.DIVIDE);
            adicionarEventoAoPressionarTecla(divisao, KeyCode.SLASH);

            adicionarEventoAoPressionarTecla(igual, KeyCode.ENTER);
            adicionarEventoAoPressionarTecla(igual, KeyCode.EQUALS);

            adicionarEventoAoPressionarTecla(virgula, KeyCode.COMMA);
            adicionarEventoAoPressionarTecla(virgula, KeyCode.DECIMAL);

            adicionarEventoAoPressionarTecla(porcentagem, KeyCode.DIGIT5, true);
        });
    }

    private void adicionarEventoAoPressionarTecla(Button botao, KeyCode codigo_tecla) {
        adicionarEventoAoPressionarTecla(botao, codigo_tecla, false);
    }

    private void adicionarEventoAoPressionarTecla(Button botao, KeyCode codigo_tecla, boolean shiftativado) {
        botao.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == codigo_tecla) {
                if (shiftativado && event.isShiftDown()) {
                    botao.arm();
                    botao.fire();
                    entrada.getParent().requestFocus(); 
                }else if(!shiftativado && !event.isShiftDown()){
                    botao.arm();
                    botao.fire();
                    entrada.getParent().requestFocus(); 
                }
            }
        });

        botao.getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == codigo_tecla) {
                botao.disarm();
            }
        });
    }
}

