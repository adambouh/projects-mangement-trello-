package businessLayer;

import java.util.Hashtable;

import models.User;

public interface InterfaceAccountsManager {
	public void AddAccount(User p);
	public void AddAccount(String userName, String password);
	public Boolean ValidateAccount(String userName, String password);
	public Boolean ValidateAccount(User p);
	public Hashtable<String, String> GetAccounts();
}
