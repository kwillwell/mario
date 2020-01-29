package com.scau.role;

import javax.swing.*;
import javax.xml.transform.dom.DOMSource;
import java.awt.*;
import java.awt.print.PrinterAbortException;

public class PiranhaFlower extends Enemy {
	private int top, bot;
	private double vy = 1;
	public double doubley;
	private Image img1 = new ImageIcon("image/flower1.1.png").getImage();
	private Image img2 = new ImageIcon("image/flower1.2.png").getImage();
	int i;

	public PiranhaFlower(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
		top=y;
		bot=y+100;
	}

	public void changeImg() {
		i++;
		if (i < 25) {
			return;
		} else
			i = 1;
		if (img == img1)
			img = img2;
		else
			img = img1;
	}


    public void move(){
        if(y<top||y>bot)
        	vy=-vy;
        y+=vy*1;
    }
}
