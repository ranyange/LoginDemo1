package com.daodao.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daodao.biz.ContactBiz;
import com.daodao.biz.impl.ContactBizImpl;
import com.daodao.entity.Contact;

public class UpdateContactServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置编码格式
		 
		//准备操作数据库对象
		ContactBiz contactBiz = new ContactBizImpl();
		Contact contact = null;
		
		//获取前端传来的数据
		//将传过来的id进行类型转换
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		//操作日期类型 准备字符串转日期类对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		String birth_temp = request.getParameter("birth");
		Date birth = null;
		try {
			birth = sdf.parse(birth_temp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer age =  Integer.parseInt(request.getParameter("age"));
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");
		
		//封装数据
		
		contact = new Contact(id,name,birth,age,phone,zipcode);
		System.out.println(contact);
		//执行修改
		contactBiz.updateContact(contact);
		//修改成功后跳转queryAllPerson.jsp页面
		response.sendRedirect("/LoginDemo1/queryAllPerson.jsp");
		
	}

}
