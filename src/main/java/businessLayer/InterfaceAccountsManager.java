package businessLayer;

import java.util.Hashtable;

import models.Projet;
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
}
