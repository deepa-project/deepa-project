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
import javax.servlet.RequestDispatcher;
import java.lang.Boolean;
import java.util.Hashtable;
import java.sql.*;
import model.course;
import model.user;


/**
 *
 * @author Administrator
 */
public class updateCourseInformation extends HttpServlet {

      course newCourse;
        String c_id,c_name,c_fee,c_prereq;
                int courseFees;
                Hashtable errors;
                int courseID;



     @Override
    public void init()
    {
        newCourse=new course();
         c_id=null;
         c_name=null;
         c_fee=null;
        courseFees=0;
        c_prereq=null;
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

        c_id=request.getParameter("course_id").toString();
        if(!(c_id.equals("")))
            courseID=Integer.parseInt(c_id);
            c_name=request.getParameter("course_name").toString();

            c_fee=request.getParameter("course_fee").toString();
            if(!(c_fee.equals("")))
            courseFees=Integer.parseInt(c_fee);
            c_prereq=request.getParameter("course_prereq").toString();

            if(c_id.equals(""))
            {
                errors.put("ID","Please enter the Course's Unique ID");
                err++;

                }
            else
            {

                errors.put("ID", "");


                }


            if(c_name.equals(""))
            {
                errors.put("Name","Enter the name of the course!");
                err++;
                }

            else  errors.put("Name","");
            if(c_fee.equals(""))
            {
                errors.put("Fee","You are not teaching for free, are you? :)");
                err++;
                }
            else
                 errors.put("Fee","");

                 newCourse.setCourseID(courseID);
               newCourse.setCourseName(c_name);
               newCourse.setCourseFees(courseFees);
               newCourse.setCoursePrereq(c_prereq);

          if(err==0)
            {
                 
                PreparedStatement stmt = null;
                ResultSet rs;

                try
                {
                stmt=con.prepareStatement("update app.fl_course set course_id=?,course_fee=?,course_prereq=? where course_name=?");
                stmt.setInt(1,newCourse.getCourseID());
                stmt.setString(4,newCourse.getCourseName());
                stmt.setInt(2,newCourse.getCourseFees());
                stmt.setString(3,newCourse.getCoursePrereq());

               stmt.executeUpdate();
               allOK=true;
               }catch(Exception e)
                {
                   out.println(e.toString());

               }
                if(allOK==true)
               out.println("Succesful Update!");
                else
                    out.println("Insertion error!");

             Statement stmt1 = null;



             stmt1=con.createStatement();


                rs=stmt1.executeQuery("select * from app.fl_course");



                //Display results
                out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN COURSE INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>UPDATE COURSE INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());

out.println("<hr>");
 out.println("Details saved to Database!!!");
 out.println("<table border=2><tr><th>Course ID</th><th>Name of the Course</th><th>Course Fees</th><th>Course Prerequisites:</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td><td>"+rs.getString(4)+"</td></tr>");

}


out.println("</table>");
out.println("</body>");
out.println("</html>");
rs.close();stmt1.close();
con.close();
stmt.close();



               /* newCourse.setCourseID(courseID);
               newCourse.setCourseName(c_name);
               newCourse.setCourseFees(courseFees);
               newCourse.setCoursePrereq(c_prereq);

               request.setAttribute("courseDetails",newCourse);
               RequestDispatcher view=request.getRequestDispatcher("./insertCourseDb");
               view.forward(request, response);*/


            }
 else
            {

        try {

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
            rs=stmt.executeQuery("select * from app.fl_course");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Query Answer page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Login Page</center></h1>");
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


            out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN COURSE INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>ADD COURSE INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());

out.println("<hr>");
out.println("<form action=\"http://localhost:8080/FastLearn/updateCourseInformation\" method=\"post\">");
out.println("ENTER COURSE ID:<input type=\"text\" name=\"course_id\" value=\""+newCourse.getCourseID()+"\" />");
out.println("<font color=\"red\">");
out.println(errors.get("ID").toString());
out.println("</font>");
out.println("<BR>");
//out.println("ENTER COURSE NAME:<input type=\"text\" name=\"course_name\" value=\""+newCourse.getCourseName()+"\" />(COURSE NAME MUST BE PICKED FROM THE TABLE)");
//out.println("<font color=\"red\">");
//out.println(errors.get("Name").toString());
//out.println("</font>");
out.println("<br><hr>");
out.println("Select Course:");
out.println("<br>");
out.println("<select name=\"facCourse\">");
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
out.println("<br>");
out.println("ENTER COURSE FEE(INR):<input type=\"text\" name=\"course_fee\" value=\""+newCourse.getCourseFees()+"\" />");
out.println("<font color=\"red\">");
out.println(errors.get("Fee").toString());
out.println("</font>");
out.println("<BR>");
out.println("ENTER COURSE PRE-REQUISITES:<input type=\"text\" name=\"course_prereq\" value=\""+newCourse.getCoursePrereq()+"\" />");
out.println("<BR>");
out.println("<input type=\"submit\" value=\"UPDATE\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\" value=\"CANCEL\" />");
out.println("</form>");
out.println("</body>");
out.println("</html>");

        } finally {
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
            Logger.getLogger(updateCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateCourseInformation.class.getName()).log(Level.SEVERE, null, ex);
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

        newCourse=null;
        c_id=null;
        c_name=null;
        c_fee=null;
         courseFees=0;
        c_prereq=null;
        errors=null;

    }

}
