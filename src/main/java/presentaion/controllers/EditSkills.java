package presentaion.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.User;

import java.io.IOException;

import businessLayer.AccountsManager;

/**
 * Servlet implementation class EditSkills
 */
public class EditSkills extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSkills() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountsManager accountManager = new AccountsManager();

        // Obtenez l'utilisateur actuel � partir de la session
        String username = (String) session.getAttribute("username");
        User user = accountManager.getUserByUsername(username);

        // R�cup�rez les nouvelles comp�tences depuis les param�tres du formulaire
        String newTechnology = request.getParameter("newTechnology");
        String newMethodology = request.getParameter("newMethodology");

        // Traitez les nouvelles comp�tences et ajoutez-les � la base de donn�es
        if (newTechnology != null && !newTechnology.isEmpty()) {
            // Ajoutez la nouvelle technologie
            accountManager.addTechnology(newTechnology);
            // Ajoutez la relation utilisateur-technologie
            accountManager.addDeveloperTechnology(user.getId(), newTechnology);
        }

        if (newMethodology != null && !newMethodology.isEmpty()) {
            // Ajoutez la nouvelle m�thodologie
            accountManager.addMethodology(newMethodology);
            // Ajoutez la relation utilisateur-m�thodologie
            accountManager.addDeveloperMethodology(user.getId(), newMethodology);
        }

        // Redirigez l'utilisateur vers la page de profil apr�s la mise � jour des comp�tences
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resource/Vue/profil.jsp");
	    dispatcher.forward(request, response);
    }

	

    
}
