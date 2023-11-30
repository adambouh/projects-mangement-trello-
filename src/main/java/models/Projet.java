package models;

import java.util.ArrayList;
import java.util.Date;
import models.Developpeur;
import models.Tache;


public class Projet {
   private String name;
   private Date dateBegin;
   private Date dateLivraison;
   private Date datePresentationEquipe;
   private String description;
   private ArrayList<Developpeur> equipe;
   private ArrayList<Methodologie> methodologies;
   private ArrayList<Technologie> technologies;
   private ArrayList<Tache> taches;
   

	public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public Date getDateBegin() {
	return dateBegin;
}


public void setDateBegin(Date dateBegin) {
	this.dateBegin = dateBegin;
}


public Date getDateLivraison() {
	return dateLivraison;
}


public void setDateLivraison(Date dateLivraison) {
	this.dateLivraison = dateLivraison;
}


public Date getDatePresentationEquipe() {
	return datePresentationEquipe;
}


public void setDatePresentationEquipe(Date datePresentationEquipe) {
	this.datePresentationEquipe = datePresentationEquipe;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public ArrayList<Developpeur> getEquipe() {
	return equipe;
}


public void setEquipe(ArrayList<Developpeur> equipe) {
	this.equipe = equipe;
}


public ArrayList<Methodologie> getMethodologies() {
	return methodologies;
}


public void setMethodologies(ArrayList<Methodologie> methodologies) {
	this.methodologies = methodologies;
}


public ArrayList<Technologie> getTechnologies() {
	return technologies;
}


public void setTechnologies(ArrayList<Technologie> technologies) {
	this.technologies = technologies;
}


public ArrayList<Tache> getTaches() {
	return taches;
}


public void setTaches(ArrayList<Tache> taches) {
	this.taches = taches;
}


	public Projet(String name, Date dateBegin, Date dateLivraison, Date datePresentationEquipe, String description,
		ArrayList<Developpeur> equipe, ArrayList<Methodologie> methodologies, ArrayList<Technologie> technologies,
		ArrayList<Tache> taches) {
	super();
	this.name = name;
	this.dateBegin = dateBegin;
	this.dateLivraison = dateLivraison;
	this.datePresentationEquipe = datePresentationEquipe;
	this.description = description;
	this.equipe = new ArrayList<Developpeur>();
	this.methodologies = new ArrayList<Methodologie>();
    this.technologies = new ArrayList<Technologie>();
	this.taches = new ArrayList<Tache>();
}


	public Projet() {
		// TODO Auto-generated constructor stub
	}

}
