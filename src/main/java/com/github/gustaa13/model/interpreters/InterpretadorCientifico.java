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
        List<String> partesDaExpressao = separadorDeExpressao();
        int posicaoFechamentoDeParenteses;
        int posicaoAberturaDeParenteses;
        int posicaoOperador;

        while(partesDaExpressao.contains(")")){
            posicaoFechamentoDeParenteses = partesDaExpressao.indexOf(")");
            List<String> parteDosParenteses = partesDaExpressao.subList(0, posicaoFechamentoDeParenteses);
            posicaoAberturaDeParenteses = parteDosParenteses.lastIndexOf("(");
            parteDosParenteses = parteDosParenteses.subList(posicaoAberturaDeParenteses + 1, posicaoFechamentoDeParenteses);

            while(parteDosParenteses.contains("√")){
                posicaoOperador = parteDosParenteses.indexOf("√");

                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador + 1));

                String valor = Calculadora.radiciacaoQuadrada(num1).toString();

                parteDosParenteses.set(posicaoOperador, valor);

                parteDosParenteses.remove(posicaoOperador + 1);
            }

            while(parteDosParenteses.contains("^")){
                posicaoOperador = parteDosParenteses.indexOf("^");

                Double num1 = Double.valueOf(parteDosParenteses.get(posicaoOperador - 1));
                
                Double num2 = Double.valueOf(parteDosParenteses.get(posicaoOperador + 1));

                String valor = Calculadora.exponenciacao(num1, num2).toString();

                parteDosParenteses.set(posicaoOperador - 1, valor);

                parteDosParenteses.remove(posicaoOperador);
                parteDosParenteses.remove(posicaoOperador);
            }

            while(parteDosParenteses.contains("%")){
                posicaoOperador = parteDosParenteses.indexOf("%");
    
                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador - 1));
    
                String valor = Calculadora.porcentagem(num1).toString();
    
                parteDosParenteses.set(posicaoOperador - 1, valor);
    
                parteDosParenteses.remove(posicaoOperador);
            }
    
            while(parteDosParenteses.contains("x")){
    
                posicaoOperador = parteDosParenteses.indexOf("x");
    
                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador - 1));
    
                BigDecimal num2 = new BigDecimal(parteDosParenteses.get(posicaoOperador + 1));
    
                String valor = Calculadora.multiplicacao(num1, num2).toString();
    
                parteDosParenteses.set(posicaoOperador - 1, valor);
                
                parteDosParenteses.remove(posicaoOperador);
                parteDosParenteses.remove(posicaoOperador);
            }
    
            while(parteDosParenteses.contains("÷")){
    
                posicaoOperador = parteDosParenteses.indexOf("÷");
    
                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador - 1));
    
                BigDecimal num2 = new BigDecimal(parteDosParenteses.get(posicaoOperador + 1));

                if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();
    
                String valor = Calculadora.divisao(num1, num2).toString();
    
                parteDosParenteses.set(posicaoOperador - 1, valor);
                
                parteDosParenteses.remove(posicaoOperador);
                parteDosParenteses.remove(posicaoOperador);       
            }
    
            while(parteDosParenteses.contains("+")){
    
                posicaoOperador = parteDosParenteses.indexOf("+");
    
                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador - 1));
    
                BigDecimal num2 = new BigDecimal(parteDosParenteses.get(posicaoOperador + 1));
    
                String valor = Calculadora.soma(num1, num2).toString();
    
                parteDosParenteses.set(posicaoOperador - 1, valor);
                
                parteDosParenteses.remove(posicaoOperador);
                parteDosParenteses.remove(posicaoOperador);         
            }
    
            while(parteDosParenteses.contains("-")){
    
                posicaoOperador = parteDosParenteses.indexOf("-");

                if(posicaoOperador - 1 < 0){
                    parteDosParenteses.add(0, "0");
                    posicaoOperador = parteDosParenteses.indexOf("-");
                }

                BigDecimal num1 = new BigDecimal(parteDosParenteses.get(posicaoOperador - 1));
    
                BigDecimal num2 = new BigDecimal(parteDosParenteses.get(posicaoOperador + 1));
    
                String valor = Calculadora.subtracao(num1, num2).toString();
    
                parteDosParenteses.set(posicaoOperador - 1, valor);
                
                parteDosParenteses.remove(posicaoOperador);
                parteDosParenteses.remove(posicaoOperador);       
            }

            if(posicaoAberturaDeParenteses > 0 && partesDaExpressao.get(posicaoAberturaDeParenteses - 1).equals("a")){

                BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoAberturaDeParenteses + 1));

                String valor = Calculadora.valorAbsoluto(num1).toString();

                partesDaExpressao.set(posicaoAberturaDeParenteses + 1, valor);
                partesDaExpressao.remove(posicaoAberturaDeParenteses - 1);
                partesDaExpressao.remove(posicaoAberturaDeParenteses - 1);
                partesDaExpressao.remove(posicaoAberturaDeParenteses);
            }else{
                partesDaExpressao.remove(posicaoAberturaDeParenteses);
                partesDaExpressao.remove(posicaoAberturaDeParenteses + 1);
            }
        }
        
        while(partesDaExpressao.remove("("));

        while(partesDaExpressao.contains("√")){
            posicaoOperador = partesDaExpressao.indexOf("√");

            BigDecimal num1 = new BigDecimal(partesDaExpressao.get(posicaoOperador + 1));

            String valor = Calculadora.radiciacaoQuadrada(num1).toString();

            partesDaExpressao.set(posicaoOperador, valor);

            partesDaExpressao.remove(posicaoOperador + 1);
        }

        while(partesDaExpressao.contains("^")){
            posicaoOperador = partesDaExpressao.indexOf("^");

            Double num1 = Double.valueOf(partesDaExpressao.get(posicaoOperador - 1));
            
            Double num2 = Double.valueOf(partesDaExpressao.get(posicaoOperador + 1));

            String valor = Calculadora.exponenciacao(num1, num2).toString();

            partesDaExpressao.set(posicaoOperador - 1, valor);

            partesDaExpressao.remove(posicaoOperador);
            partesDaExpressao.remove(posicaoOperador);
        }

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
