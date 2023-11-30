package Vue;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

import controllers.Authenification;
import dataLayer.DataAccountsManager;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("username"));
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        boolean valide =Authenification.validateCredentials(username, password);
        if(valide) {
             HttpSession session = request.getSession();
             session.setAttribute("username", username);
             response.sendRedirect("Home");
        }else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resource/Vue/login.jsp");
        dispatcher.forward(request, response);}
	}
	public static boolean validateCredentials(String username, String password) {
	      // another class
	    	User user = new User(username, password);
	    	DataAccountsManager dataManager = new DataAccountsManager();
	        try {
	        	System.out.print(user);
	        	System.out.print("\nnn");

	            // Use the DataAccountsManager to validate the account
	            return dataManager.ValidateAccount(user);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false; // Return false in case of an exception
	        }
			
	    }




}
