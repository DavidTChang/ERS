package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.enums.RType;
import com.revature.enums.Status;
import com.revature.pojo.Rmbmt;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class RmbmtDao implements IRmbmtDao {
	private final static String URL = "jdbc:oracle:thin:@java1701.cylnhxmf2kbj.us-west-2.rds.amazonaws.com:1521";
	private final static String USERNAME = "java1701";
	private final static String PASSWORD = "p4ssw0rd";

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createRmbmt(Rmbmt r) {

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			String rSql = "INSERT INTO ERS_REIMBURSEMENTS (R_AMOUNT,R_DESCRIPTION,R_"
					+ "SUBMITTED, R_RECEIPT,U_ID_AUTHOR, RT_TYPE,RT_STATUS) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(rSql);
			System.out.println(r);
			ps.setDouble(1, r.getAmount());
			ps.setString(2, r.getDesc());
			ps.setTimestamp(3, r.getSubmitDate());
			ps.setBytes(4, r.getReceiptBlob());
			ps.setInt(5, r.getAuthorId());
			ps.setInt(6, r.getType().getTypeId());
			ps.setInt(7, r.getStatus().getId());

			ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rmbmt updateRmbmt(Rmbmt r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rmbmt updateStatus(int rId, int statusId, Timestamp ts, int managerId) {
		String sql = "{call update_rmbmt_status(?,?,?,?)}";

		try (Connection connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			CallableStatement cs = connect.prepareCall(sql);
			cs.setInt(1, rId);
			cs.setInt(2, statusId);
			cs.setTimestamp(3, ts);
			cs.setInt(4, managerId);

			cs.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Rmbmt> getRmbmtByStatus(int status_id) {
		String sql = "{call get_rmbmt_by_status(?,?)}";
		List<Rmbmt> rList = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			CallableStatement cs = connection.prepareCall(sql);

			cs.setInt(1, status_id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);

			cs.executeQuery();

			ResultSet rs = (ResultSet) cs.getObject(2);

			while (rs.next()) {
				rList.add(new Rmbmt(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBytes(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getInt(7), rs.getInt(8), RType.getTypeById(rs.getInt(9)),
						Status.getStatusById(rs.getInt(10))));
			}
			if (!rList.isEmpty()) {
				return rList;
			}

		} catch (Exception e) {

		}

		return null;
	}

	@Override
	public List<Rmbmt> getRmbmtByEmployee(int empId) {
		String sql = "{call get_all_rmbmt(?,?)}";
		List<Rmbmt> rList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			CallableStatement cs = connection.prepareCall(sql);

			cs.setInt(1, empId);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();

			ResultSet rs = (ResultSet) cs.getObject(2);
			while (rs.next()) {
				rList.add(new Rmbmt(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBytes(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getInt(7), rs.getInt(8), RType.getTypeById(rs.getInt(9)),
						Status.getStatusById(rs.getInt(10))));
			}

			if (!rList.isEmpty()) {
				return rList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Rmbmt> getAllRmbmt() {
		String sql = "{call get_all_employee_rmbmt(?)}";
		
		try(Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);){
			CallableStatement cs = connection.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.executeQuery();
			
			ResultSet rs = (ResultSet) cs.getObject(1);

			List<Rmbmt> rList = new ArrayList<Rmbmt>();
			
			while(rs.next()){
				rList.add(new Rmbmt(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getBytes(4), rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getInt(7), rs.getInt(8), RType.getTypeById(rs.getInt(9)),
						Status.getStatusById(rs.getInt(10))));
			}
			
			return rList;
		}catch(Exception e){
			
		}
		
		return null;
	}

}
