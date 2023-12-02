package models;

import java.util.ArrayList;

public class Developpeur extends User {
    private ArrayList<Methodologie> methodologies;
    private ArrayList<Technologie> technologies;
    private ArrayList<Tache> taches;

    public Developpeur(String username, String nom, String prenom, String password) {
        super(username, password);
        this.methodologies = new ArrayList<>();
        this.technologies = new ArrayList<>();
        this.taches = new ArrayList<>();
    }

    public Developpeur(String username, String password) {
        super(username, password);
        this.methodologies = new ArrayList<>();
        this.technologies = new ArrayList<>();
        this.taches = new ArrayList<>();
    }

    public void addMethodologie(Methodologie methodologie) {
        methodologies.add(methodologie);
    }

    public void addTechnologie(Technologie technologie) {
        technologies.add(technologie);
    }

    public void addTache(Tache tache) {
        taches.add(tache);
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

    
}
