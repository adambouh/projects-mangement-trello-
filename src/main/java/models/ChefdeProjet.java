package models;

import java.util.ArrayList;

public class ChefdeProjet extends User {
	private ArrayList<Projet> projets;

	public ChefdeProjet(String username, String password, ArrayList<Projet> projets) {
		super(username, password);
		this.projets = projets;
	}

	public ChefdeProjet(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Projet> getProjets() {
		return projets;
	}

	public void setProjets(ArrayList<Projet> projets) {
		this.projets = projets;
	}

	
	
	

}
