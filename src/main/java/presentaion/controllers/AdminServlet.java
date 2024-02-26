package presentaion.controllers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import businessLayer.AccountsManager;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountsManager manager=new AccountsManager();
    /**
     * Default constructor. 
     */
    public AdminServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Hashtable<String, String> table=manager.GetAccounts();
		response.setContentType("text/html");
		response.getWriter().write("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
				+ "</head> \r\n"
				+ "<body>\r\n"
				+"<h5>Hello admin</h5>\r\n"
				+ "<div class='bg-light'>");
		
		Enumeration<String> e=table.keys();
		while(e.hasMoreElements()) {
			String username=e.nextElement();
			String pwd=table.get(username);
			response.getWriter().write("<div>"+username+" "+pwd+"<a href='#'>delete</a></div>");
		}
		response.getWriter().write("</div>"	+ "</body>"+ "</html>");
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
