package com.scau.role;

import javax.swing.*;
import java.awt.*;


    public class Mbox extends Enemy{
        public Mbox(int x, int y, int width, int height, Image img) {
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
        public void changeImg(){
            if (hit){
                this.img=new ImageIcon("image/coin_brick_null.png").getImage();
            }
        }
        public void move(){
            if(y<minY)
                vy=-1;
            y-=vy;
            if (y>maxY){
                hit=false;
                vy=1;
            }

        }



    }

