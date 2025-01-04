package com.github.gustaa13.model.interpreters;

import java.math.BigDecimal;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public class InterpretadorCientifico extends InterpretadorCalculadora{
    
    public InterpretadorCientifico(String expressao){
        super(expressao);
    }

    public void calcularResultadoTotal(){
        List<String> partes = separadorDeExpressao();
        int index;
        int index2;

        while(partes.contains(")")){
            index = partes.indexOf(")");
            List<String> parte = partes.subList(0, index);
            index2 = parte.lastIndexOf("(");
            parte = parte.subList(index2 + 1, index);

            while(parte.contains("√")){
                index = parte.indexOf("√");

                BigDecimal num1 = new BigDecimal(parte.get(index + 1));

                String valor = Calculadora.radiciacaoQuadrada(num1).toString();

                parte.set(index, valor);

                parte.remove(index + 1);
            }

            while(parte.contains("^")){
                index = parte.indexOf("^");

                Double num1 = Double.valueOf(parte.get(index - 1));
                
                Double num2 = Double.valueOf(parte.get(index + 1));

                String valor = Calculadora.exponenciacao(num1, num2).toString();

                parte.set(index - 1, valor);

                parte.remove(index);
                parte.remove(index);
            }

            while(parte.contains("%")){
                index = parte.indexOf("%");
    
                BigDecimal num1 = new BigDecimal(parte.get(index - 1));
    
                String valor = Calculadora.porcentagem(num1).toString();
    
                parte.set(index - 1, valor);
    
                parte.remove(index);
            }
    
            while(parte.contains("x")){
    
                index = parte.indexOf("x");
    
                BigDecimal num1 = new BigDecimal(parte.get(index - 1));
    
                BigDecimal num2 = new BigDecimal(parte.get(index + 1));
    
                String valor = Calculadora.multiplicacao(num1, num2).toString();
    
                parte.set(index - 1, valor);
                
                parte.remove(index);
                parte.remove(index);
            }
    
            while(parte.contains("÷")){
    
                index = parte.indexOf("÷");
    
                BigDecimal num1 = new BigDecimal(parte.get(index - 1));
    
                BigDecimal num2 = new BigDecimal(parte.get(index + 1));

                if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();
    
                String valor = Calculadora.divisao(num1, num2).toString();
    
                parte.set(index - 1, valor);
                
                parte.remove(index);
                parte.remove(index);       
            }
    
            while(parte.contains("+")){
    
                index = parte.indexOf("+");
    
                BigDecimal num1 = new BigDecimal(parte.get(index - 1));
    
                BigDecimal num2 = new BigDecimal(parte.get(index + 1));
    
                String valor = Calculadora.soma(num1, num2).toString();
    
                parte.set(index - 1, valor);
                
                parte.remove(index);
                parte.remove(index);         
            }
    
            while(parte.contains("-")){
    
                index = parte.indexOf("-");

                if(index - 1 < 0){
                    parte.add(0, "0");
                    index = parte.indexOf("-");
                }

                BigDecimal num1 = new BigDecimal(parte.get(index - 1));
    
                BigDecimal num2 = new BigDecimal(parte.get(index + 1));
    
                String valor = Calculadora.subtracao(num1, num2).toString();
    
                parte.set(index - 1, valor);
                
                parte.remove(index);
                parte.remove(index);       
            }

            partes.remove(index2);
            partes.remove(index2 + 1);
        }
        
        while(partes.remove("("));

        while(partes.contains("√")){
            index = partes.indexOf("√");

            BigDecimal num1 = new BigDecimal(partes.get(index + 1));

            String valor = Calculadora.radiciacaoQuadrada(num1).toString();

            partes.set(index, valor);

            partes.remove(index + 1);
        }

        while(partes.contains("^")){
            index = partes.indexOf("^");

            Double num1 = Double.valueOf(partes.get(index - 1));
            
            Double num2 = Double.valueOf(partes.get(index + 1));

            String valor = Calculadora.exponenciacao(num1, num2).toString();

            partes.set(index - 1, valor);

            partes.remove(index);
            partes.remove(index);
        }

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
