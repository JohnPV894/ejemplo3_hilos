module com.example.ejemplos_hilos3_interfaz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejemplos_hilos3_interfaz to javafx.fxml;
    exports com.example.ejemplos_hilos3_interfaz;
}