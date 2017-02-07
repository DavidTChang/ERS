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
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.revature.enums.UserRole;
import com.revature.pojo.User;

public class AuthFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
			throws IOException, ServletException {
		
		HttpServletRequest servReq = (HttpServletRequest) req;
		HttpServletResponse servRes = (HttpServletResponse) res;
		HttpSession session = servReq.getSession();
		User cUser = (User)session.getAttribute("currentuser");
		System.out.println(servReq.getRequestURI());
		if(cUser.getUserRole() == UserRole.EMPLOYEE && servReq.getRequestURI().equals("/ERS/managerHome.jsp")){
			servRes.sendRedirect("employeeHome");
		}else if(cUser.getUserRole() == UserRole.MANAGER && servReq.getRequestURI().equals("/ERS/employeeHome.jsp")){
			servRes.sendRedirect("managerHome");
		}
		else{	
			filter.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
