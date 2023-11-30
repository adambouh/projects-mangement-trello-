package models;

import java.util.ArrayList;

public class ChefdeProjet extends User {
	private ArrayList<Projet> projets;

	public ChefdeProjet(String username, String password, ArrayList<Projet> projets) {
		super(username, password);
		this.projets = new ArrayList<Projet>();
	}

	public ChefdeProjet(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public void modifierProjet(Projet p) {
		
	}
	
	

}
