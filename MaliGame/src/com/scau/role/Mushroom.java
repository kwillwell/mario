package com.scau.role;

import com.scau.ui.GameFrame;

import javax.swing.plaf.TableHeaderUI;
import java.awt.*;

public class Mushroom {
    public int x,y;
    public int width,height;
    public int vx=1,vy=2;
    public double ltop1,ltop2;
    public double changex,changey;
    public Image img;

    public boolean isAction = false;
    GameFrame gf;

    public Mushroom(GameFrame gf) {
        this.gf = gf;
    }


    public Mushroom(int x, int y, int width, int height, Image img) {
        this.x =x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img=img;
        this.ltop1=this.x+120;
        this.ltop2=this.x+240;
        this.changex=x;
        this.changey=y;

    }
    public  void  move() {
        if (this.y>=1000){
            return;
        }
        if (this.x < ltop2) {
            if (this.x < ltop1) {
                if (isAction) {
                    changex -=2*vx;
                } else {
                    changex += vx;
                }

            }
            if (this.x >= ltop1 && this.y <= 360) {
                changey += vy;
                if (isAction) {
                    changex -=2*vx;
                } else {
                    changex += vx;
                }


            }else{
                if (isAction) {
                    changex -= 2*vx;
                } else {
                    changex += vx;
                }

            }

        }else{
            changey+=vy;

        }
        this.y=(int)changey;
        this.x = (int) changex;
      isAction=false;
    }
}
