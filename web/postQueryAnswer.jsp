<%-- 
    Document   : postQueryAnswer
    Created on : Feb 24, 2011, 3:41:02 PM
    Author     : Administrator
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.user"%>
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
            rs=stmt.executeQuery("select * from app.fl_queryx where answer is not null");





            // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Query Answer page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Login Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Queries!!!</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
            out.println("<tr><th>Query ID</th><th>LoginID</th><th>Query</th><th>Answer</th></tr>");
            while(rs.next())
            {
                out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(3)+"</td></tr>");

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
        <h1>Post Query Answers</h1>
        <form action="./facultyPostAnswer"  method="POST">

            
            <hr>

            <b>Select a query:</b>
<select name="queryText">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
Connection connection= DriverManager.getConnection(connectionURL, "admin", "password");
PreparedStatement psmnt = connection.prepareStatement("select * from app.fl_queryx");
ResultSet results = psmnt.executeQuery();
while(results.next()){
String queryString = results.getString(4);
%><option value="<%= queryString %>"><%=queryString%></option>
<%} results.close(); psmnt.close(); %>
</select><br>













            <hr>
            Enter your answer:
            <textarea name="queryAnswer" rows="4" cols="20" >
            </textarea>
            <hr>
            <input type="submit" value="Answer" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Cancel" />
            <hr>


        </form>

       
       
    </body>
</html>
