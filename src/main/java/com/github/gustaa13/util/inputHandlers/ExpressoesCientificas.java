package com.github.gustaa13.util.inputHandlers;

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

    public void adicionarCaracterNaExpressao(String caractere){}
}
