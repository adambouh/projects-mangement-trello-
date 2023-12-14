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
	DataAccountsManager Users=new 	DataAccountsManager();
	ArrayList<Projet> table = new ArrayList<Projet>();
	@Override
	public ArrayList<Projet> getProjects(String username) {
			if (Users.isDirecteur(username))
				return GetProjects();
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
	    // Clear the existing data
	    table.clear();
	    
	    // Get projects from the database
	    table = DbConnection.getProjects();

	    // Print or display the projects
	    for (Projet projet : table) {
	        System.out.println("Project ID: " + projet);
	    }
	    return table;
	}

	@Override
	public void AddProject(Projet p) {
		
	}
	@Override
	public void  create(Projet p) {
		DbConnection.createProject(p);
	}

}
