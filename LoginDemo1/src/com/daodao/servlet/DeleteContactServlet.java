package com.daodao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daodao.biz.ContactBiz;
import com.daodao.biz.impl.ContactBizImpl;

public class DeleteContactServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ɾ����ϵ��servlet��ʼ");
		//����idɾ����ϵ��
		Integer id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		//������̨����
		ContactBiz contactBiz = new ContactBizImpl();
		contactBiz.delContact(id);
		
		//��ת��չʾ������ϵ��ҳ��
		System.out.println("ɾ����ϵ��servlet���,׼����ת");
		resp.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
	}

}
