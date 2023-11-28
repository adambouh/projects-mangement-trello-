package businessLayer;

import java.util.Hashtable;

import models.Account;

public interface InterfaceAccountsManager {
	public void AddAccount(Account p);
	public void AddAccount(String userName, String password);
	public Boolean ValidateAccount(String userName, String password);
	public Boolean ValidateAccount(Account p);
	public Hashtable<String, String> GetAccounts();
}
