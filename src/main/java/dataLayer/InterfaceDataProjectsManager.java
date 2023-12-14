package dataLayer;

import java.util.ArrayList;

import models.Projet;

public interface InterfaceDataProjectsManager {

	ArrayList<Projet> getProjects(String username);
    public ArrayList<Projet> GetProjects();
    public ArrayList<Projet> GetProjectsChef();
    public ArrayList<Projet> GetProjectsDev();
    public void AddProject(Projet p) ;
	void create(Projet p);
}
