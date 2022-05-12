<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
String fno = request.getParameter("fno");
out.print((new FeedDAO()).delete(fno));


System.out.println(fno);
%>