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
		 //����ͼƬ�Ĳ�buff�ڴ滺����
		BufferedImage bi=new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB); 
		//��ȡͼ��Ĵ���ӿ�
		Graphics GImage=bi.getGraphics();
		//����ͼƬ����ɫ
		Color color=new Color(200,150,255);
		//����ͼƬ��ɫ
		GImage.setColor(color);
		//��canvas ���  ���� �õ�ǰ��ɫ��� ����
		GImage.fillRect(0, 0, 68, 22);	
		
		char[] ca="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random r=new Random();  //���Ĭ�ϵ�ǰϵͳʱ���Ӧ�����ʱ���йص�������Ϊ������:
		int len=ca.length;
		int index;
		//�ַ���������
		StringBuilder sb=new StringBuilder();
		Font f = new Font("����",Font.BOLD ,24); 
		GImage.setFont(f);
		
		for(int i=0;i<4;i++){    //��������ĸ�����
			index=r.nextInt(len);
			GImage.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
			if(i==2){
				Graphics2D g2d = (Graphics2D) GImage;
				g2d.rotate( 0.02*Math.PI);//��ת
			}
			GImage.drawString(ca[index]+"", i*10+10, 18);   //����ÿ���ַ���λ��
			sb.append(ca[index]);		
		}
		// ���sb.toString()����������֤����ַ�����ʽ;����ֵ����Session������;
		request.getSession().setAttribute("piccode", sb.toString());
		//��ͼƬ�����servlet�����
		ImageIO.write(bi, "JPG", response.getOutputStream());
		 
	}

}