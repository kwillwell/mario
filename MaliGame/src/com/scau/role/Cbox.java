package com.scau.role;

import java.awt.*;

public class Cbox extends Enemy{

    public boolean hit = false;

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public int minY = y - 10;

    public int maxY = y;

    public int vY = 1;

    public void move(){
        if (y<minY)
            vY = -1;
        y -= vY;
        if (y>=maxY){
            hit = false;
            vY =  1;
        }
    }

    public Cbox(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
    }

}

