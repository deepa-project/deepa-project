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
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import model.user;
import java.util.*;
import java.util.logging.*;
import java.sql.*;



/**
 *
 * @author Administrator
 */
public class roleBasedRedirection extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occur;
     */
    String loginRole;
    String loggedinRole;
    String userX;
    String passwordX;
    Hashtable errors=new Hashtable();
    String forwardPath;
    
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        user x;
        
        try {
            loginRole=request.getParameter("role");
            if(loginRole.equals("student"))
                loggedinRole="student";
            if(loginRole.equals("faculty"))
                loggedinRole="faculty";
            if(loginRole.equals("administrator"))
                loggedinRole="administrator";
             userX=request.getParameter("login");
            passwordX=request.getParameter("password");
            x=new user();
            if(userX.equals(""))
            {
                errors.put("login","Invalid login!Please enter correct login!");

            }//end of if
            else
                x.setLoginName(userX);
            if(passwordX.equals(""))
            {
                errors.put("password","Invalid Password!Please enter correct password!");
            }//end of if
            else
                x.setLoginPassword(passwordX);

           
            x.setLoginRole(loginRole);

            
           
            if(userX.equals("")||passwordX.equals(""))
            {
                
                
                out.println("<html>");
                out.println("<title>Fastlearn Login page</title>");
                out.println("<body bgcolor=\"blueviolet\" text=\"white\">");
               
                 out.println(" <h1><center>FastLearn Login Page</center></h1>");
        out.println("<center><img alt=\"\"  src=\"STICKMAN.gif\" /></center>");
        out.println("<form action=\"./roleBasedRedirection\"  name=\"form1\">");
           out.println("<hr>");
          out.println("  Enter your login:<input type=\"text\" name=\"login\" value=\"admin\" />");
           if(errors.get("login")!="")
               out.println( errors.get("login").toString());
         out.println("<br>");
           out.println(" Enter your password:<input type=\"password\" name=\"password\" value=\"password\" />");
          if(errors.get("password")!="") out.println(errors.get("password").toString());
           out.println("<br>");out.println("<br>");
           out.println("You are logging in as:");
           out.println("<select name=\"role\">");
                out.println("<option >student</option>");
               out.println(" <option>faculty</option>");
              out.println("  <option>administrator</option>");
            out.println("</select>");
            out.println("<hr>");
           out.println(" <input type=\"submit\" value=\"LOGIN\" />");
          out.println(" <input type=\"reset\" value=\"CANCEL\" /><br>");
       out.println("</form>");
       out.println("<hr>");
out.println("<body>");
out.println("<html>");

errors.put("login","");
errors.put("password","");
            }//endof if
            else
            {
                request.setAttribute("userDetails", x);
            RequestDispatcher view=request.getRequestDispatcher("loginValidator");
            view.forward(request, response);

              }//end of if-else
            }//end of try
        catch(Exception e)
        {             
            
            
            
            
        }//endof try-catch


        finally {
            out.close();
        }
    }//end of preprocess method()

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


  



    }//end of class



