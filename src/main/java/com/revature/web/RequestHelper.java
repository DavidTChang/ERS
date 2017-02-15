package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.enums.RType;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.pojo.Rmbmt;
import com.revature.pojo.TestPojo;
import com.revature.pojo.User;
import com.revature.services.RmbmtService;
import com.revature.services.UserService;

public class RequestHelper {

	public String handleRequest(HttpServletRequest req, HttpServletResponse res) {

		HttpSession session  = req.getSession();
		if(session.getAttribute("loaded") == null){
			//Load types and status possibly employees.
			session.setAttribute("statusList", Status.values());
			session.setAttribute("typeList", RType.values());
			
			session.setAttribute("loaded", new Object());
		}
		
		String targetReq = req.getRequestURI();
		if (targetReq.equalsIgnoreCase("/ERS/newuser.do")) {

		} 
		//------------------------------------------------------------------//
		
		else if (targetReq.equalsIgnoreCase("/ERS/login.do")) {

			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User user = new UserService().getUser(username, password);
			if(user == null){
				req.getSession().setAttribute("wrongPass", new Object());
				return "/";
			}
			req.getSession().setAttribute("currentuser", user);
			req.getSession().setAttribute("authState", new Object());

			if (user.getUserRole() == UserRole.MANAGER) {
				List<Rmbmt> rmbmtList = new RmbmtService().getAllRmbmt();
				req.getSession().setAttribute("rmbmtList", rmbmtList);
				return "managerHome";
			} else if (user.getUserRole() == UserRole.EMPLOYEE) {

				List<Rmbmt> rmbmtList = new RmbmtService().getAllRmbmt(user.getUserId());
				new RmbmtService().setMappedManagers(rmbmtList, req);
				// Need to check if list is empty, do something else? or do that
				// later.
				req.getSession().setAttribute("rmbmtList", rmbmtList);
				return "employeeHome";
			}

		} 
		//---------------------------------------------------------------------------------------------//
		
		else if (targetReq.equals("/ERS/newrmbmt.do")) {
			return "newRmbmt";
			
		} 
		//------------------------------------------------------------------------------------------------//
		else if(targetReq.equals("/ERS/filterStatus.do")){
			new RmbmtService().filterStatus(req);

			User user = new UserService().getCurrentSessionUser(req);
			if(user.getUserRole() == UserRole.EMPLOYEE){
				return "employeeHome.redirect";
			}
			else if(user.getUserRole() == UserRole.MANAGER){
				return "managerHome.redirect";
			}
		}

		//------------------------------------------------------------------------------------------------//
		else if(targetReq.equals("/ERS/updateProfile.do")){
			
			new UserService().updateUserProfile(req);
			User currentUser = new UserService().getCurrentSessionUser(req);
			if(currentUser.getUserRole().equals(UserRole.MANAGER)){
				return "managerHome.redirect";
			}else if(currentUser.getUserRole().equals(UserRole.EMPLOYEE)){
				return "employeeHome.redirect";
			}
		}
		
		//--------------------------------------------------------------------------------------------//
		else if(targetReq.equals("/ERS/updateRmbmt.do")){
			new RmbmtService().updateRmbmtStatus(req);
			return "managerHome.redirect";
		}
		
		// -----------------------------------------------------------------------------------------//
		else if(targetReq.equals("/ERS/filterByEmployee.do")){
			new RmbmtService().filterOnEmployee(req);
			
		}
		//------------------------------------------------------------------------------------------//
		else if(targetReq.equals("/ERS/home.do")){
			User user = new UserService().getCurrentSessionUser(req);
			if(user.getUserRole().equals(UserRole.EMPLOYEE)){
				return "employeeHome";
			}else if(user.getUserRole().equals(UserRole.MANAGER)){
				return "managerHome";
			}
		}
		else {
			return null; // Need to not do anything. Maybe error?
		}
		//------------------------------------------------------------------------------------------------//
		return null;
	}
}
