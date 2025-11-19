package com.example.ejemplos_hilos3_interfaz;

import javafx.application.Application;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("getResource(/img/audi.png) = " + HelloApplication.class.getResource("/img/audi.png"));
        System.out.println("getResource(img/audi.png) = " + HelloApplication.class.getResource("img/audi.png"));
        System.out.println("getResource(com/.../img/audi.png) = " + HelloApplication.class.getResource("/com/example/ejemplos_hilos3_interfaz/img/audi.png"));
        Application.launch(HelloApplication.class, args);
    }
}
