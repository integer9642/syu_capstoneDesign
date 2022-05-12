<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ page import="core.*" %>
<%
request.setCharacterEncoding("utf-8");
String uid = request.getParameter("id");


if ((new UserDAO()).delete(uid)) {
out.print("회원 탈퇴가 완료되었습니다.");
}
else {
out.print("회원 탈퇴 중 오류가 발생하었습니다.");
}
%>