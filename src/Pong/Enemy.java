package Pong;


import java.awt.event.KeyEvent;


public class Enemy {

    private int dy;
    private int x;
    private int y;
    private int w = 50;
    private int h = 200;


    public Enemy(int positionX, int positionY) {

        this.x = positionX;
        this.y = positionY;

        loadPlayer();
    }

    public void loadPlayer() {


    }

    public void move() {
        if (y >= 0 && y < 550){y += dy;}
        if (y < 0){y = 1;}
        if (y >= 550){y = 548;}

    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }


}
