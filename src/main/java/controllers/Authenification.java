package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

import dataLayer.DataAccountsManager;

/**
 * Servlet implementation class Authenification
 */
@WebServlet("/Authenification")
public class Authenification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
   
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

	static public void isconnected() {
		return ;
	}
	
}
