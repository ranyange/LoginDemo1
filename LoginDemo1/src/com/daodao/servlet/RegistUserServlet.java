package com.daodao.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daodao.biz.UserBiz;
import com.daodao.biz.impl.UserBizImpl;

public class RegistUserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try{
			 
			//����ע������
			String username  = request.getParameter("username");
			String nickname = request.getParameter("nickname");
			String pwd = request.getParameter("pwd");
			String confirmPwd = request.getParameter("confirmPwd");
			//����biz
			UserBiz userBiz = new UserBizImpl();
			userBiz.regist(username, nickname, pwd, confirmPwd);
			
			
			request.setAttribute("user", nickname);
			//request.getRequestDispatcher("/login.jsp");
			response.sendRedirect("/LoginDemo1/login.jsp");
		}catch (Exception e){
		e.printStackTrace();
			//��ת��ע��ҳ��
			request.getRequestDispatcher("/register.jsp");
		
		}
	}
	/*
	 * url ͳһ��Դ��λ��
	 * 	http://ip:port/��Ŀ��/��Դ·����
	 * 	ʹ�ã������
	 * uri
	 * 	/��Ŀ��/��Դ·����
	 * 	ʹ�ã�form��action
	 * 		�ض�����ת
	 * url-pattern
	 * 	/��Դ·����
	 * 	ʹ�ã�forward��ת
	 */
}
