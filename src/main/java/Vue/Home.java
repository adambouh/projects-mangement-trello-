package Vue;
import controllers.Authenification;
import java.io.IOException;

import businessLayer.ProjectsManager;
import dataLayer.DataAccountsManager;
import jakarta.servlet.RequestDispatcher;
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


           RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resource/Vue/home.jsp");
           ProjectsManager projectsManager = new ProjectsManager();
           projectsManager.GetProjects();
           dispatcher.forward(request, response);
           
        }
   
	}
	
