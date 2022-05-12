<html>
<%
String uid = request.getParameter("id");
String res = "<h3>User ID: " + uid + "</h3>";
out.print(res);
%>
</html>