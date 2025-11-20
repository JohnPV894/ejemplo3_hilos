package com.example.ejemplos_hilos3_interfaz;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Coche extends Thread {


    // Atributo estático: La lista que guarda el orden de llegada.
    // Esto es como el acta de la carrera, compartida por todos los carros.
    private static final List<String> podio = new ArrayList<>();

    // Constante: El número de carros que deben llegar para terminar.
    private static final int TOTAL_PARTICIPANTES = 5;

    //  La usamos para decirle que lance la ventana
    // del podio una vez que el último carro haya llegado.
    private static HelloController controlador;

    private final String marca;
    private int posicion = 0;
    private final ImageView imageView;
    private final int meta = 800; // La distancia que deben recorrer.


    public Coche(String marca, ImageView imageView, HelloController controller) {
        this.marca = marca;
        this.imageView = imageView;
        // Asignamos la referencia del controlador. Es estático, así que solo necesita asignarse una vez.
        Coche.controlador = controller;
    }

    /**
     * El método synchronized (sincronizado) es clave.
     * Esto quiere decir que solo un hilo (un carro) puede estar en este método a la vez.
     * Aseguramos que el registro en el podio sea correcto y en orden.
     * @param marca La marca del carro que acaba de cruzar la meta.
     */
    private static synchronized void agregarAlPodio(String marca) {
        // Validación por si acaso.
        if (!podio.contains(marca)) {
            podio.add(marca);
            System.out.println("Registro de llegada: " + podio);

            // Revisamos si ya llegaron todos los carros.
            if (podio.size() == TOTAL_PARTICIPANTES) {
                // Se acabó la carrera ,notificar al controlador.
                // Usamos Platform.runLater para que la interfaz se actualice sin problemas.
                Platform.runLater(() -> {
                    controlador.mostrarVentanaGanador(podio);
                });
            }
        }
    }

    @Override
    public void run() {
        // La carrera sigue mientras el carro no haya llegado a la meta.
        while (posicion < meta) {
            try {
                // Simulación de avance: lo que el carro se mueve en este paso.
                int avance = (int) (Math.random() * 6);
                posicion += avance;

                // Esto mueve el ImageView del carro en la interfaz.
                Platform.runLater(() -> {
                    imageView.setTranslateX(posicion);
                });

                // Pausa: para que se vea el movimiento
                long pausa = (long) (Math.random() * 150 + 50);
                Thread.sleep(pausa);

            } catch (InterruptedException e) {
                // Si alguien interrumpe el hilo, paramos el carro.
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Llamamos al método sincronizado para registrar la llegada.
        agregarAlPodio(this.marca);
    }

    public String getMarca() {
        return marca;
    }
}