<%-- 
    Document   : viewFeedback
    Created on : Feb 25, 2011, 7:02:40 PM
    Author     : Administrator
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            Statement stmt=null;
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from app.feedb");
            }
        catch(Exception e)
                {


            }


String x=request.getParameter("lgn");
 String y=request.getParameter("pwd");



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body><html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="blueviolet" text="white">

       Hi <%=x %>

       <%
       java.util.Date date=new java.util.Date();
%><br>
       Time now is:<%=date.toString()%>
       <br><br>
        <h1>VIEW FEEDBACK ON FASTLEARN WEB SYSTEM!</h1>
        <table>
            <tr><td>LOGIN ID</td><td>FEEDBACK</td><td>DATE</td></tr>
        <%
        while(rs.next())
            out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getDate(3).toString()+"</td></tr>");
        

        %>
        </table>
    </body>
</html>
<%
            rs.close();
            con.close();
            out.close();


%>
