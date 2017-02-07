package com.revature.pojo;

import java.sql.Blob;
import java.sql.Timestamp;

import com.revature.enums.RType;
import com.revature.enums.Status;

public class Rmbmt {
	private int id, authorId, resolverId;
	private String desc;
	private double amount;
	private Blob receiptBlob;
	
	RType type;
	
	Status status;
	
	private Timestamp submitDate, resolvedDate;

	
	


	public Rmbmt(int id, double amount, String desc, Blob blob,Timestamp sb, Timestamp res,
			int authorId, int resolverId, RType rtType, Status status){
		super();
		this.id = id;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.type = rtType;
		this.status = status;
		this.receiptBlob = blob;
		this.amount = amount;
		this.submitDate = sb;
		this.resolvedDate = res;
		this.desc = desc;
	}

	public Rmbmt( int authorId, String desc, RType type, Status status, double amount, Timestamp submitDate) {
		super();
		//this.id = id;
		this.desc = desc;
		this.authorId = authorId;
		//this.resolverId = resolverId;
		this.type = type;
		this.status = status;
		this.amount = amount;
		this.submitDate = submitDate;
		//this.resolvedDate = resolvedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public RType getType() {
		return type;
	}

	public void setType(RType type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status= status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Blob getReceiptBlob() {
		return receiptBlob;
	}

	public void setReceiptBlob(Blob receiptBlob) {
		this.receiptBlob = receiptBlob;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Timestamp submitDate) {
		this.submitDate = submitDate;
	}

	public Timestamp getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Timestamp resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	@Override
	public String toString() {
		return "Rmbmt [id=" + id + ", authorId=" + authorId + ", resolverId=" + resolverId + ", typeId=" + type.getType()
				+ ", statusId=" + status.getStatus() + ", desc=" + desc + ", amount=" + amount + ", receiptBlob=" + receiptBlob
				+ ", submitDate=" + submitDate + ", resolvedDate=" + resolvedDate + "]";
	}

	
}
