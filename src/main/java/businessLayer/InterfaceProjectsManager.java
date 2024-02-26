package businessLayer;

import java.util.ArrayList;

import models.Projet;

public interface InterfaceProjectsManager {


	void AddProject(Projet p);

	ArrayList<Projet> getProjects(String username);

	ArrayList<Projet> GetProjects();

	void create(Projet p);

	Projet getProjectById(int id);
}
