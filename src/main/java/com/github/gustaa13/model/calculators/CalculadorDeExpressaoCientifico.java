package com.github.gustaa13.model.calculators;

import java.math.BigDecimal;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.PosicaoNaoExisteException;

public class CalculadorDeExpressaoCientifico extends CalculadorDeExpressaoPadrao{

    private int posicaoFechamentoDeParenteses;
    private int posicaoAberturaDeParenteses;
    private List<String> salveExpressaoVetorizada;
    
    public CalculadorDeExpressaoCientifico(String expressao){
        super(expressao, "+-x÷%()^√a");
    }

    public void calcularRadiciacao(String operador){
        while(getExpressaoVetorizada().contains(operador)){
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                setNumeroPosterior(new BigDecimal(0));
            }

            String valor = Calculadora.radiciacaoQuadrada(getNumeroPosterior()).toString();

            getExpressaoVetorizada().set(getPosicaoOperador(), valor);

            getExpressaoVetorizada().remove(getPosicaoOperador() + 1);
        }
    }

    public void calcularExponenciacao(String operador){
        while(getExpressaoVetorizada().contains(operador)){

            try {
                obterNumeroAntecessorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                setNumeroAntecessor(new BigDecimal(0));
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                setNumeroPosterior(new BigDecimal(0));
            }   

            String valor = Calculadora.exponenciacao(Double.valueOf(getNumeroAntecessor().toString()), Double.valueOf(getNumeroPosterior().toString())).toString();

            getExpressaoVetorizada().set(getPosicaoOperador() - 1, valor);

            getExpressaoVetorizada().remove(getPosicaoOperador());
            getExpressaoVetorizada().remove(getPosicaoOperador());
        }
    }

    public void calcularOperacoesEntreParenteses(String parentesesAberto, String parentesesFechado){
        while(salveExpressaoVetorizada.contains(parentesesFechado)){

            posicaoFechamentoDeParenteses = salveExpressaoVetorizada.indexOf(parentesesFechado);
            setExpressaoVetorizada(salveExpressaoVetorizada.subList(0, posicaoFechamentoDeParenteses));
            posicaoAberturaDeParenteses = getExpressaoVetorizada().lastIndexOf(parentesesAberto);
            setExpressaoVetorizada(getExpressaoVetorizada().subList(posicaoAberturaDeParenteses + 1, posicaoFechamentoDeParenteses));

            calcularRadiciacao("√");

            calcularRadiciacao("^");

            calcularPorcentagens("%");

            calcularMultiplicacoes("x");
    
            calcularDivisoes("÷");
    
            calcularSomas("+");

            calcularSubtracoes("-");

            salveExpressaoVetorizada.remove(posicaoAberturaDeParenteses);
            salveExpressaoVetorizada.remove(posicaoAberturaDeParenteses + 1);
        }
    }

    public void calcularValorAbsoluto(){
        if(posicaoAberturaDeParenteses > 0 && salveExpressaoVetorizada.get(posicaoAberturaDeParenteses - 1).equals("a")){
            BigDecimal numero = new BigDecimal(salveExpressaoVetorizada.get(posicaoAberturaDeParenteses));

            String valor = Calculadora.valorAbsoluto(numero).toString();

            salveExpressaoVetorizada.set(posicaoAberturaDeParenteses, valor);
            salveExpressaoVetorizada.remove(posicaoAberturaDeParenteses - 1);
        }
    }

    @Override
    public void calcularResultadoTotal(){
        salveExpressaoVetorizada = getExpressaoVetorizada();

        calcularOperacoesEntreParenteses("(", ")");
        calcularValorAbsoluto();
        
        while(salveExpressaoVetorizada.remove("("));

        setExpressaoVetorizada(salveExpressaoVetorizada);

        calcularRadiciacao("√");

        calcularRadiciacao("^");
        
        super.calcularResultadoTotal();
    }
}
