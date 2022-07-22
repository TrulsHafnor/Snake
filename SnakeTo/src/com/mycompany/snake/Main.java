package com.mycompany.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;


/**
 * JavaFX App
 */
public class Main extends Application {

    public enum Retning {
        OPP,
        NED,
        HØYRE,
        VENSTRE,
        START
    }
    static Retning retning;

    @Override
    public void start(Stage stage) {
        int FINAL_X = 600;
        int FINAL_Y = 600;

        retning = Retning.START;

       SnakePane mainPane = new SnakePane(FINAL_X,FINAL_Y);
        
        var scene = new Scene(SnakePane.bPane, FINAL_X, FINAL_Y);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(e -> {
            if (Snake.aRetning != Snake.sRetning.NED && e.getCode() == KeyCode.UP) {
                retning = Retning.OPP;
            }
            else if (Snake.aRetning != Snake.sRetning.OPP && e.getCode() == KeyCode.DOWN) {
                retning = Retning.NED;
            }
            else if (Snake.aRetning != Snake.sRetning.HØYRE && e.getCode() == KeyCode.LEFT) {
                retning = Retning.VENSTRE;
            }
            else if (Snake.aRetning != Snake.sRetning.VENSTRE && e.getCode() == KeyCode.RIGHT) {
                retning = Retning.HØYRE;
            }
        });
        /*
        scene.setOnKeyPressed(e -> {
            if (Snake.aRetning != Snake.sRetning.NED && e.getCode() == KeyCode.W) {
                retning = Retning.OPP;
            }
            else if (Snake.aRetning != Snake.sRetning.OPP && e.getCode() == KeyCode.S) {
                retning = Retning.NED;
            }
            else if (Snake.aRetning != Snake.sRetning.HØYRE && e.getCode() == KeyCode.A) {
                retning = Retning.VENSTRE;
            }
            else if (Snake.aRetning != Snake.sRetning.VENSTRE && e.getCode() == KeyCode.D) {
                retning = Retning.HØYRE;
            }
        });*/
        
    }

    public static void main(String[] args) {
        launch();
    }

}