package com.daodao.servlet;


import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daodao.biz.UserBiz;
import com.daodao.biz.impl.UserBizImpl;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取后台数据接口
		UserBiz userBiz = new UserBizImpl();
		//获取前端用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{		
			System.out.println("后面的也要执行");
			boolean flag = userBiz.login(username, password);
			HttpSession Login_session = request.getSession();
			//将登陆信息存储在session中 
			Login_session.setAttribute("username", username);
			//response.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
		}catch(Exception e){
			String exception = e.getMessage();  //将信息写在 session 中, 传递到jsp页面, 
			//用户登录失败,,
			HttpSession Login_session = request.getSession();
			Login_session.setAttribute("exception",exception );
			//response.sendRedirect("/LoginDemo1/login.jsp");		
		}
		try{
			//piccode 由VerificationServlet产生并存入session中;
			String piccode=(String)request.getSession().getAttribute("piccode");
			//获取前端表单提交的验证码字符串, 比较是否一致;
			String code=request.getParameter("code").toUpperCase();
			if(piccode.equals(code)){
				System.out.println("验证码正确");
				response.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
				
			}else{
				throw new RuntimeException("验证码错误");
			}
		}catch(RuntimeException e){
			 String error = e.getMessage();
			 HttpSession verifySession = request.getSession();
			 verifySession.setAttribute("verifySession", error);
			 response.sendRedirect("/LoginDemo1/login.jsp");
		}
		
		
		
		
		
	}

}
