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
		//�����ϵ��:
		//��ȡ����
		String name = req.getParameter("name");
		//���get������������
		name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		//����ȡ�����ַ���תΪ���ڸ�ʽ
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
		
		// ��װ����
		Contact contact = new Contact(1,name,birth,age,phone,zipcode);
		
		//���ú�̨�����ϵ��
		
		ContactBiz contactBiz = new ContactBizImpl();
		contactBiz.insertContact(contact);
		//��ӳɹ�,��ת����ʾ������ϵ��
		resp.sendRedirect("/LoginDemo1/servlet/queryAllContactsServlet");
		
		
	}

}
