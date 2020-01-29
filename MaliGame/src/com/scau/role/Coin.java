package com.scau.role;

import javax.swing.*;
import java.awt.Image;
import java.awt.print.PrinterAbortException;

//½ð±ÒÀà
public class Coin extends Enemy {
	private Image img1=new ImageIcon("image/coin2.png").getImage();
	//private Image img2=new ImageIcon("image/coin2.png").getImage();
	//private Image img3=new ImageIcon("image/coin3.png").getImage();
	public Coin(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
	}
	int i;
//	public void changeImg(){
////		i++;
////		if(i<14)
////			return;
////		else i=1;
////		if(img==img1)
////			img=img2;
////		else if(img==img2)
////			img=img3;
////		else if(img==img3)
////			img=img1;
////
////	}
	public boolean equal(int x,int y){
		if(this.x==x&&this.y==y){
			return true;

		}
		return false;
	}
}
