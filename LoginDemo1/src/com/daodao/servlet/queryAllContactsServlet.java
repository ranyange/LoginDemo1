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
	
	//将session设置为静态的.
	
	 @Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
			//1.获取所有数据
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
	//  url 统一资源定位符
	// 	http://ip:port/项目名/资源路径名
	// 	使用：浏览器
	// 
	// 	uri
	// 	/项目名/资源路径名
	// 		使用：form的action
	// 		重定向跳转
	// 
	// 	url-pattern 	/资源路径名
	// 	使用：forward跳转
	///
}
