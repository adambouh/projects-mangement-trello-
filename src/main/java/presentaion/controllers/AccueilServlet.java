package presentaion.controllers;

import java.io.IOException;

import businessLayer.AccountsManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * Servlet implementation class AccueilServlet
 */
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountsManager manager = new AccountsManager();

	/**
	 * Default constructor.
	 */
	public AccueilServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		if (action.equals("login")) {
			Boolean res = manager.ValidateAccount(username, password);
			if (res == false) {
				request.getRequestDispatcher("inscription.html").forward(request, response);
			} else {
				if (username.equals("admin"))
					request.getRequestDispatcher("admin.html").forward(request, response);
				else
					request.getRequestDispatcher("user.html").forward(request, response);
			}
		}
		if (action.equals("inscription")) {
			manager.AddAccount(username, password);
			request.getRequestDispatcher("index.html").forward(request, response);
		}

	}

}
