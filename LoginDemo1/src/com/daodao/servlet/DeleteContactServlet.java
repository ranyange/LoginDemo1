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
		System.out.println("删除联系人servlet开始");
		//根据id删除联系人
		Integer id = Integer.parseInt(req.getParameter("id"));
		System.out.println(id);
		//操作后台数据
		ContactBiz contactBiz = new ContactBizImpl();
		contactBiz.delContact(id);
		
		//跳转到展示所有联系人页面
		System.out.println("删除联系人servlet完毕,准备跳转");
		resp.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
	}

}
