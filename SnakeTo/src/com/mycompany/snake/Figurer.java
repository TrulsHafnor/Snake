/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snake;

/**
 *
 * @author ASUS
 */
public abstract class Figurer {
    protected int x, y, arrayX, arrayY;
 
    
    public Figurer (int x, int y, int arrayX, int arrayY) {
        this.x=x;
        this.y=y;
        this.arrayX = arrayX;
        this.arrayY = arrayY;
    }
    
    public abstract int getX ();
    public abstract int getY ();
    public abstract void høyre();
    public abstract void venstre();
    public abstract void ned();
    public abstract void opp();
    public abstract void nyHøyre(int a);
    public abstract void nyVenstre(int a);
    public abstract void nyNed(int a);
    public abstract void nyOpp(int a);
    public abstract void move(int verdi);
    public abstract void setNyRetning(int a);
    public abstract int getRetning();
    public abstract int getArrayX();
    public abstract int getArrayY();
    public abstract int getAnimasjonsteller();
}
