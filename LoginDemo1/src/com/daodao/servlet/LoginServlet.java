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
		//��ȡ��̨���ݽӿ�
		UserBiz userBiz = new UserBizImpl();
		//��ȡǰ���û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{		
			System.out.println("�����ҲҪִ��");
			boolean flag = userBiz.login(username, password);
			HttpSession Login_session = request.getSession();
			//����½��Ϣ�洢��session�� 
			Login_session.setAttribute("username", username);
			//response.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
		}catch(Exception e){
			String exception = e.getMessage();  //����Ϣд�� session ��, ���ݵ�jspҳ��, 
			//�û���¼ʧ��,,
			HttpSession Login_session = request.getSession();
			Login_session.setAttribute("exception",exception );
			//response.sendRedirect("/LoginDemo1/login.jsp");		
		}
		try{
			//piccode ��VerificationServlet����������session��;
			String piccode=(String)request.getSession().getAttribute("piccode");
			//��ȡǰ�˱��ύ����֤���ַ���, �Ƚ��Ƿ�һ��;
			String code=request.getParameter("code").toUpperCase();
			if(piccode.equals(code)){
				System.out.println("��֤����ȷ");
				response.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
				
			}else{
				throw new RuntimeException("��֤�����");
			}
		}catch(RuntimeException e){
			 String error = e.getMessage();
			 HttpSession verifySession = request.getSession();
			 verifySession.setAttribute("verifySession", error);
			 response.sendRedirect("/LoginDemo1/login.jsp");
		}
		
		
		
		
		
	}

}
