package com.scau.role;

import javax.swing.*;
import java.awt.*;

public class Fungus extends Enemy {
    public int x = 600, y = 358;//真菌的坐标
    public double doublex;
    public boolean isdeath = false;
    public int ltop, rtop;//真菌需要碰撞检测
    public int width = 30, height = 32;//真菌的大小
    public double vx = 1;    //上升速度
    public boolean isAction = false;
  //  private int flag = 1;
    public Image img;
    private Image img1 = new ImageIcon("image/fungus1.png").getImage();
    private Image img2 = new ImageIcon("image/fungus2.png").getImage();
    private Image img3 = new ImageIcon("image/fungus3.png").getImage();



    public void changeImg() {
            if (img == img1) {
                img = img2;
            } else if (img == img2) {
                img = img1;
            }


    }

    public Fungus(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);

//        this.x = 550;
//        this.y = 358;
        this.doublex = x;
        this.ltop = x - 50;
        this.rtop = x + 50;
//        this.width = width;
//        this.height = height;
//        this.img=img;
    }

    public void move() {
        if (isdeath) {
            img=img3;
            vx=0;
            this.y=100;

        } else {
            if (x < ltop) {//往右走
                vx = 1;
                img = img1;
                if (isAction) {
                    doublex -= vx;
                } else {
                    doublex += vx;
                }

            } else if (x > rtop) {//往左走
                vx = -1;
                img = img1;

                if (isAction) {
                    doublex += 5 * vx;
                } else {
                    doublex += vx;
                }

            } else {
                if (isAction && vx == -1) {
                    doublex += 6 * vx;
                } else if (isAction && vx == 1) {
                    doublex -= 4 * vx;
                }
                doublex += vx;
            }
            this.x = (int) doublex;
            isAction = false;
        }

    }
}
