/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class deleteCourseInformation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException,java.sql.SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String courseName=request.getParameter("course_name");

        if(courseName.equals(""))
        {
        try {
            // TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteCourseInformation</title>");  
            out.println("</head>");
            out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
//out.println("<h1>ADD COURSE INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());

            out.println("<h1>Servlet deleteCourseInformation at " + request.getContextPath () + "</h1>");
            out.println("<h1>DELETE COURSE INFORMATION</h1>");
            //out.println("<h1>DELETE COURSE INFORMATION</h1>");
out.println("<form action=\"./deleteCourseInformation\" method=\"POST\">");
//out.println("Enter the COurse Name from the table:<input type=\"text\" name=\"course_name\" value=\"\" /><font color=\"red\">Please enter valid ID</font>");
out.println("<br>");
out.println("<br><hr>");
out.println("Select Course:");
out.println("<br>");
out.println("<select name=\"facCourse\">");
Connection con = null;
                String url = "jdbc:derby://localhost:1527/fastlearn123";
                // String dbName="fastlearn";
                String username = "admin";
                String password = "password";
PreparedStatement psmnt=con.prepareStatement("select course_name from app.fl_course");
ResultSet result=psmnt.executeQuery();
while(result.next())
{
    String cName=result.getString(1);


  out.println("<option value=\""+cName+"\">"+cName+"</option>");


}
out.println("</select>");

out.println("<br>");
out.println("<hr>");
out.println("<input type=\"submit\" value=\"DELETE\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\" value=\"CANCEL DELETE\" />");
out.println("</form>");


            out.println("</body>");
            out.println("</html>");
            
        } finally { 
            out.close();
        }
        }//end of if

        else
        {
        try {
                Connection con = null;
                String url = "jdbc:derby://localhost:1527/fastlearn123";
                // String dbName="fastlearn";
                String username = "admin";
                String password = "password";
                ResultSet rs = null;
                String driver = "org.apache.derby.jdbc.ClientDriver";
                try {
                    Class.forName(driver);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(deleteCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
                }
                con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt1 = con.prepareStatement("delete from app.fl_course where course_name=?");
                stmt1.setString(1, courseName);
                stmt1.executeUpdate();
                stmt1.close();
                try {
                    //database connections
                    Class.forName(driver);
                    con = DriverManager.getConnection(url, username, password);
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery("select * from app.fl_course");
                    // TODO output your page here
                    out.println("<html>");
                    out.println("<title>Fastlearn Course Information System page</title>");
                    out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
                    out.println(" <h1><center>FastLearn Course Information Page</center></h1>");
                    out.println("<br><br>");
                    out.println("Course Details Succesfully Deleted!");
                    out.println(" <h3><center>View Course Info!!!</center></h3>");
                    out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
                    out.println("<body>");
                    // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
                    out.println("<table border=2>");
                    out.println("<table border=2><tr><th>Course ID</th><th>Name of the Course</th><th>Course Fees</th><th>Course Prerequisites:</th></tr>");
                    while (rs.next()) {
                        out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getInt(3) + "</td><td>" + rs.getString(4) + "</td></tr>");
                    }
                    out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                    stmt.close();
                    con.close();
                } catch (Exception e) {
                } finally {
                    //out.close();
                }
            }
        catch (SQLException ex)
                {
                Logger.getLogger(deleteCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
            }






        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(deleteCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(deleteCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
