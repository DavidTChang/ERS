package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.DataUserListJson;
import com.revature.pojo.TestPojo;
import com.revature.pojo.User;
import com.revature.services.UserService;

@WebServlet("/empList")
public class EmployeeListServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 List<User> userList = new UserService().getAllEmployees(req);
		 System.out.println(userList.size());
		//TestPojo jsonUserList = new TestPojo("David");

		ObjectMapper om = new ObjectMapper();

		DataUserListJson jsonUserList = new DataUserListJson(userList);
		String json = om.writeValueAsString(jsonUserList);
		
		PrintWriter pw = resp.getWriter();
		
		pw.write(json);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		TestPojo jsonUserList = new TestPojo("David");

		ObjectMapper om = new ObjectMapper();

		String json = om.writeValueAsString(jsonUserList);
		
		PrintWriter pw = resp.getWriter();
		
		pw.write(json);
	}
}
