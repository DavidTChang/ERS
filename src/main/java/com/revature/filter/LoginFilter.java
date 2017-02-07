package com.revature.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("IN LoginFilter");
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpSession session = servReq.getSession();
		HttpServletResponse servRes = (HttpServletResponse) res;
		
		
	
		boolean loggedIn = false;
		if(session != null){
			Object authState = session.getAttribute("authState");
			if(authState != null){
				servReq.setAttribute("user", authState);
				loggedIn = true;
			}
		}
		
		if(loggedIn){
			chain.doFilter(servReq, servRes);
		}else{
//			RequestDispatcher rd = servReq.getRequestDispatcher("login.jsp");
//			rd.forward(req, res);
			servRes.sendRedirect("login");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
