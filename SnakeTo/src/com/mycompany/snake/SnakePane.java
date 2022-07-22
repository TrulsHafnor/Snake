/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import static java.lang.Thread.sleep;

/**
 *
 * @author ASUS
 */
public class SnakePane {

    public static BorderPane bPane; // Main GUI
    private Pane pane;
    public TilePane tPane;
    public static int xLengde = 15; //Hvor langt det skal være i bredden
    public static int yLengde = 15; //hvor langt det skal være i høyde
    private int antallRec = xLengde*yLengde;
    public static int xStrl; //Strsl på en x
    public static int yStrl; //Strsl på en y
    public static Animation animation;
    public static double speed = 4.0;
    public Snake snake;
    public static ArrayList<Figurer> snakeKropp = new ArrayList<>();
    public static ArrayList<Mat> matListe = new ArrayList<>();
    public static boolean aktivMat = true;
    public static boolean gameOver = false;

    public static int[][] arr = {
                    { 0, 0, 0, 0,0 ,0 ,0 , 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 3, 2, 1, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0 ,0 ,0, 0, 0, 0, 0, 0, 0, 0, 0 },
    }; //Laget en array av banen
    
    
    public SnakePane(int x, int y) {
        bPane= new BorderPane();      
                setMap(x,y);
        bPane.setCenter(pane);
        animation = new Timeline(
                new KeyFrame(Duration.millis(speed), e -> animasjon(x,y)));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play(); // Start animation
    }
    
    public void animasjon (int x, int y) {
        if ( (snake.getRadius() + snake.getX() > x) || (snake.getX() - snake.getRadius() < 0)
             || (snake.getRadius() + snake.getY() > y) || (snake.getY() - snake.getRadius() < 0)) {
            stop();
        } 
        else if (Main.retning.equals(Main.retning.START)) {
            //Snake står stille når man starter spille, frem til man trykker på en knapp.
        }
   
        else {
            snake.move(1); //tom verdi
            bevegKropp();
            if (!aktivMat) {
                matListe.get(matListe.size()-1).removeMat();
                addKropp();
                addMat();
                animationSpeedUp();
            }
            if(gameOver) {
                stop();
            }
        }
    }

    public void animationSpeedUp() { //Dette kunne også vært en thread eventuelt
        double newSpeed = animation.getCurrentRate();
        animation.setRate(newSpeed + 0.05); //Endre speed her per mat du spiser
    }
   
    public void stop(){
        animation.stop();
    }

    public void play(){
        animation.play();
    }
    
    // denne lager nå banen perfekt på tilepane
    public TilePane setTilePane(int x, int y) {
        tPane = new TilePane();
            tPane.setMaxHeight(y);
            tPane.setMinHeight(y);
            tPane.setMaxWidth(x);
            tPane.setMinWidth(x);
        for (int i = 0;i<antallRec;i++) {
            xStrl = x/xLengde;
            yStrl = y/yLengde;        
            Rectangle r = new Rectangle();
                r.setX(0);
                r.setY(0);
                r.setWidth(xStrl);
                r.setHeight(yStrl);
                r.setFill(Color.LIGHTGRAY);
            tPane.getChildren().addAll(r);
        }
        return tPane;
    }
    
    
    public void setMap(int x,int y) {
        pane = new Pane();
            pane.setMaxHeight(y);
            pane.setMinHeight(y);
            pane.setMaxWidth(x);
            pane.setMinWidth(x);
                pane.getChildren().addAll(setTilePane(x,y));
        leggTilSnake(yStrl/2);
        printBane();
    }
    
    public void leggTilSnake(int radius) {
        snake = new Snake((xStrl*((int)xLengde/2))+radius,(yStrl*((int)yLengde/2))+radius, xLengde/2,yLengde/2, radius);
        pane.getChildren().add(snake.getSnake());
        arr[yLengde/2][xLengde/2] = 1;
        snakeKropp.add(snake);
        leggTilStartKropp(radius);
        addMat();
    }

    private void leggTilStartKropp(int radius) {
        for (int i = 1; i < 3; i++ ) {
            SnakeKropp kropp = new SnakeKropp((xStrl * ((int) xLengde / 2) -(xStrl * i)) + radius, (yStrl * ((int) yLengde / 2)) + radius, (xLengde / 2) - i, yLengde / 2, radius);
            snakeKropp.add(kropp);
            arr[yLengde/2][(xLengde/2)-i] = snakeKropp.size();
            pane.getChildren().add(kropp.getKropp());
        }
    }

    private void addKropp () {
        int retning = snakeKropp.get(snakeKropp.size()-1).getRetning();
        int arrayY = snakeKropp.get(snakeKropp.size()-1).getArrayY();
        int arrayX = snakeKropp.get(snakeKropp.size()-1).getArrayX();
        int x = snakeKropp.get(snakeKropp.size()-1).getX();
        int y = snakeKropp.get(snakeKropp.size()-1).getY();
        int tempTeller = snakeKropp.get(snakeKropp.size()-1).getAnimasjonsteller();//animasjonsteller til den foran

        // 1= NED, 2= OPP, 3= VENSTRE, 4= HØYRE
        if (retning == 1) {
            y -= yStrl;
            arrayY -= 1;
        }
        else if (retning ==2) {
            y += yStrl;
            arrayY+=1;
        }
        else if(retning == 3) {
            x += xStrl;
            arrayX+=1;
        }
        else if (retning==4) {
            x -= xStrl;
            arrayX-=1;
        }

        SnakeKropp kropp = new SnakeKropp(x,y,arrayX,arrayY,yStrl/2,retning,tempTeller);
        snakeKropp.add(kropp);
        pane.getChildren().add(kropp.getKropp());
        arr[arrayY][arrayX] = snakeKropp.size();
    }

    public void bevegKropp() {
        for(int i=1;i<snakeKropp.size();i++) {
            snakeKropp.get(i).move(i+1);
        }
    }

    public void addMat() {
        while(true) {
            int x = randomX();
            int y = randomY();
            if (arr[y][x] == 0) {
                Mat mat = new Mat(xStrl*x + xStrl/2, yStrl*y + yStrl/2,x,y,yStrl/2);
                matListe.add(mat);
                pane.getChildren().add(mat.getMat());
                arr[y][x] = -1;
                aktivMat = true;
                break;
            }
        }
    }

    public int randomX() {
        return (int)(Math.random() * (xLengde));
    }

    public int randomY() {
        return (int)(Math.random() * (yLengde));
    }

    public void printBane() {
        //Hjelpe metode for å printe ut banen
        for (int i = 0; i < arr.length; i++) {
            System.out.println("");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
    }
}
