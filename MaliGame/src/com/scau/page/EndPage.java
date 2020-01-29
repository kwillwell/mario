package com.scau.page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.scau.mario.Mario;
import com.scau.ui.GameFrame;

public class EndPage {
	public EndPage(int result,boolean iswin) {
		JFrame frame = new JFrame("超级玛丽");
		ImageIcon background = new ImageIcon("image/menu5.png");// 背景图片
	    JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
	    JPanel beginPanel;
	    JLabel title,sign,score;
	    JButton gameContinue,exit;//按钮设置
	    
	    Font font1 =new Font("隶书", Font.BOLD, 35);
	    Font font2 =new Font("Lucida Handwriting", Font.BOLD+ Font.ITALIC, 80);
	    Font font3 =new Font("宋体", Font.BOLD, 25);
	    Font font4 =new Font("宋体", Font.BOLD, 85);
	    
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
        
        title = new JLabel("SUPER MARIO");
        title.setBounds(70, 60, 750, 60);
        title.setFont(font2);
        title.setForeground(Color.orange);
        if (iswin) {
            sign = new JLabel("CONGREATULATIONS!!!!!");
            sign.setBounds(50, 200, 1000, 90);
            sign.setFont(font4);
            sign.setForeground(Color.red);
        } else{
            sign = new JLabel("GAME OVER!");
            sign.setBounds(150, 170, 750, 90);
            sign.setFont(font4);
            sign.setForeground(Color.red);
        }
        score = new JLabel("本次获得的分数为："+result);
        score.setBounds(185, 300, 270, 50);
        score.setFont(font3);
        score.setOpaque(true);
        score.getText();
        score.setForeground(new Color(216, 104, 64));
        score.setBackground(new Color(64, 193, 120));
        score.setBorder(BorderFactory.createEtchedBorder());
        
        gameContinue=new JButton("继续游戏");
        gameContinue.setBounds(185,370,185, 50);
        gameContinue.setFont(font1);
        gameContinue.setLayout(null);
        gameContinue.setBorderPainted(false);
        gameContinue.setContentAreaFilled(false);
        
        exit=new JButton("退出游戏");
        exit.setBounds(185,450,185, 50);
        exit.setFont(font1);
        exit.setLayout(null);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        
        label.add(title);
        label.add(gameContinue);
        label.add(exit);
        label.add(score);
        label.add(sign);
        frame.add(label);
        
        gameContinue.addActionListener(new ActionListener() {
			
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
        
        exit.addActionListener(new ActionListener() {//退出程序
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
