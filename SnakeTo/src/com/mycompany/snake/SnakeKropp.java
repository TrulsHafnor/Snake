/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author ASUS
 */
public class SnakeKropp extends Figurer {
    private Circle kropp;
    private int retning = 0; // 1= NED, 2= OPP, 3= VENSTRE, 4= HØYRE
    private int animajsonsteller;
    
    public SnakeKropp (int x, int y, int arrayX, int arrayY,int radius) {
        super(x,y, arrayX, arrayY);
            this.kropp = new Circle();
            this.kropp.setCenterX(x);
            this.kropp.setCenterY(y);
            this.kropp.setRadius(radius);
            this.kropp.setFill(Color.GREEN);
            this.retning = 4;
            this.animajsonsteller = SnakePane.xStrl;
    }

    //denne er for å legge til kroppen men spillet er i gang
    public SnakeKropp (int x, int y, int arrayX, int arrayY,int radius, int retning, int animajsonsteller) {
        super(x,y, arrayX, arrayY);
        this.kropp = new Circle();
        this.kropp.setCenterX(x);
        this.kropp.setCenterY(y);
        this.kropp.setRadius(radius);
        this.kropp.setFill(Color.GREEN);
        this.retning = retning;
        this.animajsonsteller = animajsonsteller;
    }

     
    @Override
    public void move(int verdi) {
        if (animajsonsteller==SnakePane.xStrl) {
            animajsonsteller=0;
            setNyRetning(verdi);
        }
        else if (retning == 1) {
            ned();
        }
        else if (retning == 2) {
            opp();
        }
        else if (retning == 3) {
            venstre();
        }
        else if (retning == 4) {
            høyre();
        }
    }
    
    @Override
    public int getX() {
        return (int) kropp.getCenterX();
    }

    @Override
    public int getY() {
        return (int) kropp.getCenterY();
    }

    
    @Override
    public void setNyRetning (int a) {
        if (arrayY != 0 && SnakePane.arr[arrayY-1][arrayX] == a-1) {
            nyOpp(a);
        }
        else if (arrayX != SnakePane.xLengde-1 && SnakePane.arr[arrayY][arrayX+1] == a-1) {
            nyHøyre(a);
        }
        else if (arrayX != 0 && SnakePane.arr[arrayY][arrayX-1] == a-1) {
            nyVenstre(a);
        }
        else if(arrayY != SnakePane.yLengde-1 && SnakePane.arr[arrayY+1][arrayX] == a-1) {
            nyNed(a);
        }
    }
    
    public Circle getKropp() {
        return kropp;
    }

    @Override
    public void høyre() {
        kropp.setCenterX(kropp.getCenterX()+1);
        animajsonsteller++;
    }

    @Override
    public void venstre() {
        kropp.setCenterX(kropp.getCenterX()-1);
        animajsonsteller++;
    }

    @Override
    public void ned() { 
        kropp.setCenterY(kropp.getCenterY()+1);
        animajsonsteller++;
    }

    @Override
    public void opp() {
        kropp.setCenterY(kropp.getCenterY()-1);
        animajsonsteller++;
    }
    
    @Override
    public void nyHøyre(int a) {
        arrayX++;
        SnakePane.arr[arrayY][arrayX] = a;
        retning = 4;
        if(a == SnakePane.snakeKropp.size()) {
            SnakePane.arr[arrayY][arrayX-1] = 0;
        }
        høyre();
    }
    
    @Override
    public void nyVenstre(int a) {
        arrayX--;
        SnakePane.arr[arrayY][arrayX] = a;
        retning = 3;
        if(a == SnakePane.snakeKropp.size()) {
            SnakePane.arr[arrayY][arrayX+1] = 0;
        }
        venstre();
    }
    
    @Override
    public void nyNed(int a) {
        arrayY++;
        SnakePane.arr[arrayY][arrayX] = a;
        retning = 1;
        if(a == SnakePane.snakeKropp.size()) {
            SnakePane.arr[arrayY-1][arrayX] = 0;
        }
        ned();
    }
    
    @Override
    public void nyOpp(int a){
        arrayY--;
        SnakePane.arr[arrayY][arrayX] = a;
        retning = 2;
        if(a == SnakePane.snakeKropp.size()) {
            SnakePane.arr[arrayY+1][arrayX] = 0;
        }
        opp();
    }

    @Override
    public int getRetning() {
        return retning;
    }

    @Override
    public int getArrayX() {
        return arrayX;
    }

    @Override
    public int getArrayY() {
        return arrayY;
    }

    @Override
    public int getAnimasjonsteller() {
        return animajsonsteller;
    }
}
