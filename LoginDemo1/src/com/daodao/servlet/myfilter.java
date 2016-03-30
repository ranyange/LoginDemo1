package com.daodao.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daodao.biz.UserBiz;
import com.daodao.biz.impl.UserBizImpl;

public class myfilter implements Filter {

	 
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("��¼����doFilter����");
		//��¼����
		HttpServletRequest request = (HttpServletRequest)srequest;
		//ǿת
		HttpServletResponse response = (HttpServletResponse)sresponse;
		HttpSession session = request.getSession();
		//��ҳ�汣������
		String user = (String)session.getAttribute("username");
		if(user!=null&&!user.equals("")){
			chain.doFilter(request, response); //����
		}else{
			String msg = "�㻹û�е�¼";
			//��δ��¼��Ϣ�洢��request������;
			request.setAttribute("error", msg);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
			System.out.println("���ٵ�¼����Filter");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("��ʼ����¼����Filter");
	}

	 

}
