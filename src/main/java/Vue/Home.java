package Vue;
import controllers.Authenification;
import java.io.IOException;

import dataLayer.DataAccountsManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;
import controllers.Authenification;
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() { 
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   HttpSession session = request.getSession(false);
           if(!Authenification.isconnected((String) session.getAttribute("username"))) {
        	   response.sendRedirect("Login");
           }
           
           
        }
   
	}
	
