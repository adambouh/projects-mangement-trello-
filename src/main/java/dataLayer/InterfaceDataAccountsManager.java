package dataLayer;

import java.util.ArrayList;
import java.util.Hashtable;

import models.Methodologie;
import models.Projet;
import models.User;

public interface InterfaceDataAccountsManager {
	public void AddAccount(User p);
	public boolean ValidateAccount(User p);
	public Hashtable<String, String> GetAccounts();
	boolean IsUser(String username);
	ArrayList<User> getUsers();
	boolean isDirecteur(String username);

	User getUserByUsername(String username);
	boolean isChefProjet(String username, Projet p);
	ArrayList<User> getDevs();
	ArrayList<Methodologie> getDeveloperMethodology(int id);
	
}
