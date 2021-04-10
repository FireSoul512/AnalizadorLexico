package com.analizador.compilacion;

import javafx.scene.shape.Rectangle;

public class Cuadrado extends Rectangle {
    public Cuadrado(int x, int y, int lado){
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setHeight(lado);
        this.setWidth(lado);
    }

}
