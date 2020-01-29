package com.scau.ui;

import com.scau.mario.Mario;
import com.scau.role.*;
import com.scau.util.KeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import com.scau.util.Map1;

/**
   主体窗口界面：展示角色。

 */
public class GameFrame extends JFrame{
	public Mario mario;
	public PiranhaFlower flower;
	public Grass grass;
	public Enemy pipe,brick,castle,box;
	public White white;
	public Coin coin;
	public tortoise tortoise;
	public Fungus fungus;
	//背景图片
	public BackgroundImage bg ;

	public ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	public ArrayList<White> whiteList = new ArrayList<White>();
	//public ArrayList<Enery> EneryList = new ArrayList<Enery>();
	public ArrayList<Grass> grassList = new ArrayList<Grass>();
	public ArrayList<Coin> coinList = new ArrayList<Coin>();
	public ArrayList<Mushroom> mrList = new ArrayList<Mushroom>();
	public ArrayList<PiranhaFlower> flowerList = new ArrayList<PiranhaFlower>();
	public ArrayList<tortoise> tortoiseList=new ArrayList<>();
	public ArrayList<Fungus> fungusList=new ArrayList<>();
	public Mushroom mr;
	boolean mrb=false;
	public int [][] map =null;
	public Mushroom getMushroom() {
		return mr;
	}
	public boolean getMrbolean() {
		return mrb;
	}

