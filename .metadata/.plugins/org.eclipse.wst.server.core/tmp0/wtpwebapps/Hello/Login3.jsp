<html>
<!-- HTML comment: print a given user ID -->
<%-- JSP comment: print a given user ID --%>
<%
String uid = request.getParameter("id");
String res = "<h3>User ID: " + uid + "</h3>";
out.print(res);
%>
</html>