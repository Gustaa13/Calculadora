package com.github.gustaa13.util.inputHandlers;

import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public class TratadorDeEntradaPadrao{

    private String operadores;
    private String caracteresespeciais;
    private Integer contadorDeAlgarismos;
    private boolean permitirVirgula; 
    private boolean permitirPorcentagem;
    private StringBuilder expressao;

    protected TratadorDeEntradaPadrao(StringBuilder expressao, String operadores, String caracteresespeciais){
        contadorDeAlgarismos = 0;
        permitirPorcentagem = true;
        permitirVirgula = true;

        this.expressao = expressao;
        this.operadores = operadores;
        this.caracteresespeciais = caracteresespeciais;
    }

    public TratadorDeEntradaPadrao(StringBuilder expressao){
        contadorDeAlgarismos = 0;
        permitirPorcentagem = true;
        permitirVirgula = true;

        this.expressao = expressao;
        operadores = "+-x÷";
        caracteresespeciais = ",";
    }

    protected Integer getContadorDeAlgarismos() {
        return contadorDeAlgarismos;
    }

    protected void setContadorDeAlgarismos(Integer contadorDeAlgarismos){
        if(contadorDeAlgarismos > 15 || contadorDeAlgarismos < 0) return;
        this.contadorDeAlgarismos = contadorDeAlgarismos;
    }

    protected boolean getPermitirVirgula() {
        return permitirVirgula;
    }

    protected void setPermitirVirgula(boolean permitirVirgula) {
        this.permitirVirgula = permitirVirgula;
    }

    protected boolean getPermitirPorcentagem() {
        return permitirPorcentagem;
    }

    protected void setPermitirPorcentagem(boolean permitirPorcentagem) {
        this.permitirPorcentagem = permitirPorcentagem;
    }

    public StringBuilder getExpressao() {
        return expressao;
    }

    public void setExpressao(StringBuilder expressao) {
        this.expressao = expressao;
    }

    public StringBuilder getExpressaoFinal() {
        return getExpressao();
    }

    public boolean expressaoExiste(){
        if(expressao.length() > 0) return true;
        else return false;
    }

    public void apagarExpressao(){
        contadorDeAlgarismos = 0;
        permitirVirgula = true;
        permitirPorcentagem = true;
        expressao.setLength(0);
    }

    protected void tratadorDeVirgula(String caractere){
        if(getPermitirVirgula() && getContadorDeAlgarismos() == 0){
            getExpressao().append("0" + caractere);
            setPermitirVirgula(false);
            setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
        }else if(getPermitirVirgula() && getContadorDeAlgarismos() < 15){
            getExpressao().append(caractere);
            setPermitirVirgula(false);
        }
    }

    protected void tratadorDeOperadorPorcentagem(String caractere){
        if(getExpressao().length() > 0 && getPermitirPorcentagem() && getContadorDeAlgarismos() > 0){
            getExpressao().append(caractere);
            setPermitirVirgula(true);
            setContadorDeAlgarismos(0);
            setPermitirPorcentagem(false);
        }
    }

    protected boolean numeroAnteriorIgualaZero(){
        String ultimoNumero = expressao.substring(posicaoDoUltimoNumero());

        if(!ultimoNumero.matches(".*[1-9].*")) return true;
        else return false;
    }

    protected boolean podeAdicionarOperador(){
        return getExpressao().length() > 0 && !(operadores + caracteresespeciais).contains(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)));
    }

    protected void erroDivisaoPorZero(){
        if(numeroAnteriorIgualaZero() && posicaoDoUltimoNumero() > 0 && getExpressao().charAt(posicaoDoUltimoNumero() - 1) == '÷'){
            throw new DivisaoPorZeroException();
        }
    }

    public void concluirExpressao(){

        try {
            erroDivisaoPorZero();
        } catch (DivisaoPorZeroException e){
            throw new DivisaoPorZeroException();
        }

        apagarExpressao();
    }

    protected void tratadorNumerico(String caractere){
        if(getContadorDeAlgarismos() >= 15) return;
        if(caractere.equals("0") && !expressaoExiste()) return;
        if(numeroAnteriorIgualaZero() && caractere.equals("0") && getContadorDeAlgarismos() > 0 && getPermitirVirgula()) return;
                
        if(getExpressao().length() > 0 && (getExpressao().charAt(getExpressao().length() - 1) == '%')){
            getExpressao().append("x" + caractere);
            setPermitirPorcentagem(true);
            setPermitirVirgula(true);
            setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
        }else{
            getExpressao().append(caractere);
        }

        setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
    }

    protected int posicaoDoUltimoNumero(){
        int index = 0;

        for(int i = getExpressao().length() - 1; i >= 0; i--){
            if(operadores.contains(String.valueOf(getExpressao().charAt(i)))){
                index = i + 1;
                i = -1;
            }
        }
        
        return index;
    }

    public void apagarCaracterDaExpressao(){
        if(getExpressao().length() <= 0) return; 
        if(Character.isDigit(getExpressao().charAt(getExpressao().length() - 1)) && getContadorDeAlgarismos() > 0) setContadorDeAlgarismos(getExpressao().length() - 1 - posicaoDoUltimoNumero());
        if(getExpressao().charAt(getExpressao().length() - 1) == ',') setPermitirVirgula(true);
        if(getExpressao().charAt(getExpressao().length() - 1) == '%') setPermitirPorcentagem(true);

        getExpressao().deleteCharAt(getExpressao().length() - 1);
    }

    public void tratadorDeOperadorPadrao(String caractere){
        if(!podeAdicionarOperador()) return;

        try {
            erroDivisaoPorZero();  
        } catch (DivisaoPorZeroException e) {
            throw new DivisaoPorZeroException();
        }

        getExpressao().append(caractere);
        setPermitirVirgula(true);
        setContadorDeAlgarismos(0);
        setPermitirPorcentagem(true);
    }

    public void adicionarCaracterNaExpressao(String caractere){
        if(getExpressao().length() >= 100) return;
        
        if(caractere.matches("[0-9]")){ 
            tratadorNumerico(caractere);
        }else if(caractere.equals(",")){
            tratadorDeVirgula(caractere);
        }else if(caractere.matches("[+\\-x÷]")){ 
            tratadorDeOperadorPadrao(caractere);
        }else if(caractere.equals("%")){
            tratadorDeOperadorPorcentagem(caractere);
        }

    }
}
