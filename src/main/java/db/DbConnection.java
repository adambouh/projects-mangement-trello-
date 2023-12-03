package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;

import models.*;


public class DbConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ProjectManagementDB?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    // Private constructor to prevent instantiation
    private DbConnection() {
    }

    // Method to get a database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database");
            }
        }
        return connection;
    }
    public static ResultSet getUSers() {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultset= preparedStatement.executeQuery();
            return resultset ;
        } catch (SQLException e) {
        	 e.printStackTrace();
        }
    }
    public static User getUserById(String id) {
        User user = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users WHERE UserID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String username = resultSet.getString("Username");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String profilePic = resultSet.getString("ProfilePic");

                user = new User(username, nom, prenom, password, role, email, profilePic);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public static Technologie getTechnologieById(String id) {
        Technologie technologie = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Technologies WHERE TechnologyID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String technologieName = resultSet.getString("TechnologyName");
                technologie = new Technologie(technologieName);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return technologie;
    }

    public static Methodologie getMethodologieById(String id) {
        Methodologie methodologie = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Methodologies WHERE MethodologyID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String methodologieName = resultSet.getString("MethodologyName");
                methodologie = new Methodologie(methodologieName);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return methodologie;
    }

    // Method to close the database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null; // Set connection to null after closing
            }
        }
    }
    public static Services getServiceById(int id) {
        Services service = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Services WHERE ServiceID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int duree = resultSet.getInt("duree");
                ArrayList<Tache> taches = getTachesForService(id); // You need to implement this function

                service = new Services(name, description, duree, taches);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    // You also need to implement a function to get Taches for a given Service ID
    private static ArrayList<Tache> getTachesForService(int serviceId) {
        ArrayList<Tache> taches = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Tasks WHERE ServiceID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Extract task details and create Tache objects
                int developerId = resultSet.getInt("DeveloperID");
                String description = resultSet.getString("Description");
                boolean done = resultSet.getBoolean("Done");

                // Create Tache object and add to the list
                Tache tache = new Tache(developerId, description, done);
                taches.add(tache);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taches;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> projectsList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM projects";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String projectID = resultSet.getString("ProjectID");
                String projectName = resultSet.getString("ProjectName");
                String description = resultSet.getString("Description");
                String client = resultSet.getString("Client");
                String startDate = resultSet.getString("StartDate");
                String deliveryDate = resultSet.getString("DeliveryDate");
                String projectManagerID = resultSet.getString("ProjectManagerID");

                // Fetch the project manager
                User manager = getUserById(projectManagerID);

                // Fetch the team members
                ArrayList<User> team = new ArrayList<>();
                String teamQuery = "SELECT UserID FROM projectdevelopers WHERE ProjectID = ?";
                PreparedStatement teamStatement = conn.prepareStatement(teamQuery);
                teamStatement.setString(1, projectID);
                ResultSet teamResultSet = teamStatement.executeQuery();

                while (teamResultSet.next()) {
                    team.add(getUserById(teamResultSet.getString("UserID")));
                }

                teamResultSet.close();
                teamStatement.close();

                // Fetch project methodologies
                ArrayList<Methodologie> methodologies = new ArrayList<>();
                String methodologyQuery = "SELECT * FROM projectmethodologies WHERE ProjectID = ?";
                PreparedStatement methodologyStatement = conn.prepareStatement(methodologyQuery);
                methodologyStatement.setString(1, projectID);
                ResultSet methodologyResultSet = methodologyStatement.executeQuery();

                while (methodologyResultSet.next()) {
                    methodologies.add(getMethodologieById(methodologyResultSet.getString("MethodologyID")));
                }

                methodologyResultSet.close();
                methodologyStatement.close();

                // Fetch project technologies
                ArrayList<Technologie> technologies = new ArrayList<>();
                String technologyQuery = "SELECT * FROM projecttechnologies WHERE ProjectID = ?";
                PreparedStatement technologyStatement = conn.prepareStatement(technologyQuery);
                technologyStatement.setString(1, projectID);
                ResultSet technologyResultSet = technologyStatement.executeQuery();

                while (technologyResultSet.next()) {
                    technologies.add(getTechnologieById(technologyResultSet.getString("TechnologyID")));
                }

                technologyResultSet.close();
                technologyStatement.close();
                // session 
                ArrayList<Service> technologies = new ArrayList<>();
                String technologyQuery = "SELECT * FROM projecttechnologies WHERE ProjectID = ?";
                PreparedStatement technologyStatement = conn.prepareStatement(technologyQuery);
                technologyStatement.setString(1, projectID);
                ResultSet technologyResultSet = technologyStatement.executeQuery();

                while (technologyResultSet.next()) {
                    technologies.add(getTechnologieById(technologyResultSet.getString("TechnologyID")));
                }

                technologyResultSet.close();
                technologyStatement.close();

                // Create a Project object and add it to the list
                Projet project = new Projet(projectID, projectName, description, client, startDate, deliveryDate, manager, team, methodologies, technologies);
                projectsList.add(project);
            }

            resultSet.close();
            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectsList;
    }

	
}
