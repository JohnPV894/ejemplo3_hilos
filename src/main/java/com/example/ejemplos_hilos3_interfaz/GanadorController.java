package com.example.ejemplos_hilos3_interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GanadorController {
    @FXML
    private Label resultadoLabel;

    /**
     * Método para establecer el mensaje del podio.
     * @param mensaje El texto completo del podio.
     */
    public void setResultado(String mensaje) {
        resultadoLabel.setText(mensaje);
    }
}