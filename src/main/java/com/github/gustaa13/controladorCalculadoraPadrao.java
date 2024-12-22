package com.github.gustaa13;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class controladorCalculadoraPadrao {

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
    private boolean permitirVirgula;

    private StringBuilder expressao = new StringBuilder();

    private boolean podeAdicionarCaracter(){

        return expressao.length() > 0 && !OPERADORES.contains(String.valueOf(expressao.charAt(expressao.length() - 1)));

    }

    public void adicionarTexto(String texto){

        if(texto.matches("[0-9]")){ 
            expressao.append(texto);
        }
        else if(texto.equals(",")){
            if(podeAdicionarCaracter() && !expressao.toString().endsWith(",") && permitirVirgula){
                expressao.append(texto);
                permitirVirgula = false; 
            }
        }
        else if(texto.matches("[+\\-x÷]")){ 
            if(podeAdicionarCaracter()){
                expressao.append(texto);
                permitirVirgula = true; 
            }
        }

        entrada.setText(expressao.toString());

        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarApagar(){
        if (expressao.length() > 0) {
            expressao.deleteCharAt(expressao.length() - 1);
            entrada.setText(expressao.toString());

            entrada.positionCaret(entrada.getLength());
        }
    }

    @FXML
    private void pressionarApagarTudo(){
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
        entrada.setText("42");
        
        expressao.setLength(0);
        entrada.positionCaret(entrada.getLength());
    }

    @FXML
    private void pressionarVirgula(){
        adicionarTexto(virgula.getText());
    }

    @FXML
    public void initialize(){
        permitirVirgula = true;

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
            adicionarEventoAoPressionarTecla(oito, KeyCode.NUMPAD9);

            adicionarEventoAoPressionarTecla(nove, KeyCode.DIGIT9);
            adicionarEventoAoPressionarTecla(nove, KeyCode.NUMPAD9);

            adicionarEventoAoPressionarTecla(soma, KeyCode.ADD);

            adicionarEventoAoPressionarTecla(subtracao, KeyCode.SUBTRACT);

            adicionarEventoAoPressionarTecla(multiplicacao, KeyCode.MULTIPLY);

            adicionarEventoAoPressionarTecla(divisao, KeyCode.DIVIDE);

            adicionarEventoAoPressionarTecla(igual, KeyCode.ENTER);

            adicionarEventoAoPressionarTecla(virgula, KeyCode.COMMA);
            adicionarEventoAoPressionarTecla(virgula, KeyCode.DECIMAL);
        });
    }

    private void adicionarEventoAoPressionarTecla(Button botao, KeyCode codigo_tecla) {
        botao.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == codigo_tecla) {
                botao.arm();
                botao.fire();
                entrada.getParent().requestFocus(); 
            }
        });

        botao.getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == codigo_tecla) {
                botao.disarm();
            }
        });
    }
}

