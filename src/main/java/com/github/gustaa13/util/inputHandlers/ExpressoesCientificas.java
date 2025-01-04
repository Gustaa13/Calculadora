package com.github.gustaa13.util.inputHandlers;

import com.github.gustaa13.util.AlertaGeral;
import com.github.gustaa13.util.exceptions.DivisaoPorZeroException;

import javafx.scene.control.Alert.AlertType;

public class ExpressoesCientificas extends TratadorDeEntradas {
    
    public ExpressoesCientificas(
        
        Integer contadorDeAlgarismos, 
        boolean permitirVirgula, 
        boolean permitirPorcentagem, 
        StringBuilder expressao
        
        ){

        super(
            contadorDeAlgarismos,
            permitirVirgula,
            permitirPorcentagem,
            expressao
        );

    }

    public void adicionarCaracterNaExpressao(String caractere){
        if(getExpressao().length() >= 100){
            AlertaGeral alertaDeCaracteres = new AlertaGeral(AlertType.INFORMATION, "Alerta de caraceteres", "Não é possível inserir mais de 100 caracteres.", 1.5);

            alertaDeCaracteres.janelaDeAlerta();

            return;
        }

        if(caractere.matches("[0-9]")){ 
            if(getContadorDeAlgarismos() >= 15){
                /*AlertaGeral alertaDeAlgarismos = new AlertaGeral(AlertType.INFORMATION, "Alerta de dígitos", "Não é possível inserir mais de 15 dígitos.", 1.5);
    
                alertaDeAlgarismos.janelaDeAlerta();*/
            }else if(getExpressao().length() > 0 && (getExpressao().charAt(getExpressao().length() - 1) == '%')){
                getExpressao().append("x" + caractere);
                setPermitirPorcentagem(true);
                setPermitirVirgula(true);
                setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
            }else if(numeroAnteriorIgualaZero() && caractere.equals("0") && getContadorDeAlgarismos() > 0 && getPermitirVirgula()){
                return;
            }else if(caractere.equals("0") && !expressaoExiste()){
                return;
            }
            else{
                getExpressao().append(caractere);
                setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
            }
        }else if(caractere.equals(",")){
            if(getPermitirVirgula() && getContadorDeAlgarismos() == 0){
                getExpressao().append("0,");
                setPermitirVirgula(false);
                setContadorDeAlgarismos(getContadorDeAlgarismos() + 1);
            }else if(getPermitirVirgula() && getContadorDeAlgarismos() < 15){
                getExpressao().append(caractere);
                setPermitirVirgula(false);
            }
        }else if(caractere.matches("[+\\-x÷]")){ 

            try {
                if(podeAdicionarCaracter()){  
                    erroDivisaoPorZero();  
                    getExpressao().append(caractere);
                    setPermitirVirgula(true);
                    setContadorDeAlgarismos(0);
                    setPermitirPorcentagem(true);
                }
            } catch (DivisaoPorZeroException e) {
                throw new DivisaoPorZeroException();
            }
            
        }else if(caractere.equals("%")){
            if(getExpressao().length() > 0 && getPermitirPorcentagem() && getContadorDeAlgarismos() > 0){
                getExpressao().append(caractere);
                setPermitirVirgula(true);
                setContadorDeAlgarismos(0);
                setPermitirPorcentagem(false);
            }
        }else if(caractere.equals("( )")){
            if(getContadorDeParentesesAbertos() == 0 && getContadorDeAlgarismos() == 0){
                getExpressao().append("(");
                setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
            }else if(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)).matches("[0-9]") && getContadorDeParentesesAbertos() > 0){
                getExpressao().append(")");
                setContadorDeParentesesFechados(getContadorDeParentesesFechados() + 1);
            }else if(String.valueOf(getExpressao().charAt(getExpressao().length() - 1)).matches("[+\\\\-x÷]")){
                getExpressao().append("(");
                setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
            }else if(getExpressao().charAt(getExpressao().length() - 1) == '('){
                getExpressao().append("(");
                setContadorDeParentesesAbertos(getContadorDeParentesesAbertos() + 1);
            }else if(getExpressao().charAt(getExpressao().length() - 1) == ')' && (getContadorDeParentesesAbertos() > getContadorDeParentesesFechados())){
                getExpressao().append(")");
                setContadorDeParentesesFechados(getContadorDeParentesesFechados() + 1);
            }
        }else if(caractere.equals("+/-") && getContadorDeAlgarismos() > 0){
            if(posicaoDoUltimoNumero() - 2 >= 0 && getExpressao().substring(posicaoDoUltimoNumero() - 2, posicaoDoUltimoNumero()).equals("(-")){
                getExpressao().delete(posicaoDoUltimoNumero() - 2, posicaoDoUltimoNumero());
                getExpressao().deleteCharAt(getExpressao().length() - 1);
            }else{
                getExpressao().insert(posicaoDoUltimoNumero(), "(-");
                getExpressao().append(")");
            }
        }else if(caractere.equals("xʸ") && getContadorDeAlgarismos() > 0){
            getExpressao().append("^");
            setPermitirVirgula(true);
            setContadorDeAlgarismos(0);
        }else if(caractere.equals("√")){
            getExpressao().append("√");
        }
    }
}
