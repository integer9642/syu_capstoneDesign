<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
out.print((new NoticeDAO()).getGroup_n(request.getParameter("frids"), request.getParameter("maxNo")));
%>