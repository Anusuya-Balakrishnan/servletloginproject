<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String username =(String)session.getAttribute("username");
if(username != null){
	 out.print("<h1>Welcome, " + username + "!</h1>");
}
else{
	
	response.sendRedirect("loginPage.html");	
}
%>

<form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>