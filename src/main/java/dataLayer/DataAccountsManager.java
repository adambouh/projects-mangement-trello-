package dataLayer;

import models.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.mysql.cj.protocol.Resultset;

import models.*;
import db.DbConnection;
public class DataAccountsManager implements InterfaceDataAccountsManager {
	static Hashtable<String, String> table=new Hashtable<String, String>();
	static ArrayList<User> users=new ArrayList<>();
	@Override
	public void AddAccount(User p) {
	    System.out.println("Adding account: " + p.getUsername() + ", " + p.getPassword());
	    if (p.getUsername() != null && p.getPassword() != null) {
	        table.put(p.getUsername(), p.getPassword());
	    } else {
	        // Handle the case where username or password is null
	        System.out.println("Username or password is null");
	    }
	}
	@Override
	public boolean ValidateAccount(User p) {
	    GetAccounts();
	  //  System.out.println(table);
	    if (p.getUsername() != null && table.containsKey(p.getUsername())) {
	        String pwd = table.get(p.getUsername());
	        if (pwd.equals(p.getPassword())) {
	            return true;
	        } else {
	            return false;
	        }
	    } else {
	        return false;
	    }
	}
	 @Override
	public boolean IsUser(String username) {
	    GetAccounts();
	  //  System.out.println(table);
	    if (username != null && table.containsKey(username)) {
	       return true;
	    } else {
	        return false;
	    }
	}
	@Override 
	public boolean isDirecteur(String username) {
		    String userRole;
		    try {
		        ResultSet resultSet = DbConnection.getUserRole(username);
		        userRole = resultSet.next() ? resultSet.getString("Role") : null;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        userRole = null;
		    }
		    return "Directeur".equals(userRole);
		}
	@Override
	public boolean isChefProjet(String username,Projet p ) {
		    return getUserByUsername(username).equals(p.getManager());
		}
	@Override
	public Hashtable<String, String> GetAccounts() {
	    table.clear(); // Clear the existing data
	    try {
	        ResultSet resultSet = DbConnection.getUsers();
	        while (resultSet.next()) {
	            String username = resultSet.getString("username");
	            String password = resultSet.getString("password");

	            User user = new User(username, password);
	            AddAccount(user);
	        }
	        return table;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return table;
	} 
	@Override
	public ArrayList<User> getUsers() {
		    users.clear(); // Clear the existing data
		    try {
		        ResultSet resultSet = DbConnection.getUsers();
		        while (resultSet.next()) {
		        	int id = resultSet.getInt("UserID");
		            String username = resultSet.getString("Username");
		            String lastname = resultSet.getString("firstname");
		            String firstname = resultSet.getString("lastname");
	                String password = resultSet.getString("password");
	                String role = resultSet.getString("role");
	                String email = resultSet.getString("email");
	                String profilePic = resultSet.getString("ProfilePic");
	                User user = new User(id,username, lastname, firstname, password, role, email, profilePic);
	                users.add(user);
		        }
		        return users;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return users;
		}
	@Override
	public ArrayList<User> getDevs(){
		ArrayList<User> Devs = new ArrayList<User>();
		getUsers();
		for(User user :users) {
			if (user.getRole().equals("dev")) {
				Devs.add(user);
			}
		}
		return Devs;
	}
	@Override
	public User getUserByUsername(String username) {
		 		getUsers();
	        for (User user : users) {
	            if (user.getUsername().equals(username)) {
	                return user; // Found the user
	            }
	        }
	        return null; // User with the specified username not found
	    }
	@Override
	public ArrayList<Methodologie> getDeveloperMethodology(int id) {
		// TODO Auto-generated method stub
		return DbConnection.getDeveloperMethodology(id);
	}
	@Override
	public ArrayList<Technologie> getDeveloperTechnology(int id) {
		// TODO Auto-generated method stub
		return DbConnection.getDeveloperTechnology(id);
	}
	  public void updateUser(User user) {
	        DbConnection.updateUser(user);
	    }
	
	
	
	}
	

