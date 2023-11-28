package dataLayer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import models.Account;
import db.DbConnection;
public class DataAccountsManager implements InterfaceDataAccountsManager {
	static Hashtable<String, String> table=new Hashtable<String, String>();
	@Override
	public void AddAccount(Account p) {
	    System.out.println("Adding account: " + p.getUserName() + ", " + p.getPassword());
	    if (p.getUserName() != null && p.getPassword() != null) {
	        table.put(p.getUserName(), p.getPassword());
	    } else {
	        // Handle the case where username or password is null
	        System.out.println("Username or password is null");
	    }
	}

	@Override
	public Boolean ValidateAccount(Account p) {
	    GetAccounts();
	    System.out.println(table);
	    if (p.getUserName() != null && table.containsKey(p.getUserName())) {
	        String pwd = table.get(p.getUserName());
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

	            Account account = new Account(username, password, role);
	            AddAccount(account);
	        }
	        return table;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return table;
	}

}
