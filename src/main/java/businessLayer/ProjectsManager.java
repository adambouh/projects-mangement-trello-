package businessLayer;

import java.util.ArrayList;
import java.util.Hashtable;

import dataLayer.DataProjectsManager;
import models.Projet;

public class ProjectsManager implements InterfaceProjectsManager  {
	public ProjectsManager(){
		
	}
	
	DataProjectsManager db=new 	DataProjectsManager();
	@Override
	public void AddProject(Projet p) {
		// TODO Auto-generated method stub
		db.AddProject(p);
	}
	@Override
	public  ArrayList<Projet> getProjects(String username){
		return db.getProjects(username);
	}
	@Override
	public   ArrayList<Projet> GetProjects(){
		return db.GetProjects();
	}
	@Override
	public  void  create(Projet p){
		 db.create(p);
	}
	@Override
	public  Projet  getProjectById(int id ){
		return db.getProjectbyId(id);}
	

	

}
