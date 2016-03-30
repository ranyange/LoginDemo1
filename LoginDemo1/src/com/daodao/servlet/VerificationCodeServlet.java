package com.daodao.servlet;

import javax.servlet.http.HttpServlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class VerificationCodeServlet extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //创建图片的不buff内存缓存区
		BufferedImage bi=new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB); 
		//获取图像的处理接口
		Graphics GImage=bi.getGraphics();
		//创建图片背景色
		Color color=new Color(200,150,255);
		//设置图片颜色
		GImage.setColor(color);
		//与canvas 差不多  用来 用当前颜色填充 矩形
		GImage.fillRect(0, 0, 68, 22);	
		
		char[] ca="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random r=new Random();  //随机默认当前系统时间对应的相对时间有关的数字作为种子数:
		int len=ca.length;
		int index;
		//字符串处理类
		StringBuilder sb=new StringBuilder();
		Font f = new Font("宋体",Font.BOLD ,24); 
		GImage.setFont(f);
		
		for(int i=0;i<4;i++){    //随机生成四个数字
			index=r.nextInt(len);
			GImage.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			if(i==2){
				Graphics2D g2d = (Graphics2D) GImage;
				g2d.rotate( 0.02*Math.PI);//旋转
			}
			GImage.drawString(ca[index]+"", i*10+10, 18);   //设置每个字符的位置
			sb.append(ca[index]);		
		}
		// 最后sb.toString()产生的是验证码的字符串形式;并将值存入Session作用域;
		request.getSession().setAttribute("piccode", sb.toString());
		//将图片输出到servlet输出流
		ImageIO.write(bi, "JPG", response.getOutputStream());
		 
	}

}