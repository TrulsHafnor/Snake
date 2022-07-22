package com.mycompany.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Mat {
    private Circle mat;
    private int x,y,arrayX,arrayY;

    public Mat(int x, int y, int arrayX, int arrayY, int radius) {
        this.x=x;
        this.y=y;
        this.arrayX=arrayX;
        this.arrayY=arrayY;
        this.mat = new Circle();
        this.mat.setCenterX(x);
        this.mat.setCenterY(y);
        this.mat.setRadius(radius);
        this.mat.setFill(Color.YELLOW);
    }

    public Circle getMat()  {
        return mat;
    }

    public void removeMat() {
        mat.setFill(Color.TRANSPARENT);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getArrayX() {
        return arrayX;
    }

    public int getArrayY() {
        return arrayY;
    }

}
