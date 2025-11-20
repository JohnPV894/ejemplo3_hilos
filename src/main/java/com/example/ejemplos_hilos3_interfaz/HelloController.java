package com.example.ejemplos_hilos3_interfaz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    // Las variables que vienen del FXML
    @FXML private Label welcomeText;
    @FXML private Button startButton;
    @FXML private ImageView coche1, coche2, coche3, coche4, coche5;

    private List<Coche> participantes;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("¡Se dio la salida! ¡Arrancó la Carrera!");
        startButton.setDisable(true); // Desactivamos el botón para que no se pueda iniciar otra vez.

        participantes = new ArrayList<>();
        participantes.add(new Coche("Audi", coche1, this));
        participantes.add(new Coche("BMW", coche2, this));
        participantes.add(new Coche("Honda", coche3, this));
        participantes.add(new Coche("Peugeot", coche4, this));
        participantes.add(new Coche("Toyota", coche5, this));

        // Ponemos a correr a todos los hilos.
        for (Coche coche : participantes) {
            coche.start();
        }
    }

    /**
     * Método llamado desde el hilo Coche (mediante Platform.runLater) cuando
     * el podio está completo. Este método abre la ventana de resultados.
     * @param podioFinal La lista con el orden de llegada.
     */
    public void mostrarVentanaGanador(List<String> podioFinal) {
        try {
            // Cargamos el FXML de la ventana emergente.
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ganador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage popUpStage = new Stage();

            // Configuración de la ventana: es modal (bloquea la ventana principal).
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setTitle("Resultados de la Carrera");
            popUpStage.setScene(scene);

            // Obtenemos el controlador de la nueva ventana para pasarle los datos.
            GanadorController controller = fxmlLoader.getController();

            // Creamos el mensaje con los puestos.
            String mensajePodio = "🥇 Ganador: " + podioFinal.get(0) + "\n" +
                    "🥈 Segundo: " + podioFinal.get(1) + "\n" +
                    "🥉 Tercero: " + podioFinal.get(2) + "\n" +
                    "Puestos de Consolación: " + podioFinal.get(3) + " y " + podioFinal.get(4);

            controller.setResultado(mensajePodio);

            // Mostramos la ventana y esperamos a que el usuario la cierre.
            popUpStage.showAndWait();

        } catch (IOException e) {
            System.err.println(" Hubo un error cargando la ventana de resultados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}