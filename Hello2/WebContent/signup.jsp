<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="core.*" %>
<%
request.setCharacterEncoding("utf-8");

String uid = request.getParameter("id");
String upass = request.getParameter("pw");
String uname = request.getParameter("name");

UserDAO dao = new UserDAO();
if (dao.exists(uid)) {
out.print("이미 가입한 회원입니다.");
return;
}

if (dao.insert(uid, upass, uname) == true) {
out.print("회원 가입이 완료되었습니다.");
return;
}
else { 
out.print("회원 가입 중 오류가 발생하었습니다.");
}
%>