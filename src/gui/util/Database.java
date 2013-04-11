package gui.util;

import gui.util.managers.AccountManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Database {
	private static Database _instance = new Database();
	
	Connection con = null;	
	
	public Connection getConnection() {
		return con;
	}
	
	public final static Database getInstance() {
		return _instance;
	}
	
	private Database() {
		try {
			System.out.println("Connecting to database ...");	
			con = DriverManager.getConnection(
		    "jdbc:mysql://instance43313.db.xeround.com:8782/DANDD?" +
		    "user=DungeonMaster&password=DANDDDM9!");
			System.out.println("Connected to database successfull");
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	public void queryAccountsAndSave() {
		ResultSet results = executeQuery("SELECT USERNAME, PASSWORD FROM ACCOUNT");
		try {
			while(results.next()) {
				String username = results.getString("USERNAME");
				String password = results.getString("PASSWORD");
				AccountManager.getInstance().getLoginsMap().put(username, password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(AccountManager.getInstance().getLoginsMap().size() + " account logins loaded.");
	}
	
	public ResultSet getAccountsCharacters(String username) {
		return executeQuery("SELECT * FROM DND_CHARACTER WHERE ACCOUNT_SEQ_ID = (SELECT ACCOUNT_SEQ_ID FROM ACCOUNT WHERE USERNAME =\'"+username+"\')");
	}
	
	public List<String> getAllAccountsCharactersNames() {
		ResultSet results = executeQuery("SELECT NAME FROM DND_CHARACTER");
		List<String> names = new ArrayList<String>();
		try {
			while(results.next()) {
				String name = results.getString("NAME");
				names.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}
	
	public void createNewCharacter(String username, String name, String dnd_class, String dnd_race) {
		executeUpdate("INSERT INTO DND_CHARACTER SET NAME=\'"+name+"\', CLASS_TYPE=\'"+dnd_class+"\', RACE_TYPE=\'"+dnd_race+"\'," +
				"ACCOUNT_SEQ_ID = (SELECT ACCOUNT_SEQ_ID FROM ACCOUNT WHERE USERNAME =\'"+username+"\')");
	}
	
	public int executeUpdate(String update) {
		try {
			return con.createStatement().executeUpdate(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet executeQuery(String query) {
		try {
			return con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
