package com.scau.role;

import javax.swing.*;
import java.awt.*;

public class tortoise extends Enemy {
    public int x = 600, y = 358;//乌龟的坐标
    public double doublex;
    public boolean isdeath = false;
    public int ltop, rtop;//乌龟需要碰撞检测
    public int width = 30, height = 32;//乌龟的大小
    public double vx = 1;    //上升速度
    public boolean isAction = false;
    private int flag = 1;
    public Image img;
    private Image img1 = new ImageIcon("image/Ltortoise1.png").getImage();
    private Image img2 = new ImageIcon("image/Ltortoise2.png").getImage();
    private Image img3 = new ImageIcon("image/rtortoise1.png").getImage();
    private Image img4 = new ImageIcon("image/rtortoise2.png").getImage();
    private Image img5 = new ImageIcon("image/shell1.png").getImage();
    private Image img6 = new ImageIcon("image/shell2.png").getImage();
    private Image img7 = new ImageIcon("image/shell3.png").getImage();
    private Image img8 = new ImageIcon("image/shell4.png").getImage();

    public void changeImg() {
        if (flag == 1) {
            if (img == img1) {
                img = img2;
            } else if (img == img2) {
                img = img1;
            }
        } else {
            if (img == img3) {
                img = img4;
            } else if (img == img4) {
                img = img3;
            }
        }

    }

    public tortoise(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);

 //       this.x = 550;
        this.y =y;
        this.doublex = x;
        this.ltop = x - 50;
        this.rtop = x + 50;
//        this.width = width;
//        this.height = height;
//        this.img=img;
    }

    public void move() {
        if (isdeath) {
           img=img4;
           vx=0;
           img=img5;

           this.y=50;

        } else {
            if (x < ltop) {//往右走
                vx = 1;
                img = img3;
                flag = 2;
                if (isAction) {
                    doublex -= vx;
                } else {
                    doublex += vx;
                }

            } else if (x > rtop) {//往左走

                vx = -1;
                img = img1;
                flag = 1;
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
