package presentaion.controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Save extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read data from the request body
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        //Received data: {"boards":[{"name":"Untitled Board","id":"b0","settings":{"theme":null},"cards":[{"name":"Service 1 for Project 2","items":[{"title":"Task 1 for Service 1 (Project 2)","description":null,"id":"e2","isDone":false,"parentCardId":"e1"},{"title":"Task 1 for Service 1 (Project 1)","description":null,"id":"e3","isDone":false,"parentCardId":"e1"},{"title":"Task 2 for Service 1 (Project 1)","description":null,"id":"e4","isDone":false,"parentCardId":"e1"},{"title":"Implement Payment Processing Logic","description":null,"id":"e5","isDone":false,"parentCardId":"e1"}],"id":"e1","parentBoardId":"b0"},{"name":"Service 2 for Project 2","items":[{"title":"Task 1 for Service 2 (Project 2)","description":null,"id":"e7","isDone":false,"parentCardId":"e6"},{"title":"Set Up Logging for the Application","description":null,"id":"e8","isDone":false,"parentCardId":"e6"}],"id":"e6","parentBoardId":"b0"},{"name":"User Interface Design","items":[],"id":"e9","parentBoardId":"b0"},{"name":"Database Optimization","items":[],"id":"e10","parentBoardId":"b0"}],"identifier":10}],"settings":{"userName":"User","dataPersistence":true},"currentBoard":0,"identifier":0}


        // Parse the JSON data
        String jsonData = requestBody.toString();
        // You may want to use a JSON library here to parse the data
        // For simplicity, let's assume it's a plain string in this example
        System.out.println("Received data: " + jsonData);

        // Your logic to process and save the data goes herechzgcf mhgc
        // ...

        // Send a response back to the client (optional)
        response.setContentType("application/json");
        response.getWriter().write("{\"status\": \"success\"}");
    }
}

