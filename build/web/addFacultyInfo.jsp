<%-- 
    Document   : addFacultyInfo
    Created on : Feb 25, 2011, 5:37:30 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="model.user,javax.servlet.http.*,java.sql.*" %>
 <%@ page language="java" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        ResultSet rs=null;
        String driver="org.apache.derby.jdbc.ClientDriver";
        try {
            //database connections
            Class.forName(driver);
            con=DriverManager.getConnection(url, username, password);
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from app.fl_faculty");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Query Answer page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Login Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Faculty Info!!!</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
          out.println("<table border=2><tr><th>FACULTY LOGIN ID</th><th>NAME</th><th>ADDRESS</th><th>EMAIL</th><th>COURSE TAUGHT BY THE FACULTY</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td></tr>");
 }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
             stmt.close();
            con.close();
            }
        catch (Exception e)
                {
            }
        finally
                {

           //out.close();
            }

 String x=request.getParameter("lgn").toString();
 String y=request.getParameter("pwd");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>FASTLEARN FACULTY INFORMATION SYSTEM</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>ADD FACULTY INFO!</h1>
        Hi <%=x %>

        <center> <img alt=""  src="STICKMAN.gif"></center>
        <br><br>
         <%
       java.util.Date date=new java.util.Date();%>Time now is:<%=date.toString()%><br><br>
        <hr>
        <center>FACULTY INFORMATION SYSTEMS</center>

        <b> Enter the following details:</b><br>
        <form action="./addFacultyInformation" method="POST">
            Enter Faculty Login:<input type="text" name="facLogin" value="" />
            <br>
            Enter Faculty's Name:<input type="text" name="facName" value="" />
            <br>
            Enter Faculty's Address:<input type="text" name="facAddress" value="" />
            <br>
            Enter Faculty's Email:<input type="text" name="facEmail" value="" />

            <br>
           
            <br>
            <b>Select a course:</b>
<select name="facCourse">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
Connection connection= DriverManager.getConnection(connectionURL, "admin", "password");
PreparedStatement psmnt = connection.prepareStatement("select course_name from app.fl_course ");
ResultSet results = psmnt.executeQuery();
while(results.next()){
String courseName = results.getString(1);

%><option value="<%= courseName %>"><%=courseName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>
            <hr>
            <input type="submit" value="ADD FACULTY DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />            <hr>
        </form>
        <br>
        <h1>UPDATE FACULTY INFO!</h1>
         <form action="./updateFacultyInformation" method="POST">
            Select faculty whose information needs to be updated:
            <br>
<select name="facLogin">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 psmnt = connection.prepareStatement("select * from app.fl_faculty");

 results = psmnt.executeQuery();
while(results.next()){
String facLogin=results.getString(1);
String facName = results.getString(2);
String facCourse=results.getString(4);




    %><option value="<%= facLogin %>"> <%=facName%>   <%=facCourse%></option>
<%} results.close(); psmnt.close(); %>
</select><br>
            Enter Faculty's Name:<input type="text" name="facName" value="" />
            <br>

            Enter Faculty's Address:<input type="text" name="facAddress" value="" />
            <br>
            Enter Faculty's Email Address:<input type="text" name="facEmail" value="" /><br>
           
            <br>
           <b>Select a course:</b>
<select name="facCourse">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 connection= DriverManager.getConnection(connectionURL, "admin", "password");
 psmnt = connection.prepareStatement("select course_name from app.fl_course ");
 results = psmnt.executeQuery();
while(results.next()){
String courseName = results.getString(1);

%><option value="<%= courseName %>"><%=courseName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>
            <br>
            <hr>
            <input type="submit" value="UPDATE FACULTY DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />
            </form>
         <br><hr>
         <h1>DELETE FACULTY DETAILS</h1>

        <form action="./deleteFacultyInformation" method="POST">
           Select faculty whose information needs to be deleted:
            <br>
<select name="facLogin">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 psmnt = connection.prepareStatement("select * from app.fl_faculty");

 results = psmnt.executeQuery();
while(results.next()){
String facLogin=results.getString(1);
String facName = results.getString(2);
String facCourse=results.getString(4);




    %><option value="<%= facLogin %>"> <%=facName%>   <%=facCourse%></option>
<%} results.close(); psmnt.close(); %>
</select><br>
        <hr>
         <input type="submit" value="DELETE FACULTY DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />
            </form>
         <br><hr>
    </body>
</html>
