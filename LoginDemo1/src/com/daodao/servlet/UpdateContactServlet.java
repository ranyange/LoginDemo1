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
		//���ñ����ʽ
		 
		//׼���������ݿ����
		ContactBiz contactBiz = new ContactBizImpl();
		Contact contact = null;
		
		//��ȡǰ�˴���������
		//����������id��������ת��
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		//������������ ׼���ַ���ת���������
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
		
		//��װ����
		
		contact = new Contact(id,name,birth,age,phone,zipcode);
		System.out.println(contact);
		//ִ���޸�
		contactBiz.updateContact(contact);
		//�޸ĳɹ�����תqueryAllPerson.jspҳ��
		response.sendRedirect("/LoginDemo1/queryAllPerson.jsp");
		
	}

}
