package com.revature.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.RmbmtDao;
import com.revature.dao.UserDao;
import com.revature.enums.RType;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.pojo.DataRmbmtJson;
import com.revature.pojo.Rmbmt;
import com.revature.pojo.User;

@MultipartConfig
public class RmbmtService {


	
	public List<Rmbmt> getAllRmbmt(int userId){
		RmbmtDao rDao = new RmbmtDao();
		List<Rmbmt> rList = rDao.getRmbmtByEmployee(userId);
		
		return rList;
	}
	
	public void setMappedManagers(List<Rmbmt> rList, HttpServletRequest req){
		Map<Integer, User> mappedManagers = getMappedApprovedManagerMap(rList);	
		req.getSession().setAttribute("addRows", mappedManagers);//maybe set map in here
	}
	
	public List<Rmbmt> getAllRmbmt(){
		List<Rmbmt> rList = new RmbmtDao().getAllRmbmt();
		return rList;
	}
	
	public List<Rmbmt> getAllRmbmtByStatus(Status status){
		List<Rmbmt> rmbmtList = new ArrayList<>();
		rmbmtList = new RmbmtDao().getRmbmtByStatus(status.getId());
		return rmbmtList;
	}
	
	public void filterStatus(HttpServletRequest req){
		String filter = req.getParameter("filterStatus");
		User user = new UserService().getCurrentSessionUser(req);
		List<Rmbmt> rmbmtList = null;
		
		if(user.getUserRole() == UserRole.MANAGER){
			rmbmtList = new RmbmtDao().getAllRmbmt();
			System.out.println(rmbmtList.size());
		}
		else if(user.getUserRole() == UserRole.EMPLOYEE){
			rmbmtList = new RmbmtDao().getRmbmtByEmployee(user.getUserId());
		}
		
		List<Rmbmt> filteredList = new ArrayList<>();
		int sId = getStatusId(filter);
		
		if(filter.equals("APPROVED") || filter.equals("DECLINED")){
			Map<Integer, User> mappedManagers = getMappedApprovedManagerMap(rmbmtList);	
			req.getSession().setAttribute("addRows", mappedManagers);//maybe set map in here
		}else{
			req.getSession().setAttribute("addRows", null);
		}
		
		if(sId == 0){
			req.getSession().setAttribute("rmbmtList", rmbmtList);
		}
		else{
			for(Rmbmt r : rmbmtList){
				if(r.getStatus().getId() == sId){
					
					System.out.println(r);
					
					filteredList.add(r);
				}
			}
			req.getSession().setAttribute("rmbmtList", filteredList);

		}
	}
	
	public void updateRmbmtStatus(HttpServletRequest req){
		User user = new UserService().getCurrentSessionUser(req);
		ObjectMapper om = new ObjectMapper();
		RmbmtDao rDao = new RmbmtDao();
		try {
			DataRmbmtJson drj = om.readValue(req.getInputStream(), DataRmbmtJson.class);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			for(Integer i : drj.getaList()){
				System.out.println("ID FOR RMBMT" + i);
				rDao.updateStatus(i, Status.APPROVED.getId(), ts, user.getUserId());
			}
			
			for(Integer i : drj.getdList()){
				rDao.updateStatus(i, Status.DECLINED.getId(), ts, user.getUserId());
			}
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void filterOnEmployee(HttpServletRequest req){
		String emp = req.getParameter("filterEmployee");
		String empParsed[] = emp.split(" "); 
		String fName = empParsed[0];
		String lName = empParsed[1];
		
		//List<Rmbmt> rList = new RmbmtDao().get
	}
	
	private Map<Integer, User> getMappedApprovedManagerMap(List<Rmbmt> rmbmtList){
		Map<Integer,User> managerMap = new HashMap<Integer,User>();
		for(Rmbmt r : rmbmtList){
			User u = new UserDao().getUser(r.getResolverId());
			if(!managerMap.containsKey(r.getId())){
				managerMap.put(r.getId(), u);
			}
		}
		return managerMap;
	}
	
	/*
	 * Change this into ENUM later.
	 */
	private int getTypeId(String type){
		if(type.equalsIgnoreCase("WEAPONS")){
			return 1;
		}
		else if(type.equalsIgnoreCase("CLOTHES")){
			return 2;
		}
		else if(type.equalsIgnoreCase("TRANSPORTATION")){
			return 3;
		}
		else if(type.equalsIgnoreCase("FOOD")){
			return 4;
		}
		return 0;
	}
	
	
	private int getStatusId(String status){
		if(status.equalsIgnoreCase("ALL")){
			return 0;
		}
		else if(status.equalsIgnoreCase("PENDING")){
			return 1;
		}
		else if (status.equalsIgnoreCase("APPROVED")) {
			return 2;
		} 
		else if (status.equalsIgnoreCase("DECLINED")) {
			return 3;
		}
		else{
			return -1;
		}
	}
	
	public void test(){}
}
