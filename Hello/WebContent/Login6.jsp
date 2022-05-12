<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.util.Date" %>
<%!
String getDate() {
return (new Date()).toString();
}
%>
<%
String uid = request.getParameter("id");
String res = "<h3>사용자 아이디: " + uid + "</h3>";
res += "<br>로그인 시간: " + getDate();
out.print(res);
%>