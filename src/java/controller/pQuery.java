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

/**
 *
 * @author Administrator
 */
public class pQuery extends HttpServlet {
   
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
        String queryString=request.getParameter("query_input").toString();
        queryString=queryString.trim();
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
            String c3="To be answered!";

            if(!(queryString.equals("")))
            num=stmt.executeUpdate("INSERT INTO APP.FL_QUERYX (LOGIN, QUERY, ANSWER)VALUES ('"+loginX+"','"+queryString+"','"+c3+"')");
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
            out.println("<title>Servlet pQuery</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet pQuery at " + request.getContextPath () + "</h1>");
            out.println(queryString);
            if(num>0)
            {
                RequestDispatcher d=request.getRequestDispatcher("./viewQuery");
                d.forward(request, response);

            }
            else

            {

                out.println("Invalid entry!");
                 RequestDispatcher d=request.getRequestDispatcher("postQuery.jsp");
                d.forward(request, response);
                }
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
