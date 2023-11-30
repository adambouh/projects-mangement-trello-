package models;
import java.util.ArrayList;

import models.Methodologie;
import models.Technologie;
import models.Tache;



public class Developpeur extends User {
    private ArrayList<Methodologie> methodologies;
    private ArrayList<Technologie> technologies;
    private ArrayList<Tache> taches;
    public Developpeur(String username, String nom, String prenom, String password) {
       super(username, password);
       this.methodologies = new ArrayList<Methodologie>();
       this.technologies = new ArrayList<Technologie>();
       this.taches = new ArrayList<Tache>();
    }

    public Developpeur(String username, String password) {
        super(username, password);
    }
    
    public void addMethodologie(Methodologie methologie) {
        methodologies.add(methologie);
    }
    public void addTechnologie(Technologie technologie) {
       technologies.add(technologie);
    }
    public void addTache(Tache tache) {
        taches.add(tache);
     }
    
}



