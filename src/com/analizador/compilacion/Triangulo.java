package com.analizador.compilacion;

import javafx.scene.shape.Polygon;

public class Triangulo extends Polygon {
    public Triangulo(int x, int y, int base, int altura){
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setScaleX(base/100);
        this.setScaleY(altura/100);
    }
}
