package com.github.gustaa13.util;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FomatadorDeExpressao {
    
    public static String formatar(String entrada){
        Pattern padrao = Pattern.compile("\\d+(?:,\\d{1,26})?");
        Matcher correspondencia = padrao.matcher(entrada);

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');

        StringBuffer resultado = new StringBuffer();
        while (correspondencia.find()) {
            String numero = correspondencia.group();
            try {
                numero = numero.replace(",", ".");
                
                String[] partes = numero.split("\\.");
                String parteInteira = partes[0];
                String parteDecimal = partes.length > 1 ? partes[1] : "";
                
                BigInteger numeroInteiro = new BigInteger(parteInteira);
                DecimalFormat formatoInteiro = new DecimalFormat("#,###", simbolos);
                parteInteira = formatoInteiro.format(numeroInteiro);

                String numeroFormatado = parteInteira;

                if (!parteDecimal.isEmpty()) {
                    numeroFormatado += "," + parteDecimal;
                }

                correspondencia.appendReplacement(resultado, numeroFormatado);
            } catch (NumberFormatException e) {
                correspondencia.appendReplacement(resultado, numero);
            }
        }
        correspondencia.appendTail(resultado);

        return resultado.toString();
    }
}
