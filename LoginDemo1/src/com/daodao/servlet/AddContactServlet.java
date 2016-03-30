package com.daodao.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daodao.biz.ContactBiz;
import com.daodao.biz.impl.ContactBizImpl;
import com.daodao.entity.Contact;

public class AddContactServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//添加联系人:
		//获取数据
		String name = req.getParameter("name");
		//解决get请求中文乱码
		name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		//将获取到的字符串转为日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		String birth_temp = req.getParameter("birth");
		Date birth = null;
		try {
			birth = sdf.parse(birth_temp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer age =  Integer.parseInt(req.getParameter("age"));
		String phone = req.getParameter("phone");
		String zipcode = req.getParameter("zipcode");
		
		// 封装数据
		Contact contact = new Contact(1,name,birth,age,phone,zipcode);
		
		//调用后台添加联系人
		
		ContactBiz contactBiz = new ContactBizImpl();
		contactBiz.insertContact(contact);
		//添加成功,跳转到显示所有联系人
		resp.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
		
		
	}

}
