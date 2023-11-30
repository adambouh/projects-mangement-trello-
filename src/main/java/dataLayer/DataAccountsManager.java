package dataLayer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import models.Account;
import models.User;
import db.DbConnection;
public class DataAccountsManager implements InterfaceDataAccountsManager {
	static Hashtable<String, String> table=new Hashtable<String, String>();
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
	public Boolean ValidateAccount(User p) {
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
	public Hashtable<String, String> GetAccounts() {
	    table.clear(); // Clear the existing data
	    try {
	        ResultSet resultSet = DbConnection.getUSers();
	        while (resultSet.next()) {
	            String username = resultSet.getString("username");
	            String password = resultSet.getString("password");
	            String role = resultSet.getString("role");

	            User user = new User(username, password, role);
	            AddAccount(user);
	        }
	        return table;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return table;
	}

}
