package com.daodao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daodao.biz.UserBiz;
import com.daodao.biz.impl.UserBizImpl;
import com.daodao.entity.Contact;

public class queryAllContactsServlet extends HttpServlet {
	
	//��session����Ϊ��̬��.
	
	 @Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
			//1.��ȡ��������
			UserBiz service = new UserBizImpl();
			List<Contact> ps = service.getAllContacts();
			req.setAttribute("ps", ps);
			req.getRequestDispatcher("/WEB-INF/queryAllPerson.jsp").forward(req,resp);;
//			if(req.getSession().getAttribute("username")!=null){
//				req.setAttribute("ps", ps);
//				req.setAttribute("something", "fuckyou");
//				RequestDispatcher rdp = req.getRequestDispatcher("/WEB-INF/queryAllPerson.jsp");
//				rdp.forward(req, resp); 
//			}		 
	}
	//  url ͳһ��Դ��λ��
	// 	http://ip:port/��Ŀ��/��Դ·����
	// 	ʹ�ã������
	// 
	// 	uri
	// 	/��Ŀ��/��Դ·����
	// 		ʹ�ã�form��action
	// 		�ض�����ת
	// 
	// 	url-pattern 	/��Դ·����
	// 	ʹ�ã�forward��ת
	///
}
