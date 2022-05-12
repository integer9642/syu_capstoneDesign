<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
out.print((new FeedDAO()).getGroup(request.getParameter("frids"), request.getParameter("maxNo")));
%>