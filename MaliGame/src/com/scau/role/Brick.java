package com.scau.role;
import java.awt.Image;
import java.util.ArrayList;

//×©Í·Àà
public class Brick extends Enemy {
    public Brick(int x, int y, int width, int height, Image img) {

        super(x, y, width, height, img);
    }
    public boolean hit=false;

    public void setHit(boolean hit) {
        this.hit = hit;
    }
    public boolean isHit(){
        return hit;
    }
    public int minY=y-10;
    public int maxY=y;
    public  int vy=1;
    public void move(){
        if(y<minY)
            vy=-1;
            y-=vy;
            if (y>maxY){
                hit=false;
                vy=1;
            }

    }
    public boolean hascoin(ArrayList<Coin> coinlist){
        for (Coin coin:coinlist){
            if(coin.equal(this.x,232)==true)
                return true;

        }
        return false;
    }
}
