package com.github.gustaa13.util.inputHandlers;

import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public abstract class TratadorDeEntradas{

    private static final String OPERADORES = "+-x÷^√";
    private static final String CARACTERESESPECIAIS = ",(";
    private Integer contadorDeAlgarismos;
    private boolean permitirVirgula; 
    private boolean permitirPorcentagem;
    private StringBuilder expressao;
    private Integer contadorDeParentesesAbertos;
    private Integer contadorDeParentesesFechados;

    protected static String getOperadores() {
        return OPERADORES;
    }

    protected Integer getContadorDeAlgarismos() {
        return contadorDeAlgarismos;
    }

    protected void setContadorDeAlgarismos(Integer contadorDeAlgarismos){
        if(contadorDeAlgarismos > 15 || contadorDeAlgarismos < 0) return;
        this.contadorDeAlgarismos = contadorDeAlgarismos;
    }

    protected Integer getContadorDeParentesesAbertos(){
        return contadorDeParentesesAbertos;
    }

    protected void setContadorDeParentesesAbertos(Integer contadorDeParentesesAbertos){
        this.contadorDeParentesesAbertos = contadorDeParentesesAbertos;
    }

    protected Integer getContadorDeParentesesFechados(){
        return contadorDeParentesesFechados;
    }

    protected void setContadorDeParentesesFechados(Integer contadorDeParentesesFechados){
        this.contadorDeParentesesFechados = contadorDeParentesesFechados;
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

    public StringBuilder getExpressaoFinal() {
        if(contadorDeParentesesAbertos > contadorDeParentesesFechados){
            for(int i = 0; i < contadorDeParentesesAbertos - contadorDeParentesesFechados; i++){
                expressao.append(")");
            }
        }
        return expressao;
    }

    public void setExpressao(StringBuilder expressao) {
        this.expressao = expressao;
    }

    public TratadorDeEntradas(

        StringBuilder expressao

        ){
        this.contadorDeAlgarismos = 0;
        this.contadorDeParentesesAbertos = 0;
        this.contadorDeParentesesFechados = 0;
        this.permitirPorcentagem = true;
        this.permitirVirgula = true;

        this.expressao = expressao;

    }

    public abstract void adicionarCaracterNaExpressao(String caractere);

    protected boolean podeAdicionarCaracter(){
        return expressao.length() > 0 && !(OPERADORES + CARACTERESESPECIAIS).contains(String.valueOf(expressao.charAt(expressao.length() - 1)));
    }

    protected int posicaoDoUltimoNumero(){
        int index = 0;

        for(int i = expressao.length() - 1; i >= 0; i--){
            if(String.valueOf(expressao.charAt(i)).matches("[+\\-x÷(]")){
                index = i + 1;
                i = -1;
            }
        }
        
        return index;
    }

    protected void erroDivisaoPorZero(){
        if(numeroAnteriorIgualaZero() && posicaoDoUltimoNumero() > 0 && expressao.charAt(posicaoDoUltimoNumero() - 1) == '÷'){
            throw new DivisaoPorZeroException();
        }
    }

    protected boolean numeroAnteriorIgualaZero(){
        String ultimoNumero = expressao.substring(posicaoDoUltimoNumero());

        if(!ultimoNumero.matches(".*[1-9].*")) return true;
        else return false;
    }

    public boolean expressaoExiste(){
        if(expressao.length() > 0) return true;
        else return false;
    }

    public void concluirExpressao(){
        try {
            erroDivisaoPorZero();
            contadorDeAlgarismos = 0;
            contadorDeParentesesAbertos = 0;
            contadorDeParentesesFechados = 0;
            permitirPorcentagem = true;
            permitirVirgula = true;
            expressao.setLength(0);
        } catch (DivisaoPorZeroException e){
            throw new DivisaoPorZeroException();
        }
    }

    public void apagarExpressao(){
        contadorDeAlgarismos = 0;
        contadorDeParentesesAbertos = 0;
        contadorDeParentesesFechados = 0;
        permitirVirgula = true;
        permitirPorcentagem = true;
        expressao.setLength(0);
    }

    public void apagarCaractereDaExpresssao(){
        if(expressao.length() <= 0) return; 
        if(Character.isDigit(expressao.charAt(expressao.length() - 1)) && contadorDeAlgarismos > 0) contadorDeAlgarismos = expressao.length() - 1 - posicaoDoUltimoNumero();
        if(expressao.charAt(expressao.length() - 1) == ',') permitirVirgula = true;
        if(expressao.charAt(expressao.length() - 1) == '%') permitirPorcentagem = true;
        if(expressao.charAt(expressao.length() - 1) == '('){
            contadorDeParentesesAbertos--;
            if(expressao.charAt(expressao.length() - 2) == 's'){
                expressao.delete(expressao.length() - 4, expressao.length() - 1);
            }
        } 
        if(expressao.charAt(expressao.length() - 1) == ')') contadorDeParentesesFechados--;

        expressao.deleteCharAt(expressao.length() - 1);
    }
}
