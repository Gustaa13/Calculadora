package com.github.gustaa13.util.inputHandlers;

import com.github.gustaa13.util.AlertaGeral;

import javafx.scene.control.Alert.AlertType;

public abstract class TratadorDeEntradas{

    private static final String OPERADORES = "+-x÷,";
    private Integer contadorDeAlgarismos;
    private boolean permitirVirgula; 
    private boolean permitirPorcentagem;
    private StringBuilder expressao;

    public static String getOperadores() {
        return OPERADORES;
    }

    public Integer getContadorDeAlgarismos() {
        return contadorDeAlgarismos;
    }

    public void setContadorDeAlgarismos(Integer contadorDeAlgarismos) {
        this.contadorDeAlgarismos = contadorDeAlgarismos;
    }

    public boolean getPermitirVirgula() {
        return permitirVirgula;
    }

    public void setPermitirVirgula(boolean permitirVirgula) {
        this.permitirVirgula = permitirVirgula;
    }

    public boolean getPermitirPorcentagem() {
        return permitirPorcentagem;
    }

    public void setPermitirPorcentagem(boolean permitirPorcentagem) {
        this.permitirPorcentagem = permitirPorcentagem;
    }

    public StringBuilder getExpressao() {
        return expressao;
    }

    public void setExpressao(StringBuilder expressao) {
        this.expressao = expressao;
    }

    public TratadorDeEntradas(

        Integer contadorDeAlgarismos, 
        boolean permitirVirgula, 
        boolean permitirPorcentagem, 
        StringBuilder expressao

        ){

        this.contadorDeAlgarismos = contadorDeAlgarismos;
        this.permitirVirgula = permitirVirgula;
        this.permitirPorcentagem = permitirPorcentagem;
        this.expressao = expressao;

    }

    public abstract void adicionarCaracterNaExpressao(String caractere);

    protected boolean podeAdicionarCaracter(){
        return expressao.length() > 0 && !OPERADORES.contains(String.valueOf(expressao.charAt(expressao.length() - 1)));
    }

    protected int PosicaoDoUltimoNumero(){
        int index;

        if(permitirVirgula){
            index = expressao.length() - 1 - contadorDeAlgarismos;
        }else{
            index = expressao.length() - 2 - contadorDeAlgarismos;
        }

        if(index > 0) {
            return index;
        }else{
            return 0;
        }
        
    }

    protected boolean erroDivisaoPorZero(){
        if(numeroAnteriorIgualaZero() && expressao.charAt(PosicaoDoUltimoNumero()) == '÷'){
            AlertaGeral erroDivisaoPorZero = new AlertaGeral(AlertType.ERROR, "Erro de Divisão por Zero", "Não é possível dividir por zero.", 2.0);

            erroDivisaoPorZero.janelaDeAlerta();

            return true;
        }else{
            return false;
        }
    }

    protected boolean numeroAnteriorIgualaZero(){
        String ultimoNumero = expressao.substring(Math.max(0, PosicaoDoUltimoNumero()));
        
        if(!ultimoNumero.matches(".*[1-9].*")){
            return true;
        }else{
            return false;
        }
    }

    public boolean expressaoExiste(){
        if(expressao.length() > 0) return true;
        else return false;
    }

    public boolean concluirExpressao(){
        if(erroDivisaoPorZero()) return false;

        contadorDeAlgarismos = 0;
        permitirPorcentagem = true;
        permitirVirgula = true;
        expressao.setLength(0);

        return true;
    }

    public void apagarExpressao(){
        contadorDeAlgarismos = 0;
        permitirVirgula = true;
        permitirPorcentagem = true;
        expressao.setLength(0);
    }

    public void apagarCaractereDaExpresssao(){
        if(expressao.length() <= 0) return; 
        if(Character.isDigit(expressao.charAt(expressao.length() - 1)) && contadorDeAlgarismos > 0) contadorDeAlgarismos--;
        if(expressao.charAt(expressao.length() - 1) == ',') permitirVirgula = true;
        if(expressao.charAt(expressao.length() - 1) == '%') permitirPorcentagem = true;

        expressao.deleteCharAt(expressao.length() - 1);
    }
}
