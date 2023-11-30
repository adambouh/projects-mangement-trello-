package models;
import models.Projet;


public class Directeur extends User {

	public Directeur(String username, String nom, String prenom, String password) {
		super(username , password);
		// TODO Auto-generated constructor stub
	}

	public Directeur(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	public Void creerProjet(){return null; 
	}
	public void modifierProjet(Projet p) {
		
	}
	public boolean supprimerProjet(Projet p) {
		return true;
	}
	
	

}
