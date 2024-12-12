/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class deleteStudentInformation extends HttpServlet {
   
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

        String studLogin=request.getParameter("student_login").toString();
        if(studLogin.equals(""))
        {
        try {
            // TODO output your page here
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 Connection connection = DriverManager.getConnection(connectionURL, "admin", "password");

PreparedStatement psmnt =null;
ResultSet result=null;
            out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN COURSE INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
//out.println("<h1>ADD COURSE INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());

            out.println("<h1>DELETE STUDENT INFORMATION</h1>");
out.println("<form action=\"./deleteStudentInformation\">");
out.println("Enter the login ID of the Student whose information needs to be deleted:");
//out.println("Please refer to table above:");
//out.println("Select Student Login whose details have to be updated:");
out.println("<br>");


out.println("<select name=\"student_login\">");
psmnt=connection.prepareStatement("select student_login,student_name from app.fl_students");
result=psmnt.executeQuery();
while(result.next())
{
    String sLogin=result.getString(1);
    String sName=result.getString(2);

    out.println("<option value=\""+sLogin+"\">"+sLogin+" "+sName+"</option>");


}
out.println("</select");
out.println("<br><hr>");

out.println("<input type=\"text\" name=\"student_login\" value=\"\" /><font color=\"red\">Please enter valid ID</font>");
out.println("<br>");
out.println("<input type=\"submit\" value=\"DELETE THIS STUDENT S INFORMATION\" />");
out.println("</form>");

            out.println("</body>");
            out.println("</html>");
            //
        }catch(Exception e)
        {
            out.println(e.toString());
        }

        finally {
            out.close();
        }
        }

        else
        {


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
            PreparedStatement stmt1=con.prepareStatement("delete from app.fl_students where student_login=?");
            stmt1.setString(1,studLogin);
            stmt1.executeUpdate();
            stmt1.close();
            PreparedStatement stmt2=con.prepareStatement("delete from app.login_users where login=?");
            stmt2.setString(1,studLogin);
            stmt2.executeUpdate();
            stmt2.close();

            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from app.fl_students");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Query Answer page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Student Information Systems</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>Delete Student Info</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
         out.println("<table border=2><tr><th>STUDENT LOGIN ID</th><th>Name </th><th>Address</th><th>Phone</th><th>Course</th><th>Faculty</th><th>Study Center</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");

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

           out.close();
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
            Logger.getLogger(deleteStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(deleteStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
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