	public ArrayList<White> getwhiteList() {
		return whiteList;
	}
	public ArrayList<Coin> getCoinList(){
		return coinList;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public ArrayList<tortoise> getTortoiseList() {
		return tortoiseList;
	}


	//构造函数里面初始化背景图片和马里奥对象
	public GameFrame() throws Exception {

		mario = new Mario(this);
		mario.start();
		mr=new Mushroom(this);
		Map1 mp= new Map1();
		bg = new BackgroundImage();
		//Thread bgm = new Thread(new LoopMusicPlayer("audio\\bgm.mp3"));
		//bgm.start();
		//窗体重绘线程
		new Thread(){
			public void run(){
				while(true){
					//重绘窗体
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		map=mp.readMap();
		//读取地图，并配置地图
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				//读取到的是1，画砖头
				if(map[i][j]==1){
					brick = new Brick(j*30,i*30,30,30,new ImageIcon("image/brick.png").getImage());
                    enemyList.add(brick);
				}

				if(map[i][j]==2){
					box = new Mbox(j*30,i*30,30,30,new ImageIcon("image/coin_brick.png").getImage());
                    enemyList.add(box);
				}
				if(map[i][j]==21){
					 coin= new Coin(
							j*30, i*30, 30, 30, new ImageIcon("image/coin2.png").getImage());
					coinList.add(coin);

				}
				if (map[i][j]==20){
					tortoise= new tortoise(j*30,i*30-5,30,30,new ImageIcon("image/Rtortoise1.png").getImage());
					tortoiseList.add(tortoise);
				}
				if (map[i][j]==98){
					fungus= new Fungus(j*30+100,i*30,30,30,new ImageIcon("image/fungus1.png").getImage());
					fungusList.add(fungus);
				}
				if(map[i][j]==3){
					pipe = new Pipe(j*30,i*30,60,120,new ImageIcon("image/pipe.png").getImage());
                    enemyList.add(pipe);
				}

				if(map[i][j]==31){
					flower = new PiranhaFlower(j*30+15, i*30-10, 30, 63, new ImageIcon("image/flower1.1.png").getImage());
					flowerList.add(flower);
				}
				if(map[i][j]==4){
					grass = new Grass(j*30-5,i*30,36,31,new ImageIcon("image/grass_L1.png").getImage());
					grassList.add(grass);
				}
				if(map[i][j]==5){
					grass = new Grass(j*30,i*30,31,31,new ImageIcon("image/grass1.png").getImage());
					grassList.add(grass);
				}
				if(map[i][j]==6){
					grass = new Grass(j*30,i*30,31,31,new ImageIcon("image/grass2.png").getImage());
					grassList.add(grass);
				}
				if(map[i][j]==7){
					grass = new Grass(j*30,i*30,31,31,new ImageIcon("image/grass3.png").getImage());
					grassList.add(grass);
				}
				if(map[i][j]==8){
					grass = new Grass(j*30,i*30,30,30,new ImageIcon("image/grass_R1.png").getImage());
					grassList.add(grass);
				}

				if(map[i][j]==9){
					grass = new Grass(j*30,i*30,31,31,new ImageIcon("image/6.png").getImage());
					grassList.add(grass);
				}

				if(map[i][j]==10){
					grass = new Grass(j*30,i*30,31,31,new ImageIcon("image/grass_soil.png").getImage());
					grassList.add(grass);
				}

				if(map[i][j]==11){
					white = new White(j*30,i*30,31,31,new ImageIcon("image/white.png").getImage());
					whiteList.add(white);
				}
				if(map[i][j]==99){
					castle =new Castle(j*30,i*30,180,160,new ImageIcon("image/tower1.png").getImage());
                    enemyList.add(castle);
				}
			}
		}
	}

	public void initFrame(){
		//设置窗体相关属性
		this.setSize(800,450);
		this.setTitle("超级玛丽");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);

		//该窗体添加键盘监听
		KeyListener kl = new KeyListener(this);
		this.addKeyListener(kl);
	}

	public ArrayList<tortoise> gettortoiseList() {
		return tortoiseList;
	}

	public ArrayList<PiranhaFlower> getFlowerList() {
		return flowerList;
	}

	public ArrayList<Mushroom> getMrList() {
		return mrList;
	}

	public void paint(Graphics g) {
		//利用双缓冲画背景图片和马里奥
		BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
		Graphics big =bi.getGraphics();
		big.drawImage(bg.img, bg.x, bg.y, null);

		//画怪物
		for (PiranhaFlower flower:flowerList){
			flower.move();
			flower.changeImg();
			big.drawImage(flower.img, flower.x, flower.y,null);
		}
		for (tortoise tortoise:tortoiseList){
			tortoise.move();
			tortoise.changeImg();
			big.drawImage(tortoise.img, tortoise.x, tortoise.y,null);
		}
		for (Fungus fungus:fungusList){
			fungus.move();
			fungus.changeImg();
			big.drawImage(fungus.img, fungus.x, fungus.y,null);
		}
		//填补
		for (White white:whiteList){
			big.drawImage(white.img, white.x, white.y, white.width, white.height,null);//绘制金币
		}
		//障碍物
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy obs =enemyList.get(i);
			Mushroom mushroom=null;

			if (obs instanceof Brick && ((Brick) obs).isHit()){
				((Brick) obs).move();
			}

			if (obs instanceof Mbox && ((Mbox) obs).isHit()){
				((Mbox) obs).move();
				mrb=true;
				mushroom=new Mushroom(obs.x, obs.y-30, 30, 30, new ImageIcon("image/mushroom.png").getImage());
				mr=mushroom;
			}

			big.drawImage(obs.img, obs.x, obs.y, obs.width, obs.height,null);//绘制当前可用的指定图像的大小。
		}
		//草地
		for (int i = 0; i < grassList.size(); i++) {
			Grass grass =grassList.get(i);
			big.drawImage(grass.img, grass.x, grass.y, grass.width, grass.height,null);//绘制草地
		}
		//金币
		for (Coin coin:coinList){
			//coin.changeImg();
			big.drawImage(coin.img, coin.x, coin.y, coin.width, coin.height,null);//绘制金币
		}
		//敌人类
//		for (int i = 0; i < obstacleList.size(); i++) {
//			Enemy enery = obstacleList.get(i);
//			if (enery  instanceof PiranhaFlower ){
//				PiranhaFlower flower=((PiranhaFlower) enery);
//				flower.move();
//				flower.changeImg();
//				big.drawImage(enery.img, enery.x, enery.y,null);
//			}
//			else
//				big.drawImage(enery.img, enery.x, enery.y, enery.width, enery.height,null);
//		}
//		//产生蘑菇
		if(mrb==true) {
			mr.move();
			big.drawImage(mr.img, mr.x, mr.y, mr.width, mr.height,null);

		}
		//画人物
		big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height,null);
		g.drawImage(bi,0,0,null);
	}
}
