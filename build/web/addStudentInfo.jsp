<%-- 
    Document   : addStudentInfo
    Created on : Feb 25, 2011, 9:26:25 AM
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
            rs=stmt.executeQuery("select * from app.fl_students");
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
         out.println("<table border=2><tr><th>STUDENT LOGIN ID</th><th>Name </th><th>Address</th><th>Phone</th><th>Email</th><th>Course</th><th>Faculty</th><th>Study Center</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");

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
        <title>FASTLEARN STUDENT INFORMATION SYSTEM</title>
    </head>
    <body bgcolor="blueviolet" text="white">
        <h1>ADD STUDENT INFO!</h1>
        Hi <%=x %>

        <center> <img alt=""  src="STICKMAN.gif"></center>
        <br><br>
         <%
       java.util.Date date=new java.util.Date();%>Time now is:<%=date.toString()%><br><br>
        <hr>
        <center>STUDENT INFORMATION SYSTEMS</center>
        
        <b> Enter the following details:</b><br>
        <form action="./addStudentInformation" method="POST">
             Student Login:<input type="text" name="studLogin" value="" /><br>
             Student Name:<input type="text" name="studName" value="" /><br>
             Student Address:<br>
            <textarea name="studAddress" rows="4" cols="20" >
            </textarea><br>
            Student Phone:<input type="text" name="studPhone" value="" /><br>
            Student Email:<input type="text" name="studEmail" value="" /><br>
            Student wants to register for course:*<br>
            <b>Select a course:</b>
<select name="studCourse">
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


            Faculty assigned for the course:*<br>
            <b>Select a faculty:</b>
<select name="studFaculty">
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




            Study Center where the student shall attend classes:*
<br>
 <b>Select a Study Center:</b>
<select name="studCenter">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 psmnt = connection.prepareStatement("select studycenter_name from app.fl_studycenter");

 results = psmnt.executeQuery();
while(results.next()){
String scName = results.getString(1);

%><option value="<%= scName %>"><%=scName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>
            <input type="submit" value="ADD STUDENT DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="CANCEL" />
        </form>
        <font color="red"> * Please view the corresponding web pages for appropriate values as these fields<br>
            have foreign key constraints on them!!!
            If you do not know the correct entries, leave the as null to be updated later!</font>
        <br><hr>
        <br>
        <h1>UPDATE STUDENT INFO</h1>
        <br>
        <form action="./updateStudentInformation" method="POST">
            Select Student Login:
            <select name="studLogin">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 connection= DriverManager.getConnection(connectionURL, "admin", "password");
 psmnt = connection.prepareStatement("select student_login,student_name from app.fl_students ");
 results = psmnt.executeQuery();
while(results.next()){
String studentLogin=results.getString(1);
String studentName = results.getString(2);

%><option value="<%= studentLogin%>"><%=studentName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>




            <br>
             Student Name:<input type="text" name="studName" value="" /><br>
             Student Address:<br>
            <textarea name="studAddress" rows="4" cols="20">
            </textarea><br>
            Student Phone:<input type="text" name="studPhone" value="" /><br>
            Student Email:<input type="text" name="studEmail" value="" /><br>
            Student wants to register for course:*<br>
            <b>Select a course:</b>
<select name="studCourse">
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


            Faculty assigned for the course:*<br>
            <b>Select a faculty:</b>
<select name="studFaculty">
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




            Study Center where the student shall attend classes:*
<br>
 <b>Select a Study Center:</b>
<select name="studCenter">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 psmnt = connection.prepareStatement("select studycenter_name from app.fl_studycenter");

 results = psmnt.executeQuery();
while(results.next()){
String scName = results.getString(1);

%><option value="<%= scName %>"><%=scName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>

            <input type="submit" value="UPDATE STUDENT DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="CANCEL" />
        </form>
        <br><hr>
        <h1>DELETE STUDENT INFORMATION</h1>
        <form action="./deleteStudentInformation">
           
           
            Select Student Login:
            <select name="student_login">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 connection= DriverManager.getConnection(connectionURL, "admin", "password");
 psmnt = connection.prepareStatement("select student_login,student_name from app.fl_students ");
 results = psmnt.executeQuery();
while(results.next()){
String studentLogin=results.getString(1);
String studentName = results.getString(2);

%><option value="<%= studentLogin%>"><%=studentName%></option>
<%} results.close(); psmnt.close(); %>
</select><br>

            <br>
            <input type="submit" value="DELETE THIS STUDENT'S INFORMATION" />


        </form>

       
    </body>
</html>
