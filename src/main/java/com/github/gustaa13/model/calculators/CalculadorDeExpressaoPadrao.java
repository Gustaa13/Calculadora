package com.github.gustaa13.model.calculators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;
import com.github.gustaa13.util.exceptions.PosicaoNaoExisteException;

public class CalculadorDeExpressaoPadrao{

    private String expressao;
    private List<String> expressaoVetorizada;
    private String resultado;
    private String operadores;
    private int posicaoOperador;
    private BigDecimal numeroPosterior;
    private BigDecimal numeroAntecessor;

    public int getPosicaoOperador() {
        return posicaoOperador;
    }

    public void setPosicaoOperador(int posicaoOperador) {
        this.posicaoOperador = posicaoOperador;
    }

    public BigDecimal getNumeroPosterior() {
        return numeroPosterior;
    }

    public void setNumeroPosterior(BigDecimal numeroPosterior) {
        this.numeroPosterior = numeroPosterior;
    }

    public BigDecimal getNumeroAntecessor() {
        return numeroAntecessor;
    }

    public void setNumeroAntecessor(BigDecimal numeroAntecessor) {
        this.numeroAntecessor = numeroAntecessor;
    }

    public List<String> getExpressaoVetorizada() {
        return expressaoVetorizada;
    }

    public void setExpressaoVetorizada(List<String> expressaoVetorizada) {
        this.expressaoVetorizada = expressaoVetorizada;
    }

    public CalculadorDeExpressaoPadrao(String expressao) {
        this.expressao = expressao;
        operadores = "+-x÷%";
        
        expressaoVetorizada = new ArrayList<>();
        expressaoVetorizada = VetorizacaoDeExpressao.vetorizar(expressao, operadores);
        numeroPosterior = new BigDecimal(0);
        numeroAntecessor = new BigDecimal(0);
    }

    protected CalculadorDeExpressaoPadrao(String expressao, String operadores) {
        this.expressao = expressao;
        this.operadores = operadores;
        
        expressaoVetorizada = new ArrayList<>();
        expressaoVetorizada = VetorizacaoDeExpressao.vetorizar(expressao, operadores);
        numeroPosterior = new BigDecimal(0);
        numeroAntecessor = new BigDecimal(0);
    }

    public String getExpressao() {
        return expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    protected void obterPosicaoOperador(String operador){
        posicaoOperador = expressaoVetorizada.indexOf(operador);
    }

    protected void obterNumeroAntecessorAoOperador(String operador){
        obterPosicaoOperador(operador);

        if(posicaoOperador - 1 < 0) throw new PosicaoNaoExisteException();
        
        numeroAntecessor = new BigDecimal(expressaoVetorizada.get(posicaoOperador - 1));
    }

    protected void obterNumeroPosteriorAoOperador(String operador){
        obterPosicaoOperador(operador);

        if(posicaoOperador + 1 > expressaoVetorizada.size() - 1) throw new PosicaoNaoExisteException();

        numeroPosterior = new BigDecimal(expressaoVetorizada.get(posicaoOperador + 1));
    }

    public void calcularPorcentagens(String operador){
        while(expressaoVetorizada.contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add(posicaoOperador, "0");
                obterNumeroAntecessorAoOperador(operador);
            }
            
            String valor = Calculadora.porcentagem(numeroAntecessor).toString();

            expressaoVetorizada.set(posicaoOperador - 1, valor);

            expressaoVetorizada.remove(posicaoOperador);
        }
    }

    public void calcularMultiplicacoes(String operador){
        while(expressaoVetorizada.contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add(posicaoOperador, "1");
                obterNumeroAntecessorAoOperador(operador);
            }
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add("1");
                obterNumeroPosteriorAoOperador(operador);
            }
            
            String valor = Calculadora.multiplicacao(numeroAntecessor, numeroPosterior).toString();

            expressaoVetorizada.set(posicaoOperador - 1, valor);
            
            expressaoVetorizada.remove(posicaoOperador);
            expressaoVetorizada.remove(posicaoOperador);
        }
    }

    public void calcularDivisoes(String operador){
        while(expressaoVetorizada.contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add(posicaoOperador, "0");
                obterNumeroAntecessorAoOperador(operador);
            }
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add("1");
                obterNumeroPosteriorAoOperador(operador);
            }

            String valor;

            try {
                valor = Calculadora.divisao(numeroAntecessor, numeroPosterior).toString();
            } catch(DivisaoPorZeroException e){
                throw new DivisaoPorZeroException();
            }
            
            expressaoVetorizada.set(posicaoOperador - 1, valor);
            
            expressaoVetorizada.remove(posicaoOperador);
            expressaoVetorizada.remove(posicaoOperador);       
        }
    }

    public void calcularSomas(String operador){
        while(expressaoVetorizada.contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add(posicaoOperador, "0");
                obterNumeroAntecessorAoOperador(operador);
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add("0");
                obterNumeroPosteriorAoOperador(operador);
            }

            String valor = Calculadora.soma(numeroAntecessor, numeroPosterior).toString();

            expressaoVetorizada.set(posicaoOperador - 1, valor);
            
            expressaoVetorizada.remove(posicaoOperador);
            expressaoVetorizada.remove(posicaoOperador);         
        }
    }

    public void calcularSubtracoes(String operador){
        while(expressaoVetorizada.contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add(posicaoOperador, "0");
                obterNumeroAntecessorAoOperador(operador);
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                expressaoVetorizada.add("0");
                obterNumeroPosteriorAoOperador(operador);
            }

            String valor = Calculadora.subtracao(numeroAntecessor, numeroPosterior).toString();

            expressaoVetorizada.set(posicaoOperador - 1, valor);
            
            expressaoVetorizada.remove(posicaoOperador);
            expressaoVetorizada.remove(posicaoOperador);       
        }
    }

    public void tratadorDeExpressao(){
        if(expressaoVetorizada.get(0).contains(".")){
            expressaoVetorizada.set(0, expressaoVetorizada.get(0).replaceAll("\\.?0*$", ""));
            expressaoVetorizada.set(0, expressaoVetorizada.get(0).replace(".", ","));
        }
    }

    public void calcularResultadoTotal(){
        
        calcularPorcentagens("%");

        calcularMultiplicacoes("x");

        calcularDivisoes("÷");

        calcularSomas("+");

        calcularSubtracoes("-");

        tratadorDeExpressao();
        
        setResultado(expressaoVetorizada.get(0));
    }
}
