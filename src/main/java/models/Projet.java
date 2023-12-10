package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.User;
public class Projet {
	private int id ;
    private String projectName;
    private Date dateBegin;
    private Date dateLivraison;
    private int  duration;
    private String description;
    private String client;
    private User manager;
    private ArrayList<User> equipe;
    private ArrayList<Methodologie> methodologies;
    private ArrayList<Technologie> technologies;
    private ArrayList<Services> services;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Projet(String Id,String projectName, Date dateBegin, Date dateLivraison, int duration, String description,
			String client,User manager ,ArrayList<User> equipe, ArrayList<Methodologie> methodologies,
			ArrayList<Technologie> technologies, ArrayList<Services> services) {
		super();
		this.id=Integer.parseInt(Id);
		this.projectName = projectName;
		this.dateBegin = dateBegin;
		this.dateLivraison = dateLivraison;
		this.duration = duration;
		this.description = description;
		this.client = client;
		this.manager = manager;
		this.equipe = equipe;
		this.methodologies = methodologies;
		this.technologies = technologies;
		this.services = services;
	}
	public Projet(String projectName, Date dateBegin, Date dateLivraison,String description,
			String client,User manager) {
		super();
		this.projectName = projectName;
		this.dateBegin = dateBegin;
		this.dateLivraison = dateLivraison;
		
		this.duration =calculateDuration(dateLivraison,dateBegin) ;
		this.description = description;
		this.client = client;
		this.manager = manager;
	}
	 private int calculateDuration(Date startDate, Date endDate) {
	        // Calculate the duration between two dates in days
	        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.DAYS);
	    }
	public Projet() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Project Name: ").append(projectName).append("\n");
        stringBuilder.append("Start Date: ").append(formatDate(dateBegin)).append("\n");
        stringBuilder.append("Delivery Date: ").append(formatDate(dateLivraison)).append("\n");
        stringBuilder.append("Duration: ").append(duration).append(" days\n");
        stringBuilder.append("Description: ").append(description).append("\n");
        stringBuilder.append("Client: ").append(client).append("\n");

        if (equipe != null && !equipe.isEmpty()) {
            stringBuilder.append("Team Members:\n");
            for (User user : equipe) {
                stringBuilder.append("  - ").append(user.getUsername()).append("\n");
            }
        }

        if (methodologies != null && !methodologies.isEmpty()) {
            stringBuilder.append("Methodologies:\n");
            for (Methodologie methodology : methodologies) {
                stringBuilder.append("  - ").append(methodology.toString()).append("\n");
            }
        }

        if (technologies != null && !technologies.isEmpty()) {
            stringBuilder.append("Technologies:\n");
            for (Technologie technologie : technologies) {
                stringBuilder.append("  - ").append(technologie.toString()).append("\n");
            }
        }

        if (services != null && !services.isEmpty()) {
            stringBuilder.append("Services:\n");
            for (Services service : services) {
                stringBuilder.append("  - ").append(service.getDescription()).append("\n");
            }
        }

        return stringBuilder.toString();
    }
   
    public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	private String formatDate(Date date) {
        if (date == null) {
            return "N/A";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
        return sdf.format(date);
    }

	public String getProjectName() {
		return projectName;
	}

	public String getDateBegin() {
		return formatDate(dateBegin);
	}

	public String getDateLivraison() {
		return formatDate(dateLivraison);
	}

	public int getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public String getClient() {
		return client;
	}

	public ArrayList<User> getEquipe() {
		return equipe;
	}

	public ArrayList<Methodologie> getMethodologies() {
		return methodologies;
	}

	public ArrayList<Technologie> getTechnologies() {
		return technologies;
	}

	public ArrayList<Services> getServices() {
		return services;
	}
    public double calculateProgress() {
        if (services == null || services.isEmpty()) {
            return 0.0; // No services, consider progress as 0%
        }

        int totalTasks = 0;
        int completedTasks = 0;

        for (Services service : services) {
            List<Tache> tasks = service.getTaches();
            if (tasks != null) {
                totalTasks += tasks.size();
                for (Tache task : tasks) {
                    if (task.isDone()) {
                        completedTasks++;
                    }
                }
            }
        }

        if (totalTasks == 0) {
            return 0.0; // No tasks, consider progress as 0%
        }

        return (double) completedTasks / totalTasks * 100.0;
    }
}
