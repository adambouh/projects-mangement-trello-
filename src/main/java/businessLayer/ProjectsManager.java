package businessLayer;

import java.util.ArrayList;
import java.util.Hashtable;

import dataLayer.DataProjectsManager;
import models.Projet;

public class ProjectsManager implements InterfaceProjectsManager  {

	DataProjectsManager db=new 	DataProjectsManager();
	public void AddProject(Projet p) {
		// TODO Auto-generated method stub
		db.AddProject(p);
	}
	public  ArrayList<Projet> getProjects(String username){
		return db.getProjects(username);
	}
	public   ArrayList<Projet> GetProjects(){
		return db.GetProjects();
	}
	


	

}
