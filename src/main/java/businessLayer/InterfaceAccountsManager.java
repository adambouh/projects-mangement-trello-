package businessLayer;

import java.util.ArrayList;
import java.util.Hashtable;

import models.Methodologie;
import models.Projet;
import models.Technologie;
import models.User;

public interface InterfaceAccountsManager {
	public void AddAccount(User p);
	public void AddAccount(String userName, String password);
	public Boolean ValidateAccount(String userName, String password);
	public Boolean ValidateAccount(User p);
	public Hashtable<String, String> GetAccounts();
	boolean isChefDeProjet(String username, Projet p);
	boolean isDirecteur(String username);
	boolean isDeveloppeur(String username);
	User getUserbyUsername(String username);
	ArrayList<User> getDevs();
	ArrayList<Methodologie> getDeveloperMethodology(int Id);
	ArrayList<Technologie> getDeveloperTechnology(int Id);
}
