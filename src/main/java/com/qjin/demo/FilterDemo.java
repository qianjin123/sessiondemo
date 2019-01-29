package com.qjin.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterDemo implements Filter {
	
	private String excludedPages;       
	private String[] excludedPageArray;  
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
			excludedPages = fConfig.getInitParameter("excludedPages");  
			System.out.println("---------->init:"+excludedPages);
			if (null!=excludedPages) {     
			excludedPageArray = excludedPages.split(",");     
			}     
			return;     
			
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("---------->filter");
		boolean isExcludedPage = false;     
		for (String page : excludedPageArray) {//判断是否在过滤url之内    
			if(((HttpServletRequest) request).getServletPath().equals(page)){     
				isExcludedPage = true;     
				break;     
			}     
		}
		if (isExcludedPage) {//在过滤url之内    
			chain.doFilter(request, response);  
			return;
		}else{
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse resp = (HttpServletResponse)response;
			Cookie[] cookie = req.getCookies();
			if(null!=cookie){
				for (Cookie c : cookie) {
					if("JSESSIONID".equals(c.getName())){
						
					}
					System.out.println(c.getName()+":"+c.getValue());
				}
			}
			
			
			/////
			HttpSession session = req.getSession();
			String name = (String) session.getAttribute("name");
			//没有登录
			if(null==name){
				//req.getRequestDispatcher("/login.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
				return;
			}
			System.out.println("------->登录人为:"+name);
			chain.doFilter(request, response);
	        return;
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
