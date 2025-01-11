package com.github.gustaa13.model.calculators;

import java.math.BigDecimal;
import java.util.List;

import com.github.gustaa13.model.Calculadora;
import com.github.gustaa13.util.exceptions.PosicaoNaoExisteException;

public class CalculadorDeExpressaoCientifico extends CalculadorDeExpressaoPadrao{

    private int posicaoFechamentoDeParenteses;
    private int posicaoAberturaDeParenteses;
    private List<String> salvoExpressaoVetorizada;
    
    public CalculadorDeExpressaoCientifico(String expressao){
        super(expressao, "+-x÷%()^√a");
    }

    protected void obterPosicaoFechamentoDeParenteses(){
        posicaoFechamentoDeParenteses = salvoExpressaoVetorizada.indexOf(")");
    }

    protected void obterPosicaoAberturaDeParenteses(){
        posicaoAberturaDeParenteses = getExpressaoVetorizada().lastIndexOf("(");
    }

    protected void obterPosicaoAberturaDeParenteses(List<String> expressaoVetorizada){
        posicaoAberturaDeParenteses = expressaoVetorizada.lastIndexOf("(");
    }

    public void calcularRadiciacao(String operador){
        while(getExpressaoVetorizada().contains(operador)){
            
            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                getExpressaoVetorizada().add("0");
                obterNumeroPosteriorAoOperador(operador);
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
                getExpressaoVetorizada().add(getPosicaoOperador(), "0");
                obterNumeroAntecessorAoOperador(operador);
            }

            try {
                obterNumeroPosteriorAoOperador(operador);
            } catch(PosicaoNaoExisteException e){
                getExpressaoVetorizada().add("0");
                obterNumeroPosteriorAoOperador(operador);
            }   

            String valor = Calculadora.exponenciacao(Double.valueOf(getNumeroAntecessor().toString()), Double.valueOf(getNumeroPosterior().toString())).toString();

            getExpressaoVetorizada().set(getPosicaoOperador() - 1, valor);

            getExpressaoVetorizada().remove(getPosicaoOperador());
            getExpressaoVetorizada().remove(getPosicaoOperador());
        }
    }

    public void calcularOperacoesEntreParenteses(String parentesesAberto, String parentesesFechado){
        while(salvoExpressaoVetorizada.contains(parentesesFechado)){

            obterPosicaoFechamentoDeParenteses();
            setExpressaoVetorizada(salvoExpressaoVetorizada.subList(0, posicaoFechamentoDeParenteses));
            obterPosicaoAberturaDeParenteses();
            setExpressaoVetorizada(getExpressaoVetorizada().subList(posicaoAberturaDeParenteses + 1, posicaoFechamentoDeParenteses));

            calcularRadiciacao("√");

            calcularExponenciacao("^");

            calcularPorcentagens("%");

            calcularMultiplicacoes("x");
    
            calcularDivisoes("÷");
    
            calcularSomas("+");

            calcularSubtracoes("-");

            salvoExpressaoVetorizada.remove(posicaoAberturaDeParenteses);
            salvoExpressaoVetorizada.remove(posicaoAberturaDeParenteses + 1);

            calcularValorAbsoluto();
        }
    }

    public void calcularValorAbsoluto(){
        if(posicaoAberturaDeParenteses > 0 && salvoExpressaoVetorizada.get(posicaoAberturaDeParenteses - 1).equals("a")){
            BigDecimal numero = new BigDecimal(salvoExpressaoVetorizada.get(posicaoAberturaDeParenteses));

            String valor = Calculadora.valorAbsoluto(numero).toString();

            salvoExpressaoVetorizada.set(posicaoAberturaDeParenteses, valor);
            salvoExpressaoVetorizada.remove(posicaoAberturaDeParenteses - 1);
        }
    }

    @Override
    public void calcularResultadoTotal(){
        salvoExpressaoVetorizada = getExpressaoVetorizada();
        
        calcularOperacoesEntreParenteses("(", ")");

        obterPosicaoAberturaDeParenteses(salvoExpressaoVetorizada);

        while(salvoExpressaoVetorizada.remove("("));

        calcularValorAbsoluto();
        
        setExpressaoVetorizada(salvoExpressaoVetorizada);

        calcularRadiciacao("√");

        calcularExponenciacao("^");
        
        super.calcularResultadoTotal();
    }
}
