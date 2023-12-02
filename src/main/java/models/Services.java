package models;

import java.util.ArrayList;

public class Services {
    private String name;
    private String description;
    private int duree;
    private ArrayList<Tache> taches;

    public Services(String name, String description, int duree, ArrayList<Tache> taches) {
        this.name = name;
        this.description = description;
        this.duree = duree;
        this.taches = taches;
    }

    public Services() {
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
}
