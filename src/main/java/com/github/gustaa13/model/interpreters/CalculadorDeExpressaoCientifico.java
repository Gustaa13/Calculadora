package com.github.gustaa13.model.interpreters;

import java.math.BigDecimal;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

public class CalculadorDeExpressaoCientifico extends InterpretadorCalculadora{
    
    public CalculadorDeExpressaoCientifico(String expressao){
        super(expressao, "+-x÷%()^√a");
    }

    public void calcularResultadoTotal(){
        int posicaoFechamentoDeParenteses;
        int posicaoAberturaDeParenteses;
        int posicaoOperador;

        while(getExpressaoSeparada().contains(")")){
            posicaoFechamentoDeParenteses = getExpressaoSeparada().indexOf(")");
            List<String> parteDosParenteses = getExpressaoSeparada().subList(0, posicaoFechamentoDeParenteses);
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

            if(posicaoAberturaDeParenteses > 0 && getExpressaoSeparada().get(posicaoAberturaDeParenteses - 1).equals("a")){

                BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoAberturaDeParenteses + 1));

                String valor = Calculadora.valorAbsoluto(num1).toString();

                getExpressaoSeparada().set(posicaoAberturaDeParenteses + 1, valor);
                getExpressaoSeparada().remove(posicaoAberturaDeParenteses - 1);
                getExpressaoSeparada().remove(posicaoAberturaDeParenteses - 1);
                getExpressaoSeparada().remove(posicaoAberturaDeParenteses);
            }else{
                getExpressaoSeparada().remove(posicaoAberturaDeParenteses);
                getExpressaoSeparada().remove(posicaoAberturaDeParenteses + 1);
            }
        }
        
        while(getExpressaoSeparada().remove("("));

        while(getExpressaoSeparada().contains("√")){
            posicaoOperador = getExpressaoSeparada().indexOf("√");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));

            String valor = Calculadora.radiciacaoQuadrada(num1).toString();

            getExpressaoSeparada().set(posicaoOperador, valor);

            getExpressaoSeparada().remove(posicaoOperador + 1);
        }

        while(getExpressaoSeparada().contains("^")){
            posicaoOperador = getExpressaoSeparada().indexOf("^");

            Double num1 = Double.valueOf(getExpressaoSeparada().get(posicaoOperador - 1));
            
            Double num2 = Double.valueOf(getExpressaoSeparada().get(posicaoOperador + 1));

            String valor = Calculadora.exponenciacao(num1, num2).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);

            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);
        }

        while(getExpressaoSeparada().contains("%")){
            posicaoOperador = getExpressaoSeparada().indexOf("%");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));

            String valor = Calculadora.porcentagem(num1).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);

            getExpressaoSeparada().remove(posicaoOperador);
        }

        while(getExpressaoSeparada().contains("x")){

            posicaoOperador = getExpressaoSeparada().indexOf("x");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));

            String valor = Calculadora.multiplicacao(num1, num2).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);
        }

        while(getExpressaoSeparada().contains("÷")){

            posicaoOperador = getExpressaoSeparada().indexOf("÷");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));

            if(num2.compareTo(BigDecimal.ZERO) == 0) throw new DivisaoPorZeroException();

            String valor = Calculadora.divisao(num1, num2).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);       
        }

        while(getExpressaoSeparada().contains("+")){

            posicaoOperador = getExpressaoSeparada().indexOf("+");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));

            String valor = Calculadora.soma(num1, num2).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);         
        }

        while(getExpressaoSeparada().contains("-")){

            posicaoOperador = getExpressaoSeparada().indexOf("-");

            BigDecimal num1 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador - 1));

            BigDecimal num2 = new BigDecimal(getExpressaoSeparada().get(posicaoOperador + 1));

            String valor = Calculadora.subtracao(num1, num2).toString();

            getExpressaoSeparada().set(posicaoOperador - 1, valor);
            
            getExpressaoSeparada().remove(posicaoOperador);
            getExpressaoSeparada().remove(posicaoOperador);       
        }

        if(getExpressaoSeparada().get(0).contains(".")){
            getExpressaoSeparada().set(0, getExpressaoSeparada().get(0).replaceAll("\\.?0*$", ""));
            getExpressaoSeparada().set(0, getExpressaoSeparada().get(0).replace(".", ","));
        }
        
        setResultado(getExpressaoSeparada().get(0));
    }
}
