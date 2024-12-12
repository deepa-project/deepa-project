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
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import java.lang.Boolean;
import java.sql.*;
import model.faculty;
import model.user;
/**
 *
 * @author Administrator
 */
public class updateFacultyInformation extends HttpServlet {

     faculty newFaculty;
    String f_login,f_name,f_address,f_course,f_email;
    Hashtable errors;

        @Override
    public void init()
    {

        newFaculty=new faculty();
        f_login=null;
        f_name=null;
        f_address=null;
        f_course=null;
        f_email=null;
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
    throws ServletException, IOException, SQLException {
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
      f_login=request.getParameter("facLogin").toString();
      f_name=request.getParameter("facName").toString();
      f_address=request.getParameter("facAddress").toString();
      f_course=request.getParameter("facCourse").toString();
      f_email=request.getParameter("facEmail").toString();
      if(f_login.equals(""))
      {
          errors.put("ID","Please enter the faculty's unique ID");
          err++;
      }
      else
      {
          errors.put("ID","");
      }
      if(f_name.equals(""))
      {
          errors.put("Name","Please enter the faculty's name!");
          err++;
      }
      else
      {
          errors.put("Name","");
      }
      if(f_address.equals(""))
      {
          errors.put("Address","Please enter faculty's address!");
          err++;
      }
      else
      {
          errors.put("Address","");
      }
      if(f_course.equals(""))
      {
          errors.put("Course","Please enter a valid name for the Course field");
          err++;
      }
      else
      {
          errors.put("Course","");
      }

        if(f_email.equals(""))
      {
          errors.put("Email","Please enter a valid Email Address!");
          err++;
      }
      else
      {
          if((f_email.indexOf("@")==-1))
          {
              errors.put("Email","Invalid format of email address!!!Please re-enter!");
          err++;
          }
          else
              errors.put("Email","");
      }

      newFaculty.setFacultyLogin(f_login);
         newFaculty.setFacultyName(f_name);
         newFaculty.setFacultyAddress(f_address);
         newFaculty.setFacultyCourse(f_course);
         newFaculty.setFacultyEmail(f_email);
     if(err==0)
     {
         
         PreparedStatement stmt = null;
         ResultSet rs;
         try
         {
              stmt=con.prepareStatement("update app.fl_faculty set faculty_name=?,faculty_address=?,course_name=?,faculty_email=? where faculty_login=?");
              stmt.setString(5,newFaculty.getFacultyLogin());
              stmt.setString(1,newFaculty.getFacultyName());
              stmt.setString(2,newFaculty.getFacultyAddress());
              stmt.setString(3,newFaculty.getFacultyCourse());
              stmt.setString(4,newFaculty.getFacultyEmail());
              stmt.executeUpdate();
              allOK=true;
                if(allOK==true)
               out.println("Succesful Update!");
                else
                    out.println("Insertion error!");
                Statement stmt1 = null;
                stmt1=con.createStatement();
                rs=stmt1.executeQuery("select * from app.fl_faculty");                //Display results
out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN FACULTY INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>ADD FACULTY INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());
out.println("<hr>");
out.println("Details saved to Database!!!");
 out.println("<table border=2><tr><th>FACULTY LOGIN ID</th><th>NAME</th><th>ADDRESS</th><th>EMAIL</th><th>COURSE TAUGHT BY THE FACULTY</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td></tr>");
 }
out.println("</table>");
out.println("</body>");
out.println("</html>");
rs.close();
stmt1.close();
con.close();
stmt.close();
         }
         catch(Exception e)
         {

         }
     }//end of if

      else
     {

          con=null;
        url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
         username="admin";
        password="password";
        ResultSet rs=null;
        driver="org.apache.derby.jdbc.ClientDriver";
        try {
                    try {
                        //database connections
                        Class.forName(driver);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(updateFacultyInformation.class.getName()).log(Level.SEVERE, null, ex);
                    }
            con=DriverManager.getConnection(url, username, password);
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from app.fl_faculty");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>FASTLEARN FACULTY INFORMATION SYSTEM</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Login Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Faculty Info!!!</center></h3>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<body>");

           // out.println("<h1>Servlet viewQuery at " + request.getContextPath () + "</h1>");
            out.println("<table border=2>");
          out.println("<table border=2><tr><th>FACULTY LOGIN ID</th><th>NAME</th><th>ADDRESS</th><th>FACULTY EMAIL</th><th>COURSE TAUGHT BY THE FACULTY</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td></tr>");
 }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
             stmt.close();
            con.close();
          out.println("<html>");
          }
        catch(Exception e)
        {

        }
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN FACULTY INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>UPDATE FACULTY INFO!</h1>");
out.println("Hi admin");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is:");
out.println(date.toString());
out.println("<br><br>");
out.println("<hr>");
out.println("<center>FACULTY INFORMATION SYSTEMS</center>");
out.println("<b> Enter the following details:</b><br>");
out.println("<form action=\"./updateFacultyInformation\" method=\"POST\">");
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


//out.println("Enter Faculty Login:<input type=\"text\" name=\"facLogin\" value=\""+newFaculty.getFacultyLogin()+"\" />(LOGIN ID MUST BE PICKED FROM THE TABLE)");
//out.println("<font color=\"red\">");out.println(errors.get("ID").toString());out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Name:<input type=\"text\" name=\"facName\" value=\""+newFaculty.getFacultyName()+"\" />");
out.println("<font color=\"red\">");out.println(errors.get("Name").toString());out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Address:<input type=\"text\" name=\"facAddress\" value=\""+newFaculty.getFacultyAddress()+"\" />");
out.println("<font color=\"red\">");out.println(errors.get("Address").toString());out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Email:<input type=\"text\" name=\"facEmail\" value=\""+newFaculty.getFacultyEmail()+"\" />");
out.println("<font color=\"red\">");out.println(errors.get("Email").toString());out.println("</font>");
out.println("<br>");
out.println("Enter the name of the Course taught by the faculty:");
out.println("<br>");
//out.println("(Please enter only those courses present in the course table to avoid database entry conflicts,thank you!)");
//out.println("New courses must be updated in the Course table first!!!");
//out.println("<br>");
//out.println("<input type=\"text\" name=\"facCourse\" value=\""+newFaculty.getFacultyCourse()+"\" />");
//out.println("<font color=\"red\">");out.println(errors.get("Course").toString());out.println("</font>");
out.println("<br><hr>");
out.println("Select Course:");
out.println("<br>");
out.println("<select name=\"facCourse\">");
 psmnt=con.prepareStatement("select course_name from app.fl_course");
result=psmnt.executeQuery();
while(result.next())
{
    String cName=result.getString(1);


  out.println("<option value=\""+cName+"\">"+cName+"</option>");


}
out.println("</select>");

out.println("<br>");
out.println("<hr>");
out.println("<input type=\"submit\" value=\"UPDATE FACULTY DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" /><hr>");
out.println("</form>");
out.println("<hr>");
out.println(" </body>");
out.println("</html>");
      }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addFacultyInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFacultyInformation at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally {
            out.close();
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
            Logger.getLogger(updateFacultyInformation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateFacultyInformation.class.getName()).log(Level.SEVERE, null, ex);
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
        @Override
    public void destroy()
    {

        newFaculty=null;
        f_login=null;
        f_name=null;
        f_address=null;
        f_course=null;
        f_email=null;
       errors=null;
    }

}
