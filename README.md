# sessiondemo
最简单session机制
servlet + tomcat ,一个登录页展示最简单session+cookie机制，比如关闭浏览器如何避免session失效（设置cookie的有效期）。
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
