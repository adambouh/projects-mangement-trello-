package models;

public class Tache {
	private String name; 
	private String description;
	private int duree;
	private boolean done;

	public Tache(String name, String description, int duree,boolean done) {
		super();
		this.name = name;
		this.description = description;
		this.duree = duree;
		this.done=false;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Tache() {
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
	

}
