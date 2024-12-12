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
public class deleteStudyCenterInformation extends HttpServlet {
   
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
        String studycenter_name=request.getParameter("flsc_name").toString();
        if(studycenter_name.equals(""))
        {
        try {
            //TODO output your page here
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
            out.println("<h1>Servlet deleteStudyCenterInformation at " + request.getContextPath () + "</h1>");
            out.println(" <br><br><hr><hr>");
out.println("<h3> DELETE FASTLEARN STUDYCENTER INFORMATION</h3>");
out.println("<form action=\"./deleteStudyCenterInformation\" method=\"post\">");
//out.println("<br> Enter name of the Study Center:<input type=\"text\" name=\"flsc_name\" value=\"\" />Choose from table above!");
//out.println("<br><font color=\"red\">PLEASE ENTER THE NAME OF THE STUDYCENTER WHOSE DETAILS ARE TO BE DELETED!!</font><br>");
out.println("<br><hr>");
out.println("Select StudyCenter:");
out.println("<br>");
out.println("<select name=\"flsc_name\">");
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 Connection connection = DriverManager.getConnection(connectionURL, "admin", "password");

PreparedStatement psmnt =null;
ResultSet result=null;
psmnt=connection.prepareStatement("select studycenter_name from app.fl_studycenter");
result=psmnt.executeQuery();
while(result.next())
{

    String scName=result.getString(1);


  out.println("<option value=\""+scName+"\">"+scName+"</option>");


}
out.println("</select");
out.println("<br><hr>");
out.println(" <br> <input type=\"submit\" value=\"DELETE STUDYCENTER DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" />");
out.println("</form>");

            out.println("</body>");
            out.println("</html>");
            //
        } 
        catch(Exception e)
        {
            out.println(e.toString());
        }


        finally {
            out.close();
        }

        }//end of if
        else
        {
            //delete from database and display all records from updated table

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
           // Statement stmt=con.createStatement();
            PreparedStatement stmt1 = con.prepareStatement("delete from app.fl_studycenter where studycenter_name=?");
                stmt1.setString(1,studycenter_name );
                stmt1.executeUpdate();
                stmt1.close();
                //now assuming that the details have been deleted, THE WHOLE TABLE IS DISPLAYED
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
        catch(Exception e)
        {

        }
        finally
        {

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
            Logger.getLogger(deleteStudyCenterInformation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(deleteStudyCenterInformation.class.getName()).log(Level.SEVERE, null, ex);
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
