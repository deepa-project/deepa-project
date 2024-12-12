/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Administrator
 */
public class deleteFacultyInformation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String facultyLogin=request.getParameter("facLogin").toString();
        if(facultyLogin.equals(""))
        {
        try {
            Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        ResultSet rs=null;
        String driver="org.apache.derby.jdbc.ClientDriver";
            //TODO output your page here
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
            out.println("<h1>Servlet deleteFacultyInformation at " + request.getContextPath () + "</h1>");
            out.println("<br><hr>");
out.println("<h1>DELETE FACULTY DETAILS</h1>");
out.println("<form action=\"./deleteFacultyInformation\" method=\"POST\">");
//out.println("Enter Faculty Login:<input type=\"text\" name=\"facLogin\" value=\"\" />(LOGIN ID MUST BE PICKED FROM THE TABLE)");
out.println("<br>");
out.println("<br><hr>");
out.println("Select Faculty:");
out.println("<br>");
out.println("<select name=\"facLogin\">");
PreparedStatement psmnt=con.prepareStatement("select * from app.fl_faculty");
ResultSet result=psmnt.executeQuery();
while(result.next())
{
    String cName=result.getString(4);
    String fName=result.getString(2);
    String fLogin=result.getString(1);

  out.println("<option value=\""+fLogin+"\">"+fName+" "+cName+"</option>");


}
out.println("</select>");

//out.println("<font color=\"red\">Please enter valid Login from table</font>");
out.println("<hr>");
out.println("<input type=\"submit\" value=\"DELETE FACULTY DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" />");
out.println("</form>");

            out.println("</body>");
            out.println("</html>");
            
        } catch(Exception e)
        {
            out.println(e.toString());
        }


        finally {
            out.close();
        }
    }//end of if
        else
        {
            
            
            
            
            
            
            //reproduce faculty table page
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
            PreparedStatement stmt1 = con.prepareStatement("delete from app.fl_faculty where faculty_login=?");
                stmt1.setString(1, facultyLogin);
                stmt1.executeUpdate();
                stmt1.close();
                PreparedStatement stmt2 = con.prepareStatement("delete from app.login_users where login=?");
                stmt2.setString(1, facultyLogin);
                stmt2.executeUpdate();
                stmt2.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
