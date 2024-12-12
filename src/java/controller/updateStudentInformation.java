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
import model.student;
import model.user;
/**
 *
 * @author Administrator
 */
public class updateStudentInformation extends HttpServlet {

      student newStudent;
    String s_login,s_name,s_address,s_phone,s_course,s_faculty,s_studycenter,s_email;
    Hashtable errors;


      @Override
    public void init()
    {
         newStudent=new student();
         s_login=null;
         s_name=null;
         s_address=null;
         s_phone=null;
         s_course=null;
         s_faculty=null;
         s_studycenter=null;
         s_email=null;
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
    throws ServletException, IOException,java.sql.SQLException {
        response.setContentType("text/html;charset=UTF-8");
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
      // c_prereq=request.getParameter("course_prereq").toString();
       s_login=request.getParameter("studLogin").toString();
       s_name=request.getParameter("studName").toString();
       s_address=request.getParameter("studAddress").toString();
       s_phone=request.getParameter("studPhone").toString();
       s_course=request.getParameter("studCourse").toString();
       s_faculty=request.getParameter("studFaculty").toString();
       s_studycenter=request.getParameter("studCenter").toString();
       s_email=request.getParameter("studEmail").toString();
       if(s_login.equals(""))
            {
                errors.put("ID","Please enter the Student's Unique ID");
                err++;

                }
            else
            {
                errors.put("ID", "");
                }
       if(s_name.equals(""))
            {
                errors.put("Name","Please enter the Student's Name");
                err++;

                }
            else
            {
                errors.put("Name", "");
                }
if(s_address.equals(""))
            {
                errors.put("Address","Please enter the Student's Address");
                err++;

                }
            else
            {
                errors.put("Address", "");
                }
if(s_phone.equals(""))
            {
                errors.put("Phone","Please enter the Student's Unique ID");
                err++;

                }
            else
            {
                errors.put("Phone", "");
                }
        if(s_email.equals(""))
      {
          errors.put("Email","Please enter a valid Email Address!");
          err++;
      }
      else
      {
          if((s_email.indexOf("@")==-1))
          {
              errors.put("Email","Invalid format of email address!!!Please re-enter!");
          err++;
          }
          else
              errors.put("Email","");
      }
      newStudent.setStudentLogin(s_login);
                newStudent.setStudentName(s_name);
                newStudent.setStudentAddress(s_address);
                newStudent.setStudentPhone(s_phone);
                newStudent.setStudentCourse(s_course);
                newStudent.setStudentFaculty(s_faculty);
                newStudent.setStudentStudyCenter(s_studycenter);
                newStudent.setStudentEmail(s_email);

            if(err==0)
            {
                
                 PreparedStatement stmt = null;
                ResultSet rs;
                try
                {

                   stmt=con.prepareStatement("update app.FL_STUDENTS set student_name=?,student_address=?,student_phone=?,course_name=?,faculty_name=?,studycenter_name=?,student_email=? where student_login=?");
                    stmt.setString(8,newStudent.getStudentLogin());
                    stmt.setString(1, newStudent.getStudentName());
                    stmt.setString(2,newStudent.getStudentAddress());
                    stmt.setString(3,newStudent.getStudentPhone());
                    stmt.setString(4,newStudent.getStudentCourse());
                    stmt.setString(5, newStudent.getStudentFaculty());
                    stmt.setString(6,newStudent.getStudentStudyCenter());
                    stmt.setString(7,newStudent.getStudentEmail());


               stmt.executeUpdate();
               allOK=true;
                if(allOK==true)
               out.println("Succesful Update!");
                else
                    out.println("Insertion error!");

                Statement stmt1 = null;



             stmt1=con.createStatement();


                rs=stmt1.executeQuery("select * from app.fl_students");



                //Display results
                out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN STUDENT INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>ADD STUDENT INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is :"+date.toString());

out.println("<hr>");
 out.println("Details saved to Database!!!");
 out.println("<table border=2><tr><th>STUDENT LOGIN ID</th><th>Name </th><th>Address</th><th>Phone</th><th>Email</th><th>Course</th><th>Faculty</th><th>Study Center</th></tr>");
 while(rs.next())
 {
 out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td></tr>");

}


out.println("</table>");
out.println("</body>");
out.println("</html>");
rs.close();stmt1.close();
con.close();
stmt.close();







                }//end of try
                catch(Exception e)
                {

                }





            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addStudentInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addStudentInformation at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
                }
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
            //database connections
            Class.forName(driver);
            con=DriverManager.getConnection(url, username, password);
            Statement stmt=con.createStatement();
            rs=stmt.executeQuery("select * from app.fl_students");
             // TODO output your page here
            out.println("<html>");
                out.println("<title>Fastlearn Query Answer page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");

                 out.println(" <h1><center>FastLearn Student Page</center></h1>");
                 out.println("<br><br>");
                 out.println(" <h3><center>View Student Info!!!</center></h3>");
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
           try
           {

               //Connection for dropdown box
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
String connectionURL = "jdbc:derby://localhost:1527/fastlearn123";
 Connection connection = DriverManager.getConnection(connectionURL, "admin", "password");

PreparedStatement psmnt =null;
ResultSet result=null;

               out.println("<html>");
out.println("<head>");
out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >");
out.println("<title>FASTLEARN STUDENT INFORMATION SYSTEM</title>");
out.println("</head>");
out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
out.println("<h1>UPDATE STUDENT INFO!</h1>");
out.println("Hi admin,");
out.println("<center> <img alt=\"\"src=\"STICKMAN.gif\"></center>");
out.println("<br><br>");
java.util.Date date=new java.util.Date();
out.println("Time now is:"+date.toString());
        out.println("<br><br>");
out.println("<hr>");
out.println("<center>STUDENT INFORMATION SYSTEMS</center>");
out.println("<b> Enter the following details:</b><br>");
out.println("<form action=\"./updateStudentInformation\" method=\"POST\">");
out.println("Select Student Login whose details have to be updated:");
out.println("<br>");


out.println("<select name=\"studLogin\">");
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

out.println(" Student Name:<input type=\"text\" name=\"studName\" value=\""+newStudent.getStudentName()+"\" /><font color=\"red\">"+errors.get("Name").toString() +"</font><br>");
out.println(" Student Address:(Please enter valid address,Null values shall not be accepted)<br>");
out.println("<textarea name=\"studAddress\" rows=\"4\" cols=\"20\">");out.println("</textarea>");
out.println("<font color=\"red\">");errors.get("Address").toString();
out.println("</font></textarea>");out.println("<br>");
out.println("Student Phone:<input type=\"text\" name=\"studPhone\" value=\""+newStudent.getStudentPhone()+"\" /><font color=\"red\">"+errors.get("Phone").toString() +"</font><br>");
out.println("Student Email:<input type=\"text\" name=\"studEmail\" value=\""+newStudent.getStudentEmail()+"\" /><font color=\"red\">"+errors.get("Email").toString() +"</font><br>");
out.println("<br>");
out.println("Select a Course:");
out.println("<br>");
out.println("<select name=\"studCourse\">");
psmnt=connection.prepareStatement("select course_name from app.fl_faculty");
result=psmnt.executeQuery();
while(result.next())
{
    String cName=result.getString(1);

    out.println("<option value=\""+cName+"\">"+cName+"</option>");


}
out.println("</select");
out.println("<br><hr>");
out.println("Select Faculty:");
out.println("<br>");
out.println("<select name=\"studFaculty\">");
psmnt=connection.prepareStatement("select * from app.fl_faculty");
result=psmnt.executeQuery();
while(result.next())
{
    String cName=result.getString(4);
    String fName=result.getString(2);
    String fLogin=result.getString(1);

  out.println("<option value=\""+fLogin+"\">"+fName+" "+cName+"</option>");


}
out.println("</select>");

out.println("Study Center where the student shall attend classes:*");
out.println("<br><hr>");
out.println("Select StudyCenter:");
out.println("<br>");
out.println("<select name=\"studCenter\">");
psmnt=connection.prepareStatement("select studycenter_name from app.fl_studycenter");
result=psmnt.executeQuery();
while(result.next())
{

    String scName=result.getString(1);


  out.println("<option value=\""+scName+"\">"+scName+"</option>");


}
out.println("</select");
out.println("<br><hr>");
//out.println("<input type=\"text\" name=\"studCenter\" value=\""+newStuden
out.println("<input type=\"submit\" value=\"UPDATE STUDENT DETAILS\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\" value=\"CANCEL\" />");
out.println("</form>");
out.println("<font color=\"red\"> * Please view the corresponding web pages for appropriate values as these fields<br>");
out.println("have foreign key constraints on them!!!");
out.println("If you do not know the correct entries, leave the as null to be updated later!</font>");
out.println("</body>");
out.println("</html>");





           }
            catch (InstantiationException ex) {
                Logger.getLogger(updateStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(updateStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
            }            catch (ClassNotFoundException ex) {
                Logger.getLogger(updateStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
            }

        finally {
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
            Logger.getLogger(updateStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateStudentInformation.class.getName()).log(Level.SEVERE, null, ex);
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
         newStudent=null;
         s_login=null;
         s_name=null;
         s_address=null;
         s_phone=null;
         s_course=null;
         s_faculty=null;
         s_studycenter=null;
         s_email=null;
        errors=null;
    }

}
