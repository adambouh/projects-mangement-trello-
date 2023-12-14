package models;

public class User {
	
	private String Username;
	private int Id;
	private String nom;
	private String prenom;
	private String password;
	private String role;
	private String email;
	private String ProfilePic;

	public User(String username, String password) {
		super();
		this.Username = username;
		this.password = password;
	}
	
	
	public User(String userID, String nom, String prenom, String password, String role, String email,
			String profilePic) {
	
		this.Username = userID;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
		this.email = email;
		this.ProfilePic= profilePic;
	}
	
	
	public User(int id, String username, String nom, String prenom, String password, String email, String profilePic) {
		super();
		Id = id;
		this.Username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.email = email;
		ProfilePic = profilePic;
	}


	public User(int Id,String username, String nom, String prenom, String password, String role, String email,
			String profilePic) {
		this.Id = Id;
		this.Username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.role = role;
		this.email = email;
		this.ProfilePic= profilePic;
	}
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePic() {
		return ProfilePic;
	}

	public void setProfilePic(String profilePic) {
		ProfilePic = profilePic;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;	
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
	
	 return prenom + " " + nom;
	}
	

}
