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
import java.util.*;
import java.sql.*;
import java.sql.PreparedStatement;



import model.studycenter;

/**
 *
 * @author Administrator
 */
public class updateStudyCenterInformation extends HttpServlet {


     studycenter newStudyCenter;
    String sc_id,sc_name,sc_location;
    int sc_identity;
    Hashtable errors;

    @Override
    public void init()
    {
        sc_id=null;
        sc_name=null;
        sc_location=null;
        sc_identity=0;
        newStudyCenter=new studycenter();
        errors=new Hashtable();

    }

   
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
        Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
        String driver="org.apache.derby.jdbc.ClientDriver";
        String username="admin";
        String password="password";
         boolean allOK=false;
         int err=0;

    try
    {
    Class.forName(driver);
    con=DriverManager.getConnection(url,username, password);
    allOK=true;
    out.println(allOK);
    }
    catch(Exception e)
    {
        allOK=false;

    }
      allOK=false;
      //take input parameters
      sc_id=request.getParameter("flsc_id");
      if(!(sc_id.equals("")))
          sc_identity=Integer.parseInt(sc_id);
      sc_name=request.getParameter("flsc_name");
      sc_location=request.getParameter("flsc_location");
   //add error messages
      if(sc_id.equals(""))
      {
          errors.put("ID","Please enter a valid ID!");
          err++;
          }
      else
          errors.put("ID","");
      if(sc_name.equals(""))
      {
          errors.put("Name","Please enter a valid Name!");
          err++;
          }
      else
          errors.put("Name","");
      if(sc_location.equals(""))
      {
          errors.put("Location","Please enter a valid Location!");
          err++;
          }
      else
          errors.put("Location","");

       newStudyCenter.setStudycenter_id(sc_identity);
                newStudyCenter.setStudycenter_name(sc_name);
                newStudyCenter.setStudycenter_location(sc_location);

     if(err==0)
      {

          //perform database operation here
                
                 PreparedStatement stmt = null;
                ResultSet rs;
                 try
                {
                stmt=con.prepareStatement("update app.fl_studycenter set studycenter_id=?,studycenter_loc=? where studycenter_name=?");
                stmt.setInt(1,newStudyCenter.getStudycenter_id());
                stmt.setString(3,newStudyCenter.getStudycenter_name());
                stmt.setString(2,newStudyCenter.getStudycenter_location());

               stmt.executeUpdate();
               allOK=true;
               }catch(Exception e)
                {

               }
                if(allOK==true)
               out.println("Succesful Update!");
                else
                    out.println("Insertion error!");


                //reproduce the page
                con = null;
                url = "jdbc:derby://localhost:1527/fastlearn123";
                // String dbName="fastlearn";
                username = "admin";
                password = "password";
                rs = null;
                driver = "org.apache.derby.jdbc.ClientDriver";
                //database connections
                try
                {
                Class.forName(driver);
                con = DriverManager.getConnection(url, username, password);
             Statement stmt1 = con.createStatement();
                rs = stmt1.executeQuery("select * from app.fl_studycenter");
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
                try {
                    while (rs.next()) {
                        out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td></tr>");
                    }
                } catch (SQLException ex) {
                   //Logger.getLogger(addStudyCenterInformation.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.println("</table>");
                out.println("<br>");
                out.println("</body>");
                out.println("</html>");
                }
                catch(Exception e)
                {

                }




      }

      else
      {
          //reproduce the page
           //reproduce the page
          con=null;
         url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
         username="admin";
        password="password";
        ResultSet rs=null;
        driver="org.apache.derby.jdbc.ClientDriver";
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

            out.println("<br>");
out.println("<h3> ADD FASTLEARN STUDYCENTER INFORMATION</h3>");
out.println("<form action=\"./addStudyCenterInformation\" method=\"post\">");
out.println("<br> Enter FastLearn Study Center's Unique ID:<input type=\"text\" name=\"flsc_id\" value=\"\" />");
out.println("<br> Enter name of the Study Center:<input type=\"text\" name=\"flsc_name\" value=\"\" />This must be unique!");
out.println("<br> Enter the location of the FastLearn Study Center:<input type=\"text\" name=\"flsc_location\" value=\"\" />");
out.println("<br> <input type=\"submit\" value=\"ADD STUDYCENTER DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" />");
out.println("</form>");
out.println("<br><br><hr><hr>");
out.println("<h3> UPDATE FASTLEARN STUDYCENTER INFORMATION</h3>");
out.println("<form action=\"./updateStudyCenterInformation\" method=\"post\">");
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
out.println("<br> Enter FastLearn Study Center's Unique ID:<input type=\"text\" name=\"flsc_id\" value=\""+newStudyCenter.getStudycenter_id()+"\" />");
out.println("<font color=\"red\">"+errors.get("ID").toString()+"</font>");
//out.println("<br> Enter name of the Study Center:<input type=\"text\" name=\"flsc_name\" value=\""+newStudyCenter.getStudycenter_name()+"\" />Choose from table above!");
//out.println("<font color=\"red\">"+errors.get("Name").toString()+"</font>");
out.println(" <br>Enter the location of the FastLearn Study Center:<input type=\"text\" name=\"flsc_location\" value=\""+newStudyCenter.getStudycenter_location()+"\" />");
out.println("<font color=\"red\">"+errors.get("Location").toString()+"</font>");
out.println(" <br> <input type=\"submit\" value=\"UPDATE STUDYCENTER DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" />");


















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


     @Override
    public void destroy()
    {
        sc_id=null;
        sc_name=null;
        sc_location=null;
        sc_identity=0;
        newStudyCenter=null;
        errors=null;;

    }
}
