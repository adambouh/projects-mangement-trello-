package models;
public class Tache{
	private User dev;
    private String Name;
    private boolean done;
    
	public Tache( User dev,String name,boolean done) {
		super();
		this.dev= dev;
		this.Name= name;
		this.done=done;
	}



    
}