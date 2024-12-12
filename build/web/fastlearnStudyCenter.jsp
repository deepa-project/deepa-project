<%-- 
    Document   : fastlearnStudyCenter
    Created on : Feb 27, 2011, 12:16:12 PM
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
            rs=stmt.executeQuery("select * from app.fl_studycenter");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn STUDYCENTER INFORMATION SYSTEM page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Study Center Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Study Center Info!!!</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
        out.println("<br><br>");
        out.println("<center><img alt=\"\"  src=\"studycenter.bmp\" /></center>");
        out.println("<br><br>");
        out.println("<hr>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
           out.println("<table border=2><tr><th>StudyCenter ID</th><th>Name </th><th>Location</th></tr>");
 while(rs.next())
 {
     
     out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");

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
        <title>FASTLEARN STUDYCENTER</title>
    </head>
    <body>
        <h1>FASTLEARN STUDYCENTER INFORMATION SYSTEMS</h1>
        <br>
        <h3> ADD FASTLEARN STUDYCENTER INFORMATION</h3>
        <form action="./addStudyCenterInformation" method="post">
            <br>   Enter FastLearn Study Center's Unique ID:<input type="text" name="flsc_id" value="" />
            <br> Enter name of the Study Center:<input type="text" name="flsc_name" value="" />This must be unique!
            <br> Enter the location of the FastLearn Study Center:<input type="text" name="flsc_location" value="" />
            <br> <input type="submit" value="ADD STUDYCENTER DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />
        </form>
        <br><br><hr><hr>
        <h3> UPDATE FASTLEARN STUDYCENTER INFORMATION</h3>
        <form action="./updateStudyCenterInformation" method="post">
            <br> Enter FastLearn Study Center's Unique ID:<input type="text" name="flsc_id" value="" />
            <br> 
            <b>Select a Study Center:</b>

  
<select name="flsc_name">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
Connection connection= DriverManager.getConnection(connectionURL, "admin", "password");
PreparedStatement psmnt = connection.prepareStatement("select studycenter_name from app.fl_studycenter ");
ResultSet results = psmnt.executeQuery();
while(results.next()){
String studycenterName = results.getString(1);

%><option value="<%= studycenterName %>"><%=studycenterName %></option>
<%} results.close(); psmnt.close(); %>
</select><br>



           <br>  Enter the location of the FastLearn Study Center:<input type="text" name="flsc_location" value="" />
           <br> <input type="submit" value="UPDATE STUDYCENTER DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />
        </form>
        <br><br><hr><hr>

        <h3> DELETE FASTLEARN STUDYCENTER INFORMATION</h3>
        <form action="./deleteStudyCenterInformation" method="post">


           <b>Select a Study Center:</b>


<select name="flsc_name">
<%
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
 connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 connection= DriverManager.getConnection(connectionURL, "admin", "password");
 psmnt = connection.prepareStatement("select studycenter_name from app.fl_studycenter ");
 results = psmnt.executeQuery();
while(results.next()){
String studycenterName = results.getString(1);

%><option value="<%= studycenterName %>"><%=studycenterName %></option>
<%} results.close(); psmnt.close(); %>
</select><br>

           <br> <input type="submit" value="DELETE STUDYCENTER DETAILS" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="CANCEL" />
        </form>
    </body>
</html>
