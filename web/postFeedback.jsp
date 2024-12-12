<%-- 
    Document   : postFeedback
    Created on : Feb 23, 2011, 7:58:04 PM
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
        <title>Give your Feedback,please</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>Fastlearn Website</h1>
        Hi <%=x %>
        <h3>Post Query!!!</h3>
        <center> <img alt=""  src="STICKMAN.gif"></center>
        <br><br>
         <%
       java.util.Date date=new java.util.Date();%>Time now is:<%=date.toString()%><br><br>
        <hr>
        <img src="feedback.jpg" alt=""/>
        <
        <form action="./postFeedback" method="POST">
<hr>
            Your Feedback please:
            <input type="hidden" name="lgn" value=<%=x %>>
            <hr>
            <textarea name="feedback_input" rows="10" cols="50">
            </textarea>
            <hr>
            <input type="submit" value="submit" />
            <input type="submit" value="cancel" />
        </form>
    </body>
</html>
