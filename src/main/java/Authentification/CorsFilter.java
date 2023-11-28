package Authentification;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;

        // Allow specific origin
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

        // Allow specific HTTP methods
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

        // Allow specific headers
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Allow credentials
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Set the maximum age for this CORS request
        response.setHeader("Access-Control-Max-Age", "3600");

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization code, if needed
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}