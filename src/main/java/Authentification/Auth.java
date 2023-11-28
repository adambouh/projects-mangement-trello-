package Authentification;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dataLayer.DataAccountsManager;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Account;// Import the correct class
@WebServlet("/test3/Auth")
public class Auth extends HttpServlet {
	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
     
    	System.out.print(request);   

        // Read the request body as a JSON string
        String requestBody = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        JsonParser parser = new JsonParser();
        JsonObject jsonRequest = parser.parse(requestBody).getAsJsonObject();

        // Retrieve login and password parameters from the request
        String username = jsonRequest.get("username").getAsString();
        String password = jsonRequest.get("password").getAsString();
        // this needs to be in  it s own  class and file
        boolean isValidCredentials = validateCredentials(username, password);

        // Create a JSON object using Gson to represent the response
        JsonObject jsonResponse = new JsonObject();
       
        if (isValidCredentials) {
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "Login successful");
            HttpSession session = request.getSession(true);
            if (session == null) {
                // If the session doesn't exist, create a new one
                session = request.getSession(true);
            }
            System.out.println("AAAAAAAAAAAAAAa");
            System.out.println(session);
            // Set the username attribute in the session
            session.setAttribute("username", username);
            System.out.println("Session ID: " + session.getId());
            System.out.println("Username attribute: " + session.getAttribute("username"));


        } else {
            jsonResponse.addProperty("status", "failure");
            jsonResponse.addProperty("message", "Invalid login credentials");
        }
        // Set the response content type
        response.setContentType("application/json");

        // Write the JSON response to the client
        response.getWriter().write(jsonResponse.toString());
    }
  

    private boolean validateCredentials(String username, String password) {
      // another class
    	Account account = new Account(username, password);
    	DataAccountsManager dataManager = new DataAccountsManager();
        try {
        	System.out.print(account);
        	System.out.print("\nnn");

            // Use the DataAccountsManager to validate the account
            return dataManager.ValidateAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
		
    }
}