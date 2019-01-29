package com.qjin.demo;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionDemo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uname = req.getParameter("userName");
		String pwd = req.getParameter("pwd");
		
		System.out.println("******************");
		if("qianjin".equals(uname)&&"123456".equals(pwd)){
			HttpSession session = req.getSession();
			/***
			 * HttpSession session = request.getSession();此行发生了如下的:
			 * 1.如果没有创建过则创建一个HttpSession对象，tomcat生成一个唯一标识作为这个session的id,存入服务器；
			 * 2.同时生成一个Cookie对象,默认key为JSESSIONID，value为这个id值，通过response.addCookie(key,value)将这个cookie回写给浏览器;
			 * 3.浏览器下次访问服务器时，在请求头里会携带一个名为JSESSIONID的cookie,tomcat会根据这个JESSIONID在服务器中找到具体的HttpSession对象，所以不同的浏览器，在服务器中的session对象也会不同。
			 * 上述机制，都是tomcat自动完成，无需编码。
			 * 
			 * 1.关闭浏览器将会清空cookie,session也将丢失
			 * 2.关闭tomcat，会自动将sessiion存入硬盘，重启后session依然有效。但直接关闭虚拟机，则session丢失
			 */
			session.setAttribute("name", "james");
			//设置JESSIONID的过期时间,重写JSESSIONID，到期后此cookie从浏览器上消失，也就导致服务器找不到对应的session;此功能可解决关闭浏览器session失效的问题
			Cookie c = new Cookie("JSESSIONID", session.getId()  );
	        c.setMaxAge(120); //120秒
	        c.setPath(req.getContextPath());
	        resp.addCookie(c);
			resp.sendRedirect(req.getContextPath()+"/success.jsp?userName="+uname);
		}else {
			req.setAttribute("message", "账密错误，请重新登录<br>");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
