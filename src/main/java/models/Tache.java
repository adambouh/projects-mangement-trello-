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

	public User getDev() {
		return dev;
	}

	public void setDev(User dev) {
		this.dev = dev;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}



    
}