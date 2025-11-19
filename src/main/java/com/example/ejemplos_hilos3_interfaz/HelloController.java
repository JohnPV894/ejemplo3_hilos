package com.example.ejemplos_hilos3_interfaz;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private Label welcomeText;

    // --- VINCULACIÓN CON LAS IMÁGENES DEL FXML ---
    @FXML private ImageView coche1;
    @FXML private ImageView coche2;
    @FXML private ImageView coche3;
    @FXML private ImageView coche4;
    @FXML private ImageView coche5;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
