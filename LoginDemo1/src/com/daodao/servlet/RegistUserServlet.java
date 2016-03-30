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
			 
			//接受注册数据
			String username  = request.getParameter("username");
			String nickname = request.getParameter("nickname");
			String pwd = request.getParameter("pwd");
			String confirmPwd = request.getParameter("confirmPwd");
			//调用biz
			UserBiz userBiz = new UserBizImpl();
			userBiz.regist(username, nickname, pwd, confirmPwd);
			
			
			request.setAttribute("user", nickname);
			//request.getRequestDispatcher("/login.jsp");
			response.sendRedirect("/LoginDemo1/login.jsp");
		}catch (Exception e){
		e.printStackTrace();
			//跳转到注册页面
			request.getRequestDispatcher("/register.jsp");
		
		}
	}
	/*
	 * url 统一资源定位符
	 * 	http://ip:port/项目名/资源路径名
	 * 	使用：浏览器
	 * uri
	 * 	/项目名/资源路径名
	 * 	使用：form的action
	 * 		重定向跳转
	 * url-pattern
	 * 	/资源路径名
	 * 	使用：forward跳转
	 */
}
