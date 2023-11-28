package Authentification;

import java.io.IOException;
import java.util.Enumeration;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/check-login")
public class CheckLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logExistingSessions(request);
        HttpSession session = request.getSession(false);
       
        System.out.println("is i ");
        
        if (session != null && session.getAttribute("username") != null) {
        	System.out.println("Session ID: " + session.getId());
            System.out.println("Username attribute: " + session.getAttribute("username"));
            System.out.println("true ");
            response.getWriter().write("true");
        } else {
            response.getWriter().write("false");
        }
    }

    private void logExistingSessions(HttpServletRequest request) {
        Enumeration<String> sessionNames = request.getSession().getAttributeNames();
        while (sessionNames.hasMoreElements()) {
            String sessionName = sessionNames.nextElement();
            System.out.println("Session attribute: " + sessionName);
        }
    }

    }

