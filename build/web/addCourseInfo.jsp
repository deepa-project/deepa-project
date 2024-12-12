<%-- 
    Document   : addCourseInfo
    Created on : Feb 25, 2011, 3:36:24 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="model.user,javax.servlet.http.*" %>
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
            rs=stmt.executeQuery("select * from app.fl_course");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Course Information System page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Course Information Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Course Info!!!</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
           out.println("<table border=2><tr><th>Course ID</th><th>Name of the Course</th><th>Course Fees</th><th>Course Prerequisites:</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>FASTLEARN COURSE INFORMATION SYSTEM</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>ADD COURSE INFO!</h1>
        Hi <%=x %>
        
        <center> <img alt=""  src="STICKMAN.gif"></center>
        <br><br>
         <%
       java.util.Date date=new java.util.Date();%>Time now is:<%=date.toString()%><br><br>
        <hr>
        <center><img src="books.gif" alt=""></center>
        <hr>
        <br><hr><br>
        <h1>ADD COURSE INFORMATION</h1><BR>
        <form action="./addCourseInformation" method="post">

              ENTER COURSE ID:<input type="text" name="course_id" value="" />
            <BR>
            ENTER COURSE NAME:<input type="text" name="course_name" value="" />
            <br>
            ENTER COURSE FEE(INR):<input type="text" name="course_fee" value="" />
            <BR>
            ENTER COURSE PRE-REQUISITES:<input type="text" name="course_prereq" value="" />
            <BR>
            <input type="submit" value="ADD" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="CANCEL" />
            </form>
        <br><hr><br>
        <h1>UPDATE COURSE INFORMATION</h1>
        <form action="./updateCourseInformation" method="post">

              ENTER COURSE ID:<input type="text" name="course_id" value="" />
            <BR>
            
            <br>
             <b>Select a course:</b>
<select name="course_name">
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
            <br>
            ENTER COURSE FEE(INR):<input type="text" name="course_fee" value="" />
            <BR>
            ENTER COURSE PRE-REQUISITES:<input type="text" name="course_prereq" value="" />
            <BR>
            <input type="submit" value="UPDATE" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="CANCEL" />
            </form>

        <h1>DELETE COURSE INFORMATION</h1>
        <form action="./deleteCourseInformation" method="POST">
           <br>
             <b>Select a course:</b>
<select name="course_name">
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
            <input type="submit" value="DELETE" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="CANCEL DELETE" />
        </form>
    </body>
</html>
