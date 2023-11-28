package dataLayer;

import java.util.Hashtable;

import models.Account;

public interface InterfaceDataAccountsManager {

	public void AddAccount(Account p);
	public Boolean ValidateAccount(Account p);
	public Hashtable<String, String> GetAccounts();
}
