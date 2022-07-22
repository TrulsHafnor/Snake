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
public class Snake extends Figurer {
    public Circle snake;
    private int animasjonsTeller;
    private int teller;
    
    // Dette er retningen han må fullføre, før snake kan endre retning
    public static enum sRetning {
        OPP,
        NED,
        HØYRE,
        VENSTRE,
        FRI
    }
    
    public static sRetning aRetning; // nåverende retning
    public static sRetning tidligereRetning; // den tdiligere retningen
    
    public Snake (int x, int y, int arrayX, int arrayY, int radius) {
        super(x, y, arrayX, arrayY);
        this.snake = new Circle();
            this.snake.setCenterX(x);
            this.snake.setCenterY(y);
            this.snake.setRadius(radius);
            this.snake.setFill(Color.RED);
            this.aRetning = sRetning.HØYRE;
            this.tidligereRetning = sRetning.FRI;
            this.animasjonsTeller = SnakePane.xStrl;
            this.teller = teller =0;
    }
    
    public void move(int i) {
        if (aRetning.equals(aRetning.NED) && animasjonsTeller !=SnakePane.yStrl) {
            ned();
        }
        else if (aRetning.equals(aRetning.OPP) && animasjonsTeller !=SnakePane.yStrl) {
            opp();
        }
        else if (aRetning.equals(aRetning.VENSTRE) && animasjonsTeller !=SnakePane.xStrl) {
            venstre();
        }
        else if (aRetning.equals(aRetning.HØYRE) && animasjonsTeller !=SnakePane.xStrl) {
            høyre();
        }
        else if (com.mycompany.snake.Main.retning.equals(Main.retning.NED)) {
            nyNed(1);
        }
        else if (com.mycompany.snake.Main.retning.equals(Main.retning.OPP)) {
            nyOpp(1);
        }
        else if (com.mycompany.snake.Main.retning.equals(Main.retning.VENSTRE)) {
            nyVenstre(1);
        }
        else if (com.mycompany.snake.Main.retning.equals(Main.retning.HØYRE)) {
            nyHøyre(1);
        }
    }
    
    @Override
    public int getX() {
        return (int) snake.getCenterX();
    }
    
    @Override
    public int getY() {
        return (int) snake.getCenterY();
    }
    
    public void opp () {
        snake.setCenterY(snake.getCenterY()-1);
        animasjonsTeller ++;
    }
    
    public void ned () {
        snake.setCenterY(snake.getCenterY()+1);
        animasjonsTeller ++;
    }
        
    public void venstre () {
        snake.setCenterX(snake.getCenterX()-1);
        animasjonsTeller ++;
    }
            
    public void høyre () {
        snake.setCenterX(snake.getCenterX()+1);
        animasjonsTeller ++;
    }
    
    public void nyNed (int a) {
        tidligereRetning = aRetning;
        animasjonsTeller =0;
        snake.setCenterY(snake.getCenterY()+1);
        animasjonsTeller ++;
        aRetning = sRetning.NED;
        arrayY++;
        if(SnakePane.arr[arrayY][arrayX] == -1) {
            SnakePane.aktivMat = false;
        }
        if(SnakePane.arr[arrayY][arrayX] > 1) {
            SnakePane.gameOver = true;
        }
        SnakePane.arr[arrayY][arrayX] = 1;
    }
    
    public void nyOpp (int a) {
        tidligereRetning = aRetning;
        animasjonsTeller =0;
        snake.setCenterY(snake.getCenterY()-1);
        animasjonsTeller++;
        aRetning = sRetning.OPP;
        arrayY--;
        if(SnakePane.arr[arrayY][arrayX] == -1) {
            SnakePane.aktivMat = false;
        }
        if(SnakePane.arr[arrayY][arrayX] > 1) {
            SnakePane.gameOver = true;
        }
        SnakePane.arr[arrayY][arrayX] = 1;
    }
    
    public void nyVenstre(int a) {
        tidligereRetning = aRetning;
        animasjonsTeller =0;
        snake.setCenterX(snake.getCenterX()-1);
        animasjonsTeller++;
        aRetning = sRetning.VENSTRE;
        arrayX--;
        if(SnakePane.arr[arrayY][arrayX] == -1) {
            SnakePane.aktivMat = false;
        }
        if(SnakePane.arr[arrayY][arrayX] > 1) {
            SnakePane.gameOver = true;
        }
        SnakePane.arr[arrayY][arrayX] = 1;
    }
    
    public void nyHøyre(int a) {
        tidligereRetning = aRetning;
        animasjonsTeller =0;
        snake.setCenterX(snake.getCenterX()+1);
        animasjonsTeller++;
        aRetning = sRetning.HØYRE;
        arrayX++;
        if(SnakePane.arr[arrayY][arrayX] == -1) {
            SnakePane.aktivMat = false;
        }

        SnakePane.arr[arrayY][arrayX] = 1;
    }
    
    public Circle getSnake() {       
        return snake;
    }
    
    public int getRadius() {
       return (int)snake.getRadius();
    }
    
    @Override
    public void setNyRetning(int a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRetning() {
        return 0;
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
        return 0;
    }


}
