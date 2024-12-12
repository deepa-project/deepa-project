<%-- 
    Document   : changePassword
    Created on : Feb 23, 2011, 1:45:24 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.user,javax.servlet.http.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
 String x=request.getParameter("lgn");
 String y=request.getParameter("pwd");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>Change Password</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>Fastlearn Website</h1>
        Hi <%=x %>
        <h3>Change your Password!!!</h3>
        <center> <img alt=""  src="STICKMAN.gif"></center>
        <br><br>
         <%
       java.util.Date date=new java.util.Date();%>Time now is:<%=date.toString()%><br><br>
        <hr>
        <form action="./passwordChanger" method="post">
            <input type="hidden" name="lgn" value=<%=x %>>
            <br>Your old password is:<%=y%>
           
            <br>Enter new password:<input type="password" name="newPassword">
            <br>Confirm (Re-enter new password):<input type="password" name="confirm_newPassword">
            <br><br><br><hr>
            <input type="submit" value="Confirm Changes" name="submit">&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" value="Cancel Changes" name="reset">
            <hr>



            
        </form>
        
      
    </body>
</html>
