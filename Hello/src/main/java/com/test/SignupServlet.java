package com.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String uid = request.getParameter("id");
		String upw = request.getParameter("pw");
		String unme = request.getParameter("nme");
		String str = "<html><h>User ID :" +uid+ "</h></html><br>";
		String str2 = "<html><h>User PW :" +upw+ "</h></html><br>";
		String str3 = "<html><h>User NAME :" +unme+ "</h></html><br>";

		response.getWriter().print(str);
		response.getWriter().print(str2);
		response.getWriter().print(str3);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
