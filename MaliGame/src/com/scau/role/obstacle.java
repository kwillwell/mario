package com.scau.role;

import java.awt.*;

public class obstacle {
    public int x,y;
    public int width,height;
    public Image img;
    public obstacle(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img=img;
    }
}
