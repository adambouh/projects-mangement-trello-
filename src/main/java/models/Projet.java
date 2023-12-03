package models;

import java.util.ArrayList;
import java.util.Date;

public class Projet {
    private String projectName;
    private Date dateBegin;
    private Date dateLivraison;
    private Date datePresentationEquipe;
    private String description;
    private String client;
    private ArrayList<Developpeur> equipe;
    private ArrayList<Methodologie> methodologies;
    private ArrayList<Technologie> technologies;
    private ArrayList<Services> services;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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

    public ArrayList<Services> getServices() {
        return services;
    }

    public void setServices(ArrayList<Services> services) {
        this.services = services;
    }



    public Projet(String projectName, Date dateBegin, Date dateLivraison, Date datePresentationEquipe,
			String description, String client, ArrayList<Developpeur> equipe, ArrayList<Methodologie> methodologies,
			ArrayList<Technologie> technologies, ArrayList<Services> services) {
		super();
		this.projectName = projectName;
		this.dateBegin = dateBegin;
		this.dateLivraison = dateLivraison;
		this.datePresentationEquipe = datePresentationEquipe;
		this.description = description;
		this.client = client;
		this.equipe = equipe;
		this.methodologies = methodologies;
		this.technologies = technologies;
		this.services = services;
	}

	public Projet() {
        // TODO Auto-generated constructor stub
    }
}
