package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MasterServlet extends HttpServlet{

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String nextPage = new RequestHelper().handleRequest(req, resp);
		if(nextPage.contains(".redirect")){
			String[] reqPath = nextPage.split("\\.");
			resp.sendRedirect(reqPath[0]);
			
		}
		else{
			req.getRequestDispatcher(nextPage).forward(req, resp);		
		}
	}
}
