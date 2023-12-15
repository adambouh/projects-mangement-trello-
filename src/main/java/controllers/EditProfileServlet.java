package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import models.User;
import businessLayer.*;

/**
 * Servlet implementation class EditProfileServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    AccountsManager accountsManager = new AccountsManager();
	    // Obtenez l'utilisateur actuellement connecté depuis la session
	    HttpSession session = request.getSession();
	    User currentUser = accountsManager.getUserByUsername((String)session.getAttribute("username"));
	    // Obtenez les nouvelles informations de l'utilisateur depuis le formulaire
	    String newFullName = request.getParameter("newFullName");
	    String newEmail = request.getParameter("newEmail");

	    // Mettez à jour les informations de l'utilisateur
	    currentUser.setFullName(newFullName);
	    currentUser.setEmail(newEmail);

	    // Utilisez la classe AccountsManager pour mettre à jour l'utilisateur
	    accountsManager.updateUser(currentUser);

	    // Utilisez RequestDispatcher pour transférer le contrôle à votre page JSP
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resource/Vue/profil.jsp");
	    dispatcher.forward(request, response);
	}

}
