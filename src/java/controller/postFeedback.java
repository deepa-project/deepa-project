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
import javax.servlet.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class postFeedback extends HttpServlet {

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
        String feedbackString=request.getParameter("feedback_input").toString();
        int num=0;

        try
        {
            String loginX=request.getParameter("lgn");
            Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        String driver="org.apache.derby.jdbc.ClientDriver";
        Class.forName(driver);
            con=DriverManager.getConnection(url, username, password);
            Statement stmt=con.createStatement();
          java.util.Date dt=new java.util.Date();
          long t=dt.getTime();
          java.sql.Date dbDate=new java.sql.Date(t);
            
            num=stmt.executeUpdate("insert into app.fl_feedb values('"+loginX+"','"+feedbackString+"','"+dbDate+"')");
            stmt.close();
            con.close();

        }
        catch(Exception e)
        {

        }
        finally
        {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet postFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet postFeack at " + request.getContextPath () + "</h1>");
            out.println(feedbackString);
            if(num>0)
            {
                out.println("Thank you for your feedback!");

            }
            else
                out.println("Failiure");
            out.println("</body>");
            out.println("</html>");

        }

            out.close();

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