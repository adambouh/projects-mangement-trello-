package db;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
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

    public static ResultSet getUsers() {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching users");
        }
    }

    public static User getUserById(String id) {
    	System.out.println(id);
        User user = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users WHERE UserID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("Username");
               
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String profilePic = resultSet.getString("ProfilePic");

                user = new User(username, "nom", "prenom", password, role, email, profilePic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching user by ID");
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

           
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching technology by ID");
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


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching methodology by ID");
        }

        return methodologie;
    }

    public static Services getServiceById(String id) {
        Services service = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Services WHERE ServiceID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int duree = resultSet.getInt("DurationInDays");
                ArrayList<Tache> taches = getTachesForService(id);

                service = new Services("name", description, duree, taches);
            }

            

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching service by ID");
        }

        return service;
    }

    private static ArrayList<Tache> getTachesForService(String serviceId) {
        ArrayList<Tache> taches = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Tasks WHERE ServiceID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, serviceId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String developerId = resultSet.getString("DeveloperID");
                String description = resultSet.getString("Description");
                boolean done = resultSet.getBoolean("Done");

                Tache tache = new Tache(getUserById(developerId), description, done);
                taches.add(tache);
            }

           

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching tasks for service");
        }

        return taches;
    }
    public static ResultSet getUserRole(String username) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT Role FROM Users WHERE Username = ?")) {

            preparedStatement.setString(1, username);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ArrayList<Projet> getProjects() {
        ArrayList<Projet> projectsList = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM projects";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (resultSet.next()) {
                String projectID = resultSet.getString("ProjectID");
                String projectName = resultSet.getString("ProjectName");
                String description = resultSet.getString("Description");
                String client = resultSet.getString("Client");
                String projectManagerID = resultSet.getString("ProjectManagerID");
                int duration = resultSet.getInt("DevelopmentDays");

                // Parse dates
                Date startDate = resultSet.getString("StartDate") != null ?
                        dateFormat.parse(resultSet.getString("StartDate")) : null;
                Date deliveryDate = resultSet.getString("DeliveryDate") != null ?
                        dateFormat.parse(resultSet.getString("DeliveryDate")) : null;

   
                // Get the project manager
                User manager = getUserById(projectManagerID);

                // Get team members, methodologies, technologies, and services
                ArrayList<User> team = getTeamMembers(projectID);
                ArrayList<Methodologie> methodologies = getProjectMethodologies(projectID);
                ArrayList<Technologie> technologies = getProjectTechnologies(projectID);
                ArrayList<Services> services = getProjectServices(projectID);

                // Create a Project object with parsed dates and duration
                Projet project = new Projet(projectName, startDate, deliveryDate, duration, description, client,manager ,team, methodologies, technologies, services);
                projectsList.add(project);
            }


        }
            catch (SQLException | ParseException e) {
                e.printStackTrace();
                throw new RuntimeException("Error fetching projects");
            }

            return projectsList;
        }

    private static ArrayList<User> getTeamMembers(String projectID) {
        ArrayList<User> team = new ArrayList<>();

        try {
        	System.out.println(projectID);
            Connection conn = getConnection();
            String teamQuery = "SELECT DeveloperID FROM ProjectDevelopers WHERE ProjectID = ?";
            PreparedStatement teamStatement = conn.prepareStatement(teamQuery);
            System.out.println(teamStatement);
            teamStatement.setString(1, projectID);
           
            ResultSet teamResultSet = teamStatement.executeQuery();

            while (teamResultSet.next()) {
                team.add(getUserById(teamResultSet.getString("DeveloperID")));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching team members");
        }

        return team;
    }

    private static ArrayList<Methodologie> getProjectMethodologies(String projectID) {
        ArrayList<Methodologie> methodologies = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String methodologyQuery = "SELECT * FROM ProjectMethodologies WHERE ProjectID = ?";
            PreparedStatement methodologyStatement = conn.prepareStatement(methodologyQuery);
            methodologyStatement.setString(1, projectID);
            ResultSet methodologyResultSet = methodologyStatement.executeQuery();

            while (methodologyResultSet.next()) {
                methodologies.add(getMethodologieById(methodologyResultSet.getString("MethodologyID")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching project methodologies");
        }

        return methodologies;
    }

    private static ArrayList<Technologie> getProjectTechnologies(String projectID) {
        ArrayList<Technologie> technologies = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String technologyQuery = "SELECT * FROM ProjectTechnologies WHERE ProjectID = ?";
            PreparedStatement technologyStatement = conn.prepareStatement(technologyQuery);
            technologyStatement.setString(1, projectID);
            ResultSet technologyResultSet = technologyStatement.executeQuery();

            while (technologyResultSet.next()) {
                technologies.add(getTechnologieById(technologyResultSet.getString("TechnologyID")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching project technologies");
        }

        return technologies;
    }

    private static ArrayList<Services> getProjectServices(String projectID) {
        ArrayList<Services> services = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String serviceQuery = "SELECT * FROM Services WHERE ProjectID = ?";
            PreparedStatement serviceStatement = conn.prepareStatement(serviceQuery);
            serviceStatement.setString(1, projectID);
            ResultSet serviceResultSet = serviceStatement.executeQuery();

            while (serviceResultSet.next()) {
                services.add(getServiceById(serviceResultSet.getString("ServiceID")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching project services");
        }

        return services;
    }
}
