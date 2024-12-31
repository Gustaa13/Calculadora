package com.github.gustaa13.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculadoraApp extends Application {

    private static Scene cena;

    @Override
    public void start(Stage palco) throws IOException {
        cena = new Scene(loadFXML("/com/github/gustaa13/calculadora-padrao"), 400, 545);

        cena.getStylesheets().add(getClass().getResource("/com/github/gustaa13/style.css").toExternalForm());

        palco.setResizable(false);
        palco.setTitle("Calculadora");
        palco.setScene(cena);
        palco.show();
    }

    public static void setRoot(String fxml) throws IOException {
        cena.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculadoraApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}