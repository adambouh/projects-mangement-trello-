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
        User user = null;
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users WHERE UserID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
            
                String username = resultSet.getString("Username");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String profilePic = resultSet.getString("ProfilePic");

                user = new User(Integer.parseInt(id),username, firstname,lastname, password, role, email, profilePic);
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
    

    public static ArrayList<Methodologie> getDeveloperMethodology(int developerID) {
        ArrayList<Methodologie> methodologies = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM DeveloperMethodologies WHERE DeveloperID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, developerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String methodologyID = resultSet.getString("MethodologyID");
                methodologies.add(getMethodologieById(methodologyID));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching developer methodologies");
        }

        return methodologies;
    }
    
    public static ArrayList<Technologie> getDeveloperTechnology(int developerID) {
        ArrayList<Technologie> technologies = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM DeveloperTechnologies WHERE DeveloperID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, developerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String technologyID = resultSet.getString("TechnologyID");
                technologies.add(getTechnologieById(technologyID));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching developer technologies");
        }

        return technologies;
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
                Projet project = new Projet(projectID,projectName, startDate, deliveryDate, duration, description, client,manager ,team, methodologies, technologies, services);
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
            Connection conn = getConnection();
            String teamQuery = "SELECT DeveloperID FROM ProjectDevelopers WHERE ProjectID = ?";
            PreparedStatement teamStatement = conn.prepareStatement(teamQuery);
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

    public static void createProject(Projet p) {
        String insertQuery = "INSERT INTO Projects (ProjectName, Description, Client, StartDate, DeliveryDate, DevelopmentDays, ProjectManagerID) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, p.getProjectName());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.setString(3, p.getClient());
            preparedStatement.setString(4,parce(p.getDateBegin()));
            preparedStatement.setString(5,parce(p.getDateLivraison()));
            preparedStatement.setLong(6, p.getDuration());
            // Assuming DatePresentation is a property in your Projet class
            preparedStatement.setInt(7, p.getManager().getId()); // Assuming getUserID() returns the manager's ID

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., log them)
            e.printStackTrace();
        
    }
    }
    public static String  parce(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM dd, yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = inputFormat.parse(dateString);
            String formattedDate = outputFormat.format(date);
            	return    formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null ;
    }
    public static void updateUser(User user) {

        String updateQuery = "UPDATE Users SET lastname = ?, firstname = ?, Email = ? WHERE UserID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, user.getNom());
            preparedStatement.setString(2, user.getPrenom());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());

            // Execute the update
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., log them)
            e.printStackTrace();
        }
    }
    
    public static int getTechnologyId(String technologyName) {
        String query = "SELECT TechnologyID FROM technologies WHERE TechnologyName = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, technologyName);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("TechnologyID");
            } else {
                return -1; // Retourne -1 si la technologie n'est pas trouvée
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // ou jetez une exception personnalisée
        }
    }

    public static int addTechnology(String technologyName) {
        String query = "INSERT INTO technologies (TechnologyName) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, technologyName);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating technology failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating technology failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw a custom exception
        }
    }
    
    public static void addDeveloperTechnology(int userId, String technologyName) {
        int technologyId = getTechnologyId(technologyName);

        if (technologyId == -1) {
            // La technologie n'existe pas encore, ajoutons-la
            technologyId = addTechnology(technologyName);
        }

        String query = "INSERT INTO developertechnologies (DeveloperID, TechnologyID) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, technologyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception appropriately
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public static int getMethodologyId(String methodologyName) {
        String query = "SELECT MethodologyID FROM methodologies WHERE MethodologyName = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, methodologyName);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("MethodologyID");
            } else {
                return -1; // Retourne -1 si la méthodologie n'est pas trouvée
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // ou jetez une exception personnalisée
        }
    }
    public static int addMethodology(String methodologyName) {
        String query = "INSERT INTO methodologies (MethodologyName) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, methodologyName);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating methodology failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating methodology failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // or throw a custom exception
        }
    }
    
    public static void addDeveloperMethodology(int userId, String methodologyName) {
        int methodologyId = getMethodologyId(methodologyName);

        if (methodologyId == -1) {
            // La méthodologie n'existe pas encore, ajoutons-la
            methodologyId = addMethodology(methodologyName);
        }

        String query = "INSERT INTO developermethodologies (DeveloperID, MethodologyID) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, methodologyId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
            // Handle exception appropriately
        }}

	public static Projet getProjectbyId(int id) {
		 try {
		Connection conn = getConnection();
        String query = "SELECT * FROM projects where projectId ="+id+";";
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
            return new Projet(projectID,projectName, startDate, deliveryDate, duration, description, client,manager ,team, methodologies, technologies, services);
           
        }

	
    }
    catch (SQLException | ParseException e) {
        e.printStackTrace();
        throw new RuntimeException("Error fetching projects");
    }
		return null;
    }
}
