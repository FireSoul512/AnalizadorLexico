package com.analizador.Model.Compilacion;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Lienzo {
    private AnchorPane root = new AnchorPane();
    private Stage stage = new Stage();

    public Lienzo(int x, int y){
        stage.setTitle("Figuras");
        stage.setResizable(false);
        stage.setScene(new Scene(root, x, y));
    }

    public void agregarFigura(Node circulo){
        root.getChildren().add(circulo);
    }

    public void run(){
        stage.show();
    }

}
