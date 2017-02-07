package com.revature.pojo;

import java.util.List;

public class DataRmbmtJson {
	private List<Integer> aList;
	private List<Integer> dList;
	
	public DataRmbmtJson(){	}
	
	public DataRmbmtJson(List<Integer> aList, List<Integer> dList) {
		super();
		this.aList = aList;
		this.dList = dList;
	}

	public List<Integer> getaList() {
		return aList;
	}

	public void setaList(List<Integer> aList) {
		this.aList = aList;
	}

	public List<Integer> getdList() {
		return dList;
	}

	public void setdList(List<Integer> dList) {
		this.dList = dList;
	}
	
	
}
