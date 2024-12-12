<%-- 
    Document   : success
    Created on : Feb 22, 2011, 11:10:02 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.user,javax.servlet.http.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% user y=new user();
   y=(user)request.getAttribute("userDetails");
  
   user x=new user();
   x.setLoginName(y.getLoginName().toString());
   x.setLoginPassword(y.getLoginPassword().toString());
   x.setLoginRole(y.getLoginRole().toString());
   request.setAttribute("userDetails",x);
   String z=y.getLoginName().toString();
   String z1=y.getLoginPassword().toString();


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>FASTLEARN WEBSITE:Welcome to Student Home Page</h1>
        <center><img alt=""  src="STICKMAN.gif"></center>
       Hi <%=y.getLoginName()%>
       Welcome back!
       <%
       java.util.Date date=new java.util.Date();
%><br>
       Time now is:<%=date.toString()%>
       <br><br>
        <div align="center">
       <img src="students.gif" alt="" height="395" width="395">
      
       
       <form action="changePassword.jsp" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>>
           <input type="submit" name="submit" value="Change Your Password">
       </form>
       <form action="./viewCourseInfo" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>><input type="submit" name="submit" value="View Course Information"></form>
           <form action="postQuery.jsp" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>><input type="submit" name="submit" value="Post Queries"></form>
            <form action="./viewQuery" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>><input type="submit" name="submit" value="View Queries"></form>

            <form action="postFeedback.jsp" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>><input type="submit" name="submit" value="Post Feedback!"></form>
            <form action="logout.jsp" method="post"><input type="hidden" name="lgn" value=<%=z %>><input type="hidden" name="pwd" value=<%=z1 %>><input type="submit" name="submit" value="Logout!"></form>

       </div>
    </body>
    
</html>
