<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="java.io.*" %>
<%
String str = "", line = "";
str += "Content type: " + request.getContentType() + "<br>";
str += "Content length: " + request.getContentLength() + "<br>";
str += "<hr>";
str+= "ID: " + request.getParameter("id") + "<br>";
str+= "Content: " + request.getParameter("content") + "<br>";
str+= "Image: " + request.getParameter("image") + "<br>";
str += "<hr>";
InputStreamReader isr = new InputStreamReader(request.getInputStream(), "UTF-8");
BufferedReader br = new BufferedReader(isr);
while((line = br.readLine()) != null) {
str += line;
}
out.print(str);
%>