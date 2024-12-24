package com.github.gustaa13.util;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

public class AlertaUtil {

    private AlertType tipo;
    private String titulo;
    private String mensagem;
    private Double segundos;

    public AlertaUtil(AlertType tipo, String titulo, String mensagem, Double segundos) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.segundos = segundos;
    }

    public void janelaDeAlerta(){
        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.show();

        PauseTransition pausa = new PauseTransition(Duration.seconds(segundos)); 
        pausa.setOnFinished(event -> {
            Platform.runLater(() -> alerta.close());
        });

        pausa.play();
    }
}
