package dataLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import businessLayer.AccountsManager;
import db.DbConnection;
import models.Projet;
import models.User;

public class DataProjectsManager implements InterfaceDataProjectsManager {
	DataAccountsManager db=new 	DataAccountsManager();
	ArrayList<Projet> table = new ArrayList<Projet>();
	@Override
	public ArrayList<Projet> getProjects(String username) {
			if (db.isDirecteur(username))
				return GetProjects();
			else if(db.isChef(username))
				return GetProjectsChef();
			else 
				return GetProjectsDev();

	}
	@Override
	public ArrayList<Projet> GetProjectsDev() {
		
				return null;
	}
	public ArrayList<Projet> GetProjectsChef() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Projet> GetProjects() {
		// TODO Auto-generated method stub
		   table.clear(); // Clear the existing data
		    try {
		        ResultSet resultSet =DbConnection.getProjects();
		        while (resultSet.next()) {
		        	//get attributes of project 
		            String username = resultSet.getString("username");
		            String password = resultSet.getString("password");
		            String role = resultSet.getString("role");

		            Projet p = new Projet();
		            AddProject(p);
		        }
		        return table;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return table;
		
	}
	@Override
	public void AddProject(Projet p) {
		
	}

}
