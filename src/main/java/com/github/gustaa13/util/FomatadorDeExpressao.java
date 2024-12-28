package com.github.gustaa13.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FomatadorDeExpressao {
    
    public static String formatar(String entrada) {
        Pattern padrao = Pattern.compile("\\d+(?:,\\d{1,2})?");
        Matcher correspondencia = padrao.matcher(entrada);

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');

        StringBuffer resultado = new StringBuffer();
        while (correspondencia.find()) {
            String numero = correspondencia.group();
            try {
                numero = numero.replace(",", ".");
                
                double valorNumerico = Double.parseDouble(numero);
                DecimalFormat formatoDecimal = new DecimalFormat("#,###.###", simbolos);
                String numeroFormatado = formatoDecimal.format(valorNumerico);
                
                correspondencia.appendReplacement(resultado, numeroFormatado);
            } catch (NumberFormatException e) {
                correspondencia.appendReplacement(resultado, numero);
            }
        }
        correspondencia.appendTail(resultado);

        return resultado.toString();
    }
}
