package gui.util.managers;

import gui.util.Database;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kryonet.client.DnDClient;

public class AccountManager {
	private static AccountManager _instance = new AccountManager();
	
	private Map<String, String> loginsMap;
	
	private String errorMessage;
	
	public final static AccountManager getInstance() {
		return _instance;
	}
	
	private AccountManager() {
		loginsMap = new HashMap<String, String>();
		if(DnDClient.testing)
			loginsMap.put("xabbu", "xabbu");
		errorMessage = "";
	}
	
	public ResultSet getAccountCharacters(String username) {
		return Database.getInstance().getAccountsCharacters(username);
	}
	
	public boolean validateLogin(String username, String password) {
		if(!loginsMap.containsKey(username)) {
			errorMessage = "Username Does Not Exist";
			return false;
		}
		if(!loginsMap.get(username).equals(password)) {
			errorMessage = "Password Does Not Match";
			return false;
		}
		return true;
	}
	
	public boolean validateNewAccount(String username, String password, String email) {
		// Check valid input
		if(username.equals("") | password.equals("") | email.equals("")) {
			errorMessage = "All Fields Must Be Filled";
			return false;
		}
		
		if(loginsMap.containsKey(username)) {
			errorMessage = "Username Already Exists";
			return false;
		}
		
		if(!DnDClient.testing) {
//			if(Database.getInstance().getConnection() != null) {
				Database.getInstance().executeUpdate("INSERT INTO ACCOUNT SET USERNAME=\'"+username+"\', PASSWORD=\'"+password+"\', EMAIL=\'"+email+"\'");
//			}	
		}
		// Insert record into table
		loginsMap.put(username, password);
		return true;
	}
	
	public boolean validateNewCharacter(String username, String name, String dnd_class, String dnd_race) {
		if(Database.getInstance().getAllAccountsCharactersNames().contains(name)) {
			errorMessage = "Character Name Already Exists";
			return false;
		} else {
			Database.getInstance().createNewCharacter(username, name, dnd_class, dnd_race);
		}
		return true;
	}
	
	public String getErrorMessage() {
		String temp = errorMessage;
		errorMessage = "";
		return temp;
	}
	
	public Map<String, String> getLoginsMap() {
		return loginsMap;
	}
}
