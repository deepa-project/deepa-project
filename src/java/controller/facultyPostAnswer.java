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
import javax.servlet.RequestDispatcher;
import java.sql.Clob;

/**
 *
 * @author Administrator
 */
public class facultyPostAnswer extends HttpServlet {
    
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

          
            String queryText=request.getParameter("queryText").toString();
            
            out.println(queryText);
            String qAns=request.getParameter("queryAnswer").toString();
            qAns=qAns.trim();
            if(qAns.equals(""))
            {
                out.println("Invalid Answer");
                RequestDispatcher qAn=request.getRequestDispatcher("postQueryAnswer.jsp");
                qAn.include(request, response);
                }
            else
            {

            Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        ResultSet rs=null;
         //database connections
         String driver="org.apache.derby.jdbc.ClientDriver";
        Class.forName(driver);
            con=DriverManager.getConnection(url, username, password);
           
             try
             {
                 PreparedStatement stmt=con.prepareStatement("update app.fl_queryx set answer=? where query=?");
             stmt.setString(1, qAns);
             
             stmt.setString(2,queryText);
             stmt.executeUpdate();
             stmt.close();
              con.close();
               out.println("Query Update Succesful!");
               RequestDispatcher view =request.getRequestDispatcher("success.jsp");
               view.forward(request, response);
              
             }
             catch(Exception e)
             {
                 out.println(e.toString());
             }
              
            
              }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet facultyPostAnswer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet facultyPostAnswer at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        }
        catch(Exception e)
        {
            out.println("Query Update Failed!");
            out.println(e.toString());
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
            Logger.getLogger(facultyPostAnswer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(facultyPostAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(facultyPostAnswer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(facultyPostAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
