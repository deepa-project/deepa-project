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
public class addFacultyInformation extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
              stmt=con.prepareStatement("insert into app.fl_faculty values(?,?,?,?,?)");
              stmt.setString(1,newFaculty.getFacultyLogin());
              stmt.setString(2,newFaculty.getFacultyName());
              stmt.setString(3,newFaculty.getFacultyAddress());
              stmt.setString(4,newFaculty.getFacultyCourse());
              stmt.setString(5,newFaculty.getFacultyEmail());
              stmt.executeUpdate();
              PreparedStatement stmtLogin=null;
              stmtLogin=con.prepareStatement("insert into app.login_users values(?,?,?)");
              stmtLogin.setString(1,newFaculty.getFacultyLogin());
              String pwd="password";
              String role="faculty";
              stmtLogin.setString(2, pwd);
              stmtLogin.setString(3,role);
              stmtLogin.executeUpdate();
              stmtLogin.close();
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
 out.println("<table border=2><tr><th>FACULTY LOGIN ID</th><th>NAME</th><th>ADDRESS</th><th>EMAIL ADDRESS</th><th>COURSE TAUGHT BY THE FACULTY</th></tr>");
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
          out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN FACULTY INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>ADD FACULTY INFO!</h1>");
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
out.println("<form action=\"./addFacultyInformation\" method=\"POST\">");
out.println("Enter Faculty Login:<input type=\"text\" name=\"facLogin\" value=\""+newFaculty.getFacultyLogin()+"\" />");
out.println("<font color=\"red\">");
out.println(errors.get("ID").toString());
out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Name:<input type=\"text\" name=\"facName\" value=\""+newFaculty.getFacultyName()+"\" />");out.println("<font color=\"red\">");out.println(errors.get("Name").toString());out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Address:<input type=\"text\" name=\"facAddress\" value=\""+newFaculty.getFacultyAddress()+"\" />");out.println("<font color=\"red\">");out.println(errors.get("Address").toString());out.println("</font>");
out.println("<br>");
out.println("Enter Faculty's Email Address:<input type=\"text\" name=\"facEmail\" value=\""+newFaculty.getFacultyEmail()+"\" />");out.println("<font color=\"red\">");out.println(errors.get("Email").toString());out.println("</font>");
out.println("<br>");
out.println("Enter the name of the Course taught by the faculty:");
out.println("(Please enter only those courses present in the course table to avoid database entry conflicts,thank you!)");
out.println("New courses must be updated in the Course table first!!!");
out.println("<br>");
out.println("<input type=\"text\" name=\"facCourse\" value=\""+newFaculty.getFacultyCourse()+"\" />");
out.println("<font color=\"red\">");
out.println(errors.get("Course").toString());
out.println("</font>");
out.println("<br>");
out.println("<hr>");
out.println("<input type=\"submit\" value=\"ADD FACULTY DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" value=\"CANCEL\" /><hr>");
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

        newFaculty=null;
        f_login=null;
        f_name=null;
        f_address=null;
        f_course=null;
        f_email=null;
       errors=null;
    }

}
