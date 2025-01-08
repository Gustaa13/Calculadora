package com.github.gustaa13.model.interpreters;

import java.math.BigDecimal;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;
import com.github.gustaa13.util.exceptions.PosicaoNaoExisteException;

public class CalculadorDeExpressaoPadrao extends InterpretadorCalculadora{

    private int posicaoOperador;
    private BigDecimal numeroPosterior;
    private BigDecimal numeroAntecessor;
    
    public CalculadorDeExpressaoPadrao(String expressao){
        super(expressao, "+-x÷%");
        posicaoOperador = 0;
        numeroPosterior = new BigDecimal(0);
        numeroAntecessor = new BigDecimal(0);
    }

    private void obterPosicaoOperador(String operador){
        posicaoOperador = getExpressaoSeparada().indexOf(operador);
    }

    private void obterNumeroAntecessorAoOperador(String operador){
        obterPosicaoOperador(operador);

        if(posicaoOperador < 0) throw new PosicaoNaoExisteException();
        
        numeroAntecessor = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));
    }

    private void obterNumeroPosteriorAoOperador(String operador){
        obterPosicaoOperador(operador);

        if(posicaoOperador > getExpressaoSeparada().size()) throw new PosicaoNaoExisteException();

        numeroAntecessor = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));
    }

    public void calcularPorcentagens(String operador){
        while(getExpressaoSeparada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroAntecessor = new BigDecimal(0);
            }
            
            String valor = Calculadora.porcentagem(numeroAntecessor).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);

            getExpressaoSeparada().remove(posicaoOperador);
        }
    }

    public void calcularMultiplicacoes(String operador){
        while(getExpressaoSeparada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroAntecessor = new BigDecimal(1);
            }
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroPosterior = new BigDecimal(1);
            }
            
            String valor = Calculadora.multiplicacao(numeroAntecessor, numeroPosterior).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);
        }
    }

    public void calcularDivisoes(String operador){
        while(getExpressaoSeparada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroAntecessor = new BigDecimal(0);
            }
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroPosterior = new BigDecimal(1);
            }

            String valor;

            try {
                valor = Calculadora.divisao(numeroAntecessor, numeroPosterior).toString();
            } catch(DivisaoPorZeroException e){
                throw new DivisaoPorZeroException();
            }
            
            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);       
        }
    }

    public void calcularSomas(String operador){
        while(getExpressaoSeparada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroAntecessor = new BigDecimal(0);
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroPosterior = new BigDecimal(0);
            }

            String valor = Calculadora.soma(numeroAntecessor, numeroPosterior).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);         
        }
    }

    public void calcularSubtracoes(String operador){
        while(getExpressaoSeparada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroAntecessor = new BigDecimal(0);
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                numeroPosterior = new BigDecimal(0);
            }

            String valor = Calculadora.subtracao(numeroAntecessor, numeroPosterior).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);       
        }
    }

    public void tratadorDeExpressao(){
        if(getExpressaoSeparada().get(0).contains(".")){
            getExpressaoSeparada().set(0, getExpressaoSeparada().get(0).replaceAll("\\.?0*$", ""));
            getExpressaoSeparada().set(0, getExpressaoSeparada().get(0).replace(".", ","));
        }
    }

    public void calcularResultadoTotal(){
        
        calcularPorcentagens("%");

        calcularMultiplicacoes("x");

        calcularDivisoes("÷");

        calcularSomas("+");

        calcularSubtracoes("-");

        tratadorDeExpressao();
        
        setResultado(getExpressaoSeparada().get(0));
    }
}
