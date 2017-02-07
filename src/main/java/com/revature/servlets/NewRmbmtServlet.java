package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.enums.UserRole;
import com.revature.pojo.User;
import com.revature.services.RmbmtService;

@WebServlet("/newRmbmt")
public class NewRmbmtServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new RmbmtService().createNewRmbmt(req);
		User user = (User) req.getSession().getAttribute("currentuser");

		if (user == null) {
			System.out.println("USER IS NULL*****************************");
			// TODO: GO CRAZY... why is this here if there is no user??
		}
		if (user.getUserRole() == UserRole.EMPLOYEE) {
			req.getSession().setAttribute("rmbmtList",new RmbmtService().getAllRmbmt(user.getUserId()));
			resp.sendRedirect("employeeHome");		
		} else if (user.getUserRole() == UserRole.MANAGER) {
			resp.sendRedirect("managerHome");
		}
		
	}
}
