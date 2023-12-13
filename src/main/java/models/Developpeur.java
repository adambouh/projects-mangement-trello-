package models;

import java.util.ArrayList;

public class Developpeur extends User {
    private int Phone;
    private String About;
	private ArrayList<Methodologie> methodologies;
    private ArrayList<Technologie> technologies;
    private ArrayList<Tache> taches;

    public Developpeur(String username,String nom, String prenom, String email,int phone,String about) {
        super(username,nom, prenom,email);
        this.Phone=phone;
        this.About=about;
        this.methodologies = new ArrayList<>();
        this.technologies = new ArrayList<>();
        this.taches = new ArrayList<>();
    }
    

    public String getAbout() {
		return About;
	}


	public void setAbout(String about) {
		About = about;
	}


	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	 public String getFullName() {
	        return getNom() + " " + getPrenom();}
	 

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
