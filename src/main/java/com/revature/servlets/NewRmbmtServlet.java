package com.revature.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.dao.RmbmtDao;
import com.revature.enums.RType;
import com.revature.enums.Status;
import com.revature.enums.UserRole;
import com.revature.pojo.Rmbmt;
import com.revature.pojo.User;
import com.revature.services.RmbmtService;
import com.revature.services.UserService;

@WebServlet("/newRmbmt")
@MultipartConfig
public class NewRmbmtServlet extends HttpServlet{
	
	private byte[] rmbmtReceipt;
	private double amount;
	private int typeId;
	private String desc;
	
	
	
	/*
	 * double amount = Double.parseDouble(req.getParameter("amount"));
		String type = req.getParameter("type");
		int typeId = getTypeId(type);
		String desc = req.getParameter("desc");
		
		Date javaDate = new Date();
		Timestamp date = new Timestamp(javaDate.getTime());
	 */
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*		try {
			Part filePart = req.getPart("photoFile");
			if(filePart != null){
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		User user = new UserService().getCurrentSessionUser(req);
        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(req);
                
                
                for(FileItem item : multiparts){
                	System.out.println(item.getFieldName());
                    if(!item.isFormField()){
                        rmbmtReceipt = item.get();
                    }else{
                    	if(item.getFieldName().equals("amount")){
                    		amount = Double.parseDouble(item.getString());
                    	}
                    	else if(item.getFieldName().equals("type")){
                    		RType rtype = RType.getTypeByName(item.getString());
                    		typeId = rtype.getTypeId();
                    	}
                    	else if(item.getFieldName().equals("desc")){
                    		desc = item.getString();
                    	}
                    	else if(item.getFieldName().equals("photoFile")){
                    		rmbmtReceipt = item.get();
                    	}

                    	
                    }
                }
                Date javaDate = new Date();
				Timestamp date = new Timestamp(javaDate.getTime());
				
				//InputStream blobStream = new ByteArrayInputStream(rmbmtReceipt);
				//Blob blobReceipt = new SerialBlob(rmbmtReceipt);
               
				Rmbmt rmbmt = new Rmbmt(user.getUserId(),desc,RType.getTypeById(typeId),
                					Status.getStatusById(1),amount,date, rmbmtReceipt);
               
                
				RmbmtDao daoRmbmt = new RmbmtDao();
				daoRmbmt.createRmbmt(rmbmt);
				
                //File uploaded successfully
               req.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               req.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            req.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
		
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
