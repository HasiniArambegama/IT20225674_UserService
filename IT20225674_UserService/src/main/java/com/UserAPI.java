package com;
import model.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet{
	
	private static final long serialVersionUID =1L;
	User userObj = new User();
	
	public UserAPI()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			System.out.println("requets recieved");
			String output = userObj.insertUser(request.getParameter("UserName"),
			request.getParameter("UserNIC"),
			request.getParameter("UserAddress"),
			request.getParameter("UserPhone"));
			 
			response.getWriter().write(output);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = userObj.updateUser(Integer.parseInt(paras.get("hidUserIDSave").toString()),
			 paras.get("UserName").toString(),paras.get("UserNIC").toString(),paras.get("UserAddress").toString(),paras.get("UserPhone").toString());
			 
			response.getWriter().write(output);
	} 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 //String output = userObj.deleteUser(paras.get("UserID").toString());
			 String output = userObj.deleteUser(Integer.parseInt(paras.get("UserID").toString()));
			 //System.out.println(paras.get("UserID").toString());
			 response.getWriter().write(output);
	}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
	
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}