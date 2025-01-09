package com.github.gustaa13.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class AlertaGeral {

    private AlertType tipo;
    private String titulo;
    private String mensagem;

    public AlertaGeral(AlertType tipo, String titulo, String mensagem) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public void exibirAlerta(){
        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.show();
    }
}
