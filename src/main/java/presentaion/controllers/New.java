package presentaion.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Projet;
import models.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLayer.AccountsManager;
import businessLayer.ProjectsManager;

/**
 * Servlet implementation class New
 */
public class New extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  // Extract parameters from the request
		 AccountsManager pr =new AccountsManager();
		
		 ProjectsManager projects= new  ProjectsManager();
        String projectName = request.getParameter("projectName");
        String dateBeginStr = request.getParameter("start");
        String dateLivraisonStr = request.getParameter("end");
        String description = request.getParameter("description");
        String client = request.getParameter("client");
        String managerName = request.getParameter("teamLeader"); // Assuming managerName is a parameter for the manager's name

        // Parse date strings to Date objects
        Date dateBegin = parseDate(dateBeginStr);
        Date dateLivraison = parseDate(dateLivraisonStr);

        // Create a new User  for the manager (assuming you have a User class)
        User manager = pr.getUserbyUsername(managerName);

        // Create a new Projet object
        Projet newProject = new Projet(projectName, dateBegin, dateLivraison, description, client, manager);
        
        // Perform any additional actions or forward to another page as needed
        projects.create(newProject);
        // Example: Set the newProject as an attribute in the request and forward to a JSP page
        response.sendRedirect("Home");
	}


    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            // Handle parsing exception (e.g., log it or return null)
            e.printStackTrace();
            return null;
        }
	}

}
