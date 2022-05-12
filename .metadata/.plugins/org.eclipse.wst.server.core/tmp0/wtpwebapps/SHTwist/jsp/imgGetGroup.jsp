<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
out.print((new ImageDAO()).getTopGroup(request.getParameter("frids"), request.getParameter("maxNo")));
%>