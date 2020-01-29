package com.scau.page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.scau.ui.GameFrame;

public class BeginPage {
	public BeginPage(){
		JFrame frame = new JFrame("超级玛丽");
		ImageIcon background = new ImageIcon("image/menu3.png");// 背景图片
	    JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
	    JPanel beginPanel;
	    JLabel title1,title2;
	    JButton gameStart,showRanking,exit;//按钮设置
	    
	    Font font1 =new Font("隶书", Font.BOLD, 35);
	    Font font2 =new Font("Lucida Handwriting", Font.BOLD+ Font.ITALIC, 80);
	    
	    label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
	    
	    beginPanel = (JPanel) frame.getContentPane();
        beginPanel.setOpaque(false);
        beginPanel.setLayout(new FlowLayout());
        frame.getLayeredPane().setLayout(null);
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(background.getIconWidth(), background.getIconHeight()+30);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title1 = new JLabel("SUPER");
        title1.setBounds(100,80, 450, 100);
        title1.setFont(font2);
        title1.setForeground(Color.orange);
        
        title2 = new JLabel("MARIO");
        title2.setBounds(350,200, 450, 100);
        title2.setFont(font2);
        title2.setForeground(Color.orange);
        
        gameStart=new JButton("开始游戏");
        gameStart.setBounds(305,360,185, 50);
        gameStart.setFont(font1);
        gameStart.setLayout(null);
        gameStart.setBorderPainted(false);
        gameStart.setContentAreaFilled(false);
        
        showRanking=new JButton("查看排名");
        showRanking.setBounds(305,415,185, 50);
        showRanking.setFont(font1);
        showRanking.setLayout(null);
        showRanking.setBorderPainted(false);
        showRanking.setContentAreaFilled(false);
        
        exit=new JButton("退出游戏");
        exit.setBounds(305,470,185, 50);
        exit.setFont(font1);
        exit.setLayout(null);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        
        label.add(title1);
        label.add(title2);
        label.add(gameStart);
        label.add(showRanking);
        label.add(exit);
        
        frame.add(label);
        
        gameStart.addActionListener(new ActionListener() {//开始游戏
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameFrame gf;
				try {
					frame.setVisible(false);
					gf = new GameFrame();
					gf.initFrame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
        
        showRanking.addActionListener(new ActionListener() {//显示排行榜
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
        
        exit.addActionListener(new ActionListener() {//退出程序
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
    
    
    
}
