package com.scau.mario;

import com.scau.role.*;
import com.scau.ui.GameFrame;
import com.scau.page.EndPage;
import javax.swing.*;
import java.awt.*;

//自己的角色类
public class Mario extends Thread {

    public GameFrame gf;
    public boolean jumpFlag = true;
    public boolean isGravity = false;
    public boolean isHited=false;
    public boolean iswin=false;
    public int hitNum=-1;

    //马里奥的坐标
    public int x = 180, y = 358;
    //马里奥的速度
    public int xspeed = 5, yspeed = 1;
    //马里奥的宽高
    public int width = 30, height = 32;
    //马里奥的得分
    public static int result = 0;
    //马里奥的图片
    public Image img = new ImageIcon("image/mari1.png").getImage();

    public boolean left = false, right = false, down = false, up = false;

    public String Dir_Up = "Up", Dir_Left = "Left", Dir_Right = "Right", Dir_Down = "Down";


    public Mario(GameFrame gf) {
        this.gf = gf;
        this.Gravity();
    }

    public static void setResult(int result) {
        Mario.result = result;
    }
    public static int getResult() {
        return result;
    }
    public void run() {
        while (true) {
            //向左走

            if (left) {
                //碰撞到了障碍物
                if (hit(Dir_Left)) {
                    this.xspeed = 0;//即停止
                }
                //碰撞到了深渊
                if (!ashore(Dir_Left) && this.y == 358) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 4; j++)
                            gf.mario.y += 3;
                        try {
                            this.sleep(60);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    //死翘翘
                    gameOver(this.y);
                }

                //碰撞到了敌人
				/*if(hitEnery(Dir_Left)) {
					this.y = 500;
					gameOver(this.y);
				}*/
                //碰撞到了花
                if (hitFlower(Dir_Left)) {
                    int n = this.y;
                    for (int j = 0; j < 7; j++) {
                        this.y = 500;
                        this.y = n;
                        this.y = 500;
                        this.y = n;
                        try {
                            this.sleep(200);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    this.y = 500;
                    gameOver(this.y);
                }

                if (hitCoin(Dir_Left)) {
                    result++;
                }
                //马里奥的坐标改变
                if (this.x >= 0) {
                    this.x -= this.xspeed;
                    this.img = new ImageIcon("image/mari_left.gif").getImage();
                }
                this.xspeed = 5;
            }
            //向右走
            if (right) {
                //碰撞到了障碍物
                if (hit(Dir_Right)) {
                    this.xspeed = 0;
                }
                //碰撞到了深渊
                if (!ashore(Dir_Right) && this.y == 358) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 4; j++)
                            gf.mario.y += 3;
                        try {
                            this.sleep(60);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    //死翘翘
                    gameOver(this.y);
                }

                //碰到敌人
				/*if(hitEnery(Dir_Right)) {
	                this.y = 500;
					gameOver(this.y);
				}*/
                //碰撞到了花
                if (hitFlower(Dir_Right)||hitTortoise(Dir_Right)) {
                    int n = this.y;
                    for (int j = 0; j < 7; j++) {
                        this.y = 500;
                        this.y = n;
                        this.y = 500;
                        this.y = n;
                        try {
                            this.sleep(200);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }
                    this.y = 400;
                    gameOver(this.y);
                }
                if (hitCoin(Dir_Right)) {
                    result++;
                }

                //让人物向右移动
                if (this.x < 400) {
                    this.x += this.xspeed;
                    this.img = new ImageIcon("image/mari_right.gif").getImage();
                }
                //背景，草地，障碍物向左移动
                if (this.x >= 400) {
                    gf.bg.x -= this.xspeed;

                    for (PiranhaFlower flower : gf.getFlowerList()) {
                        flower.x -= this.xspeed;
                    }
                    for (Mushroom mush : gf.getMrList()) {
                        if (this.xspeed!=0)
                      mush.isAction=true;
                      mush.x-=this.xspeed;
                      mush.ltop1-=this.xspeed;
                      mush.ltop2-=this.xspeed;
                    }
//

                    for (White white : gf.getwhiteList()) {
                        white.x -= this.xspeed;
                    }
                    if (gf.getMrbolean() == true){
                        gf.getMushroom().x -= this.xspeed;
                        if (this.xspeed!=0)
                            gf.getMushroom().isAction=true;
                        gf.getMushroom().x-=this.xspeed;
                        gf.getMushroom().ltop1-=this.xspeed;
                        gf.getMushroom().ltop2-=this.xspeed;}

                    for (int i = 0; i < gf.enemyList.size(); i++) {
                        Enemy obs = gf.enemyList.get(i);
                        obs.x -= this.xspeed;
                    }
                    for (int i = 0; i < gf.grassList.size(); i++) {
                        Grass grass = gf.grassList.get(i);
                        grass.x -= this.xspeed;
                    }
                    for (int i=0;i<gf.tortoiseList.size();i++){
                        tortoise tortoise=gf.tortoiseList.get(i);
                        tortoise.x-=this.xspeed;
                        if (this.xspeed!=0)
                        tortoise.isAction=true;
                        tortoise.ltop-=this.xspeed;
                        tortoise.rtop-=this.xspeed;
                    }
                    for (int i=0;i<gf.fungusList.size();i++){
                        Fungus fungus=gf.fungusList.get(i);
                        fungus.x-=this.xspeed;
                        if (this.xspeed!=0)
                            fungus.isAction=true;
                        fungus.ltop-=this.xspeed;
                        fungus.rtop-=this.xspeed;
                    }
                    for (int i = 0; i < gf.coinList.size(); i++) {
                        Coin coin = gf.coinList.get(i);
                        coin.x -= this.xspeed;
                    }
                    this.img = new ImageIcon("image/mari_right.gif").getImage();
                }
                this.xspeed = 5;
            }

            //向上跳
            if (up) {
                if (jumpFlag && !isGravity) {
                    jumpFlag = false;
                    new Thread() {
                        public void run() {
                            jump();
                            jumpFlag = true;
                        }
                    }.start();
                }

            }
            if ((hitTortoise("static")||hitFungus("static")||hitFlower("static"))||hit("static")&&!hitTortoise(Dir_Down)){
                this.y = 400;
                gameOver(this.y);
            }

            try {
                this.sleep(20);//0.20s:程序暂停执行指定的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    //游戏结束
    public void gameOver(int hight) {
        gf.dispose();
        if (hight > 358) {
            EndPage bp = new EndPage(this.getResult(),this.iswin);
            setResult(0);
        }
    }

    //向上跳的函数
    public void jump() {
        int jumpHeigh = 0;
        for (int i = 0; i < 150; i++) {
            gf.mario.y -= this.yspeed;
            jumpHeigh++;
            if (hit(Dir_Up) || jumpHeigh == 130) {
                break;
            }
            if (hitCoin(Dir_Up)) {
                result++;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < jumpHeigh; i++) {
            gf.mario.y += this.yspeed;
            if (hit(Dir_Down)) {
                this.yspeed = 0;
            }
            if (hitCoin(Dir_Down)) {
                result++;
            }

            if (hitTortoise(Dir_Down)&&hitNum>=0) {
                tortoise die=gf.tortoiseList.get(hitNum);
                die.isdeath=true;
                this.yspeed=0;
                //踩死敌人
            }
            if (hitFungus(Dir_Down)&&hitNum>=0) {
                Fungus die=gf.fungusList.get(hitNum);
                die.isdeath=true;
                this.yspeed=0;
                //踩死敌人
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.yspeed = 1;//还原速度

    }

    //检测障碍物碰撞     Rectangle 指定坐标空间中的一个区域，通过坐标空间中 Rectangle 对象左上方的点 (x,y)、宽度和高度可以定义这个区域
    public boolean hit(String dir) {
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
        Rectangle rect = null;   //障碍物

        for (int i = 0; i < gf.enemyList.size(); i++) {
            Enemy obs = gf.enemyList.get(i);

            if (dir.equals("Left")) {
                rect = new Rectangle(obs.x + 2, obs.y, obs.width, obs.height);
            } else if (dir.equals("Right")) {
                rect = new Rectangle(obs.x - 2, obs.y, obs.width, obs.height);
            } else if (dir.equals("Up")) {
                rect = new Rectangle(obs.x, obs.y + 1, obs.width, obs.height);
            } else if (dir.equals("Down")) {
                rect = new Rectangle(obs.x, obs.y - 2, obs.width, obs.height);
            } else{
                rect = new Rectangle(obs.x, obs.y + 1, obs.width, obs.height);
            }
            //将两个Rectangle对象依附在既定好的两个矩形上，通过线程时时判断是否相交
            /**        new		*/
            if (myrect.intersects(rect)) {
                if (obs instanceof Mbox && dir.equals(Dir_Up)&&!isHited) {
                    ((Mbox) obs).setHit(true);
                    isHited=true;
                    ((Mbox)obs).changeImg();

                }
                if (obs instanceof Castle ) {
                    iswin=true;
                    this.y = 500;
                    gameOver(this.y);


                }
//                if (obs instanceof Cbox && dir.equals(Dir_Up)) {
//                    ((Cbox) obs).setHit(true);
//
//                }
//                if (obs instanceof Brick && dir.equals(Dir_Up)) {
//                    ((Brick) obs).setHit(true);
//                    System.out.println("duang");
////
//                }
                return true;
            }
            Mushroom mr = gf.mr;
            if (dir.equals("Left")) {
                rect = new Rectangle(mr.x + 2, mr.y, mr.width, mr.height);
            } else if (dir.equals("Right")) {
                rect = new Rectangle(mr.x - 2, mr.y, mr.width, mr.height);
            } else if (dir.equals("Up")) {
                rect = new Rectangle(mr.x, mr.y + 2, mr.width, mr.height);
            } else if (dir.equals("Down")) {
                rect = new Rectangle(mr.x, mr.y - 2, mr.width, mr.height);
            }else{
                rect = new Rectangle(mr.x, mr.y + 2, mr.width, mr.height);
            }
            if (myrect.intersects(rect)) {
                gf.mr.x = 0;
                gf.mr.y = 1000;
                result+=10;
                //new Thread(new MusicPlayer("audio\\得到金币.mp3")).start();

        }
        }
       return false;

   }


        //检测花碰撞
        public boolean hitFlower (String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
            Rectangle rect = null;   //花

            for (int i = 0; i < gf.flowerList.size(); i++) {
                PiranhaFlower flower = gf.flowerList.get(i);

                if (dir.equals("Left")) {
                    rect = new Rectangle(flower.x + 2, flower.y, flower.width, flower.height);
                } else if (dir.equals("Right")) {
                    rect = new Rectangle(flower.x - 2, flower.y, flower.width, flower.height);
                } else if (dir.equals("Up")) {
                    rect = new Rectangle(flower.x, flower.y + 2, flower.width, flower.height);
                } else if (dir.equals("Down")) {
                    rect = new Rectangle(flower.x, flower.y - 2, flower.width, flower.height);
                }else{
                    rect = new Rectangle(flower.x, flower.y + 2, flower.width, flower.height);
                }
                //判断是否相交
                if (myrect.intersects(rect))
                    return true;
            }
            return false;
        }
    public boolean hitTortoise (String dir){
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
        Rectangle rect = null;   //敌人

        for (int i = 0; i < gf.tortoiseList.size(); i++) {
            tortoise tortoise = gf.tortoiseList.get(i);
            if (dir.equals("Left")) {
                rect = new Rectangle(tortoise.x + 2, tortoise.y, tortoise.width, tortoise.height);
            } else if (dir.equals("Right")) {
                rect = new Rectangle(tortoise.x - 2, tortoise.y, tortoise.width, tortoise.height);
            } else if (dir.equals("Up")) {
                rect = new Rectangle(tortoise.x, tortoise.y + 2, tortoise.width, tortoise.height);
            }else if (dir.equals("Down")) {
                rect = new Rectangle(tortoise.x, tortoise.y - 3, tortoise.width, tortoise.height);
                hitNum=i;
            }else{
                rect = new Rectangle(tortoise.x-2, tortoise.y , tortoise.width, tortoise.height);
            }

            //判断是否相交
            if (myrect.intersects(rect))
                return true;

        }
        return false;
    }
    public boolean hitFungus (String dir){
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
        Rectangle rect = null;   //敌人

        for (int i = 0; i < gf.fungusList.size(); i++) {
            Fungus fungus = gf.fungusList.get(i);
            if (dir.equals("Left")) {
                rect = new Rectangle(fungus.x + 2, fungus.y, fungus.width, fungus.height);
            } else if (dir.equals("Right")) {
                rect = new Rectangle(fungus.x - 2, fungus.y, fungus.width, fungus.height);
            } else if (dir.equals("Up")) {
                rect = new Rectangle(fungus.x, fungus.y + 2, fungus.width, fungus.height);
            }else if (dir.equals("Down")) {
                rect = new Rectangle(fungus.x, fungus.y - 2, fungus.width, fungus.height);
                hitNum=i;
            }else{
                rect = new Rectangle(fungus.x-2, fungus.y , fungus.width, fungus.height);
            }

            //判断是否相交
            if (myrect.intersects(rect))
                return true;

        }
        return false;
    }

        //检测得分
        public boolean hitCoin (String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
            Rectangle rect = null;
            for (int i = 0; i < gf.coinList.size(); i++) {
                Coin obs = gf.coinList.get(i);
                if (dir.equals("Left")) {
                    rect = new Rectangle(obs.x + 1, obs.y, obs.width, obs.height);
                } else if (dir.equals("Right")) {
                    rect = new Rectangle(obs.x - 1, obs.y, obs.width, obs.height);
                } else if (dir.equals("Up")) {
                    rect = new Rectangle(obs.x, obs.y + 1, obs.width, obs.height);
                } else if (dir.equals("Down")) {
                    rect = new Rectangle(obs.x, obs.y - 1, obs.width, obs.height);
                }

                if (myrect.intersects(rect)) {
                    obs.x = 0;
                    obs.y = 0;
                    //new Thread(new MusicPlayer("audio\\得到金币.mp3")).start();
                    result++;
                }
            }

            return false;
        }

        //检测敌人碰撞
//        public boolean hitEnery (String dir){
//            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
//            Rectangle rect = null;   //敌人
//
//            for (int i = 0; i < gf.obstacleList.size(); i++) {
//                Enemy enery = gf.obstacleList.get(i);
//
//                if (dir.equals("Left")) {
//                    rect = new Rectangle(enery.x + 1, enery.y, enery.width, enery.height);
//                } else if (dir.equals("Right")) {
//                    rect = new Rectangle(enery.x - 1, enery.y, enery.width, enery.height);
//                } else if (dir.equals("Up")) {
//                    rect = new Rectangle(enery.x, enery.y + 2, enery.width, enery.height);
//                } else if (dir.equals("Down")) {
//                    rect = new Rectangle(enery.x, enery.y - 2, enery.width, enery.height);
//                }
//                //判断是否相交
//                if (myrect.intersects(rect))
//                    return false;
//            }
//            return true;
//        }

        //是否在草地上
        public boolean ashore(String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //马里奥
            Rectangle rect = null;   //草地

            for (int i = 0; i < gf.grassList.size(); i++) {
                Grass grass = gf.grassList.get(i);

                if (dir.equals("Left")) {
                    rect = new Rectangle(grass.x + 2, grass.y - 2, grass.width, grass.height);
                } else if (dir.equals("Right")) {
                    rect = new Rectangle(grass.x - 2, grass.y - 2, grass.width, grass.height);
                }

			/*else if(dir.equals("Up")){
				rect = new Rectangle(grass.x,grass.y-2,grass.width,grass.height);
			}else if(dir.equals("Down")){
				rect = new Rectangle(grass.x,grass.y-2,grass.width,grass.height);
			}*/
                //将两个Rectangle对象依附在既定好的两个矩形上，通过线程时时判断是否相交
                if (myrect.intersects(rect)) {
                    return true;
                }
            }
            return false;
        }

        //检查是否贴地
        public void Gravity() {
            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (true) {
                            if (!jumpFlag) {
                                break;
                            }
                            if (hit(Dir_Down)) {
                                isGravity = false;
                                break;
                            }
                            if (y >= 358) {
                                isGravity = false;
                            } else {
                                isGravity = true;
                                y += yspeed;
                            }
                            try {
                                sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }.start();

        }
    }

