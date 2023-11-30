package businessLayer;

import java.util.Hashtable;

import dataLayer.DataAccountsManager;
import models.User;

public class AccountsManager implements InterfaceAccountsManager  {

	DataAccountsManager db=new 	DataAccountsManager();
	@Override
	public void AddAccount(User p) {
		// TODO Auto-generated method stub
		db.AddAccount(p);
	}

	@Override
	public void AddAccount(String userName, String password) {
		// TODO Auto-generated method stub
		User x=new User(userName, password);
		AddAccount(x);
	}

	@Override
	public Boolean ValidateAccount(String userName, String password) {
		// TODO Auto-generated method stub
		User x=new User(userName, password);
		return db.ValidateAccount(x);
	}

	@Override
	public Boolean ValidateAccount(User p) {
		// TODO Auto-generated method stub
		return db.ValidateAccount(p);
	}

	@Override
	public Hashtable<String, String> GetAccounts() {
		// TODO Auto-generated method stub
		return db.GetAccounts();
	}

}
