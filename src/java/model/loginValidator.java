/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.lang.String;
import model.user;

/**
 *
 * @author Administrator
 */
public class loginValidator extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */user y;
    @Override
    public void init()
    {
        y=new user();


    }
    @Override
    public void destroy()
    {
        y=null;

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        y=(user)request.getAttribute("userDetails");
        String forwardPath = null;

        //Database Connections
        Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        String driver="org.apache.derby.jdbc.ClientDriver";
        boolean allOK=false;
        boolean valOK=false;
          try
    {
    Class.forName(driver);
    con=DriverManager.getConnection(url,username, password);
     Statement stmt;
                ResultSet rs;
                stmt=con.createStatement();
                rs=stmt.executeQuery("select distinct * from app.login_users");
                
                allOK=true;
                
            while(rs.next())
            {
                //out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
                if((rs.getString(1).equals(y.loginName.toString()))&&(rs.getString(2).equals(y.loginPassword.toString()))&&rs.getString(3).equals(y.loginRole.toString()))
                {
                    valOK=true;
                    break;
                }
                

            }
           // out.println("</table>");
            if(valOK==true)
            {
                if(y.loginRole.toString().equals("administrator"))
                    forwardPath="adminmenu.jsp";
                if(y.loginRole.toString().equals("faculty"))
                    forwardPath="facultymenu.jsp";
                if(y.loginRole.toString().equals("student"))
                    forwardPath="studentmenu.jsp";

                request.setAttribute("userDetails", y);

                RequestDispatcher d=request.getRequestDispatcher(forwardPath);
                d.include(request, response);
                }

            else
            {
                out.println("<html>");
            out.println("<head>");
            out.println("<title>Validating your credentials...</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"blueviolet\">");
            out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
            out.println("<h1>Servlet loginValidator at " + request.getContextPath () + "</h1>");
            out.println("You have entered your login as :"+y.loginName);
            out.println("<br>");
            out.println("You want to login as :"+y.loginRole);
             while(rs.next())
            {
                //out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
                if((rs.getString(1).equals(y.loginName.toString()))&&(rs.getString(2).equals(y.loginPassword.toString()))&&rs.getString(3).equals(y.loginRole.toString()))
                {
                    valOK=true;
                    break;
                }


            }
            out.println("<br>");
            out.println("Please wait while our server validates your credentials and redirects you to the appropriate page!");
            if(allOK==true)
                out.println("<h1>Connection Succesful!!!</h1>");
            else
                out.println("<h1>Connection failiure!!!</h1>");

                  out.println("<br>");

            out.println("<br>");
                out.println("<h1>Validation unsuccesful!Please try again!</h1>");

            out.println("<br>");

            out.println("<br>");
            //out.println("<a href=\"http:////localhost:8080//FastLearn//index.jsp\">Click here to go back to home page!</a>");
            out.println("<br>");

            out.println("<br>");

            out.println("</body>");

            out.println("</html>");
            }
    
    
    }
    catch(Exception e)
    {
        allOK=false;
         

    }
       finally { 
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
            Logger.getLogger(loginValidator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loginValidator.class.getName()).log(Level.SEVERE, null, ex);
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
