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
        List<String> partes = separadorDeExpressao();
        int index;

        while(partes.contains("%")){
            index = partes.indexOf("%");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            String valor = Calculadora.porcentagem(num1).toString();

            partes.set(index - 1, valor);

            partes.remove(index);
        }

        while(partes.contains("x")){

            index = partes.indexOf("x");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.multiplicacao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);
        }

        while(partes.contains("÷")){

            index = partes.indexOf("÷");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();

            String valor = Calculadora.divisao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);       
        }

        while(partes.contains("+")){

            index = partes.indexOf("+");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.soma(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);         
        }

        while(partes.contains("-")){

            index = partes.indexOf("-");

            BigDecimal num1 = new BigDecimal(partes.get(index - 1));

            BigDecimal num2 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.subtracao(num1, num2).toString();

            partes.set(index - 1, valor);
            
            partes.remove(index);
            partes.remove(index);       
        }

        if(partes.get(0).contains(".")){
            partes.set(0, partes.get(0).replaceAll("\\.?0*$", ""));
            partes.set(0, partes.get(0).replace(".", ","));
        }
        
        setResultado(partes.get(0));
    }
}
