package com.scau.mario;

import com.scau.role.*;
import com.scau.ui.GameFrame;
import com.scau.page.EndPage;
import javax.swing.*;
import java.awt.*;

//�Լ��Ľ�ɫ��
public class Mario extends Thread {

    public GameFrame gf;
    public boolean jumpFlag = true;
    public boolean isGravity = false;
    public boolean isHited=false;
    public boolean iswin=false;
    public int hitNum=-1;

    //����µ�����
    public int x = 180, y = 358;
    //����µ��ٶ�
    public int xspeed = 5, yspeed = 1;
    //����µĿ��
    public int width = 30, height = 32;
    //����µĵ÷�
    public static int result = 0;
    //����µ�ͼƬ
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
            //������

            if (left) {
                //��ײ�����ϰ���
                if (hit(Dir_Left)) {
                    this.xspeed = 0;//��ֹͣ
                }
                //��ײ������Ԩ
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
                    //������
                    gameOver(this.y);
                }

                //��ײ���˵���
				/*if(hitEnery(Dir_Left)) {
					this.y = 500;
					gameOver(this.y);
				}*/
                //��ײ���˻�
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
                //����µ�����ı�
                if (this.x >= 0) {
                    this.x -= this.xspeed;
                    this.img = new ImageIcon("image/mari_left.gif").getImage();
                }
                this.xspeed = 5;
            }
            //������
            if (right) {
                //��ײ�����ϰ���
                if (hit(Dir_Right)) {
                    this.xspeed = 0;
                }
                //��ײ������Ԩ
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
                    //������
                    gameOver(this.y);
                }

                //��������
				/*if(hitEnery(Dir_Right)) {
	                this.y = 500;
					gameOver(this.y);
				}*/
                //��ײ���˻�
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

                //�����������ƶ�
                if (this.x < 400) {
                    this.x += this.xspeed;
                    this.img = new ImageIcon("image/mari_right.gif").getImage();
                }
                //�������ݵأ��ϰ��������ƶ�
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

            //������
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
                this.sleep(20);//0.20s:������ִͣ��ָ����ʱ��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }



    //��Ϸ����
    public void gameOver(int hight) {
        gf.dispose();
        if (hight > 358) {
            EndPage bp = new EndPage(this.getResult(),this.iswin);
            setResult(0);
        }
    }

    //�������ĺ���
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
                //��������
            }
            if (hitFungus(Dir_Down)&&hitNum>=0) {
                Fungus die=gf.fungusList.get(hitNum);
                die.isdeath=true;
                this.yspeed=0;
                //��������
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.yspeed = 1;//��ԭ�ٶ�

    }

    //����ϰ�����ײ     Rectangle ָ������ռ��е�һ������ͨ������ռ��� Rectangle �������Ϸ��ĵ� (x,y)����Ⱥ͸߶ȿ��Զ����������
    public boolean hit(String dir) {
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
        Rectangle rect = null;   //�ϰ���

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
            //������Rectangle���������ڼȶ��õ����������ϣ�ͨ���߳�ʱʱ�ж��Ƿ��ཻ
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
                //new Thread(new MusicPlayer("audio\\�õ����.mp3")).start();

        }
        }
       return false;

   }


        //��⻨��ײ
        public boolean hitFlower (String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
            Rectangle rect = null;   //��

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
                //�ж��Ƿ��ཻ
                if (myrect.intersects(rect))
                    return true;
            }
            return false;
        }
    public boolean hitTortoise (String dir){
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
        Rectangle rect = null;   //����

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

            //�ж��Ƿ��ཻ
            if (myrect.intersects(rect))
                return true;

        }
        return false;
    }
    public boolean hitFungus (String dir){
        Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
        Rectangle rect = null;   //����

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

            //�ж��Ƿ��ཻ
            if (myrect.intersects(rect))
                return true;

        }
        return false;
    }

        //���÷�
        public boolean hitCoin (String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
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
                    //new Thread(new MusicPlayer("audio\\�õ����.mp3")).start();
                    result++;
                }
            }

            return false;
        }

        //��������ײ
//        public boolean hitEnery (String dir){
//            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
//            Rectangle rect = null;   //����
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
//                //�ж��Ƿ��ཻ
//                if (myrect.intersects(rect))
//                    return false;
//            }
//            return true;
//        }

        //�Ƿ��ڲݵ���
        public boolean ashore(String dir){
            Rectangle myrect = new Rectangle(this.x, this.y, this.width, this.height);  //�����
            Rectangle rect = null;   //�ݵ�

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
                //������Rectangle���������ڼȶ��õ����������ϣ�ͨ���߳�ʱʱ�ж��Ƿ��ཻ
                if (myrect.intersects(rect)) {
                    return true;
                }
            }
            return false;
        }

        //����Ƿ�����
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

