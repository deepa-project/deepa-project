/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import model.user;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class passwordChanger extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user z=new user();
        String forwardPath=null;
          boolean allOK=false;
        boolean valOK=false;
        int rows_updated=0;
        try
        {
        String loginX=request.getParameter("lgn");
        String new1=request.getParameter("newPassword");
        String new2=request.getParameter("confirm_newPassword");
        if(new1.equals(new2))
        {
            //Database Connections
            Connection con=null;
        String url="jdbc:derby://localhost:1527/fastlearn123";
       // String dbName="fastlearn";
        String username="admin";
        String password="password";
        String driver="org.apache.derby.jdbc.ClientDriver";

        try
        {
 Class.forName(driver);
            con=DriverManager.getConnection(url, username, password);
            PreparedStatement stmt =con.prepareStatement("update app.login_users set password=? where login =?");

            stmt.setString(1,new1);
            stmt.setString(2,loginX);
                     stmt.executeUpdate();

            stmt.close();
            con.close();
         allOK=true;
        }
        catch(Exception e)
        {
            



        }
         //allOK=true;

            }//end of success
        if(allOK==true)
             forwardPath="success.jsp";
        if(new1.equals("")||new2.equals(""))
            forwardPath="SessionTimeOut.jsp";
        if((!(new1.equals(new2)))||allOK==false)
           
            forwardPath="SessionTimeOut.jsp";


        
        }
        catch(java.lang.NullPointerException e)
        {
            out.println("Error in retrieval");
        }
           RequestDispatcher d=request.getRequestDispatcher(forwardPath);
           d.forward(request, response);

        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(passwordChanger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(passwordChanger.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(passwordChanger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(passwordChanger.class.getName()).log(Level.SEVERE, null, ex);
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
