package dataLayer;

import java.util.Hashtable;

import models.User;

public interface InterfaceDataAccountsManager {
	public void AddAccount(User p);
	public Boolean ValidateAccount(User p);
	public Hashtable<String, String> GetAccounts();
	Boolean IsUser(String username);
	boolean isDirecteur(String username);
	boolean isChefDeProjet(String username);
}
