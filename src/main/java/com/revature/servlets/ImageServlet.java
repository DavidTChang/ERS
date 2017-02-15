package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.ImageIdJson;
import com.revature.pojo.Rmbmt;


public class ImageServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Rmbmt> rList = (List<Rmbmt>)req.getSession().getAttribute("rmbmtList");
		 //String imageName = req.getPathInfo().substring(1); 
		String imageName = "image.jpg";
		
		System.out.println(imageName + " Do Post");

		ObjectMapper om = new ObjectMapper();
		ImageIdJson jid = om.readValue(req.getInputStream(), ImageIdJson.class);
		
		byte[] imageByte = null;
		for(Rmbmt r : rList){
			if(jid.getImageId() == r.getId()){
				imageByte = r.getReceiptBlob();
			}
		}
	   resp.setContentType(getServletContext().getMimeType(imageName));
       resp.setContentLength(imageByte.length);
       resp.getOutputStream().write(imageByte);	
       
/*       RequestDispatcher rd = req.getRequestDispatcher("testImage.jsp");
       rd.forward(req, resp);*/
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Rmbmt> rList = (List<Rmbmt>)req.getSession().getAttribute("rmbmtList");
		String imageName = req.getPathInfo().substring(1); 
		//String imageName = "image.jpg";
		
		System.out.println(imageName + " Do Get");

		//ObjectMapper om = new ObjectMapper();
		//ImageIdJson jid = om.readValue(req.getInputStream(), ImageIdJson.class);
		
		byte[] imageByte = null;
		for(Rmbmt r : rList){
			if(r.getReceiptBlob() !=null){
				imageByte = r.getReceiptBlob();
			}
		}
		resp.setContentType(getServletContext().getMimeType(imageName));
        resp.setContentLength(imageByte.length);
        resp.getOutputStream().write(imageByte);
	
/*        RequestDispatcher rd = req.getRequestDispatcher("testImage.jsp");
        rd.forward(req, resp);*/
	}
		
}
