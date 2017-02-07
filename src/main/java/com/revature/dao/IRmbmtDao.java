package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.pojo.Rmbmt;

public interface IRmbmtDao {
	
	void createRmbmt(Rmbmt r);
	
	Rmbmt updateRmbmt(Rmbmt r);
	
	Rmbmt updateStatus(int rId, int statusId, Timestamp ts, int managerId);
	
	List<Rmbmt> getRmbmtByStatus(int status_id);
	
	List<Rmbmt> getRmbmtByEmployee(int empId);
	
	List<Rmbmt> getAllRmbmt();
}
