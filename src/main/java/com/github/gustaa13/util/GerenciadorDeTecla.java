package com.github.gustaa13.util;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GerenciadorDeTecla{

    public static void adicionarEventoAoPressionar(Button botao, KeyCode codigo_tecla) {
        adicionarEventoAoPressionar(botao, codigo_tecla, false);
    }

    public static void adicionarEventoAoPressionar(Button botao, KeyCode codigo_tecla, boolean shiftativado){
        botao.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == codigo_tecla) {
                if (shiftativado && event.isShiftDown()) {
                    botao.arm();
                    botao.fire();
                }else if(!shiftativado && !event.isShiftDown()){
                    botao.arm();
                    botao.fire();
                }
            }
        });

        botao.getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == codigo_tecla) {
                botao.disarm();
            }
        });
    }
}
