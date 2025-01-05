package com.github.gustaa13.model.interpreters;

import java.math.BigDecimal;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public class InterpretadorPadrao extends InterpretadorCalculadora{
    
    public InterpretadorPadrao(String expressao){
        super(expressao);
    }

    public void calcularResultadoTotal(){
        List<String> partesDaExpressao = separadorDeExpressao();
        int posicaoOperador;

        while(partesDaExpressao.contains("%")){
            posicaoOperador = partesDaExpressao.indexOf("%");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador - 1));

            String valor = Calculadora.porcentagem(num1).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);

            partesDaExpressao.remove(posicaoOperador);
        }

        while(partesDaExpressao.contains("x")){

            posicaoOperador = partesDaExpressao.indexOf("x");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(partesDaExpressao.get(posicaoOperador + 1));

            String valor = Calculadora.multiplicacao(num1, num2).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);
            
            partesDaExpressao.remove(posicaoOperador);
            partesDaExpressao.remove(posicaoOperador);
        }

        while(partesDaExpressao.contains("÷")){

            posicaoOperador = partesDaExpressao.indexOf("÷");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(partesDaExpressao.get(posicaoOperador + 1));

            if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();

            String valor = Calculadora.divisao(num1, num2).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);
            
            partesDaExpressao.remove(posicaoOperador);
            partesDaExpressao.remove(posicaoOperador);       
        }

        while(partesDaExpressao.contains("+")){

            posicaoOperador = partesDaExpressao.indexOf("+");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(partesDaExpressao.get(posicaoOperador + 1));

            String valor = Calculadora.soma(num1, num2).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);
            
            partesDaExpressao.remove(posicaoOperador);
            partesDaExpressao.remove(posicaoOperador);         
        }

        while(partesDaExpressao.contains("-")){

            posicaoOperador = partesDaExpressao.indexOf("-");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(partesDaExpressao.get(posicaoOperador + 1));

            String valor = Calculadora.subtracao(num1, num2).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);
            
            partesDaExpressao.remove(posicaoOperador);
            partesDaExpressao.remove(posicaoOperador);       
        }

        if(partesDaExpressao.get(0).contains(".")){
            partesDaExpressao.set(0, partesDaExpressao.get(0).replaceAll("\\.?0*$", ""));
            partesDaExpressao.set(0, partesDaExpressao.get(0).replace(".", ","));
        }
        
        setResultado(partesDaExpressao.get(0));
    }
}
