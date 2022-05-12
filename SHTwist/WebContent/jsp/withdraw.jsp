<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.UserDAO" %>
<%
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("id");
UserDAO dao = new UserDAO();

if(dao.delete(uid)){
	out.print("OK");
	return;
}

else if(dao.delete(uid) == false){
	out.print("NA");
}

else { 
out.print("ER");
}

%>