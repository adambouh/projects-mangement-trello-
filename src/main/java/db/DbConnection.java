package db;

import java.sql.*;


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
    public static ResultSet getUSers() throws SQLException {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM Users";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultset= preparedStatement.executeQuery();
            return resultset ;
        } catch (SQLException e) {
            throw e;
        }
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
}
