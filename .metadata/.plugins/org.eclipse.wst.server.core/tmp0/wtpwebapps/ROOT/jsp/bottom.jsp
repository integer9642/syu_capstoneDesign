<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="dao.*" %>
<%
	
    String str = (new ImageDAO()).getBottomList();

    out.print(str);
%>