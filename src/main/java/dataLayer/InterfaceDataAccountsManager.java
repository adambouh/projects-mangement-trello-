package dataLayer;

import java.util.Hashtable;

import models.User;

public interface InterfaceDataAccountsManager {
	public void AddAccount(User p);
	public boolean ValidateAccount(User p);
	public Hashtable<String, String> GetAccounts();
	boolean IsUser(String username);
	
}
