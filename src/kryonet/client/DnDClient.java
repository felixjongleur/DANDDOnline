 package kryonet.client;

import gui.GUIState;
import gui.menu.CreateCharacterState;
import gui.menu.LoadingState;
import gui.menu.LoginState;
import gui.menu.MainMenuState;
import gui.menu.WorldChatState;
import gui.util.managers.ResourceManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import kryonet.DnDNetwork;
import kryonet.DnDNetwork.CreateAccount;
import kryonet.DnDNetwork.CreateCharacter;
import kryonet.DnDNetwork.DisconnectCharacter;
import kryonet.DnDNetwork.GetCharacters;
import kryonet.DnDNetwork.Login;
import kryonet.DnDNetwork.LoginCharacter;
import kryonet.DnDNetwork.Message;
import kryonet.DnDNetwork.Register;
import mdes.slick.sui.skin.simple.SimpleSkin;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class DnDClient extends StateBasedGame {
	
	public static boolean testing = false;
	
	public static final int LOADINGSTATE	 = 0;
	public static final int LOGINSSTATE 	 = 1;
	public static final int MAINMENUSTATE  	 = 2;
	public static final int CREATECHARSTATE  = 3;
	public static final int WORLDCHATSTATE   = 4;
	public static final int GUISTATE		 = 10;
	
	public static final int SCREENWIDTH  = 1280;
	public static final int SCREENHEIGHT = 768;
	
	private static Client client;
	
	public DnDClient() throws FileNotFoundException, SlickException {
		super("DNDGame");
		this.addState(new LoadingState(LOADINGSTATE));	
		this.addState(new LoginState(LOGINSSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new CreateCharacterState(CREATECHARSTATE));
		this.addState(new WorldChatState(WORLDCHATSTATE));
		this.addState(new GUIState(GUISTATE));
		this.enterState(LOADINGSTATE);
		ResourceManager.getInstance().loadResources(new FileInputStream("src/data/resourceLoaderFile.xml"), true);
		SimpleSkin.setRoundRectanglesEnabled(true);
		
		client = new Client(8192, 8192);
		client.start();		
		DnDNetwork.register(client);		
		client.addListener(new Listener.ThreadedListener(new Listener() {
			public void connected (Connection connection) {
				client.sendTCP(new Register());
				return;
			}

			public void received (Connection c, Object object) {
				DnDClient.this.getCurrentState().receiveObject(object);
				return;
			}
		}));
		
		new Thread("Connect") {
			public void run () {
				try {
					if(testing) {
						client.connect(5000, "localhost", DnDNetwork.port);
					} else {
						client.connect(5000, "54.245.51.122", DnDNetwork.port);
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
	}

	public static void main(String[] args) throws SlickException, FileNotFoundException {
		AppGameContainer app = new AppGameContainer(new DnDClient());
		app.setShowFPS(false);
		app.setDisplayMode(SCREENWIDTH, SCREENHEIGHT, false);
		app.setAlwaysRender(true);
		app.start();
	}
	
	public static void checkLogin(String username, String password) {
		Login login = new Login();
		login.username = username;
		login.password = password;
		client.sendTCP(login);
	}
	
	public static void getCharacters(String username) {
		GetCharacters characters = new GetCharacters();
		characters.name = username;
		client.sendTCP(characters);
	}
	
	public static void sendChatMessage(String text) {
		Message message = new Message();
		message.text = text;
		client.sendTCP(message);
	}
	
	public static void loginCharacter(String name) {
		LoginCharacter character = new LoginCharacter();
		character.name = name;
		client.sendTCP(character);
	}
	
	public static void disconnectCharacter() {
		client.sendTCP(new DisconnectCharacter());
	}
	
	public static void checkCreate(String username, String password, String email) {
		CreateAccount create = new CreateAccount();
		create.username = username;
		create.password = password;
		create.email = email;
		client.sendTCP(create);
	}

	public static void checkNewCharacter(String username, String name, String dnd_class, String dnd_race) {
		CreateCharacter create = new CreateCharacter();
		create.username = username;
		create.name = name;
		create.dnd_class = dnd_class;
		create.dnd_race = dnd_race;
		client.sendTCP(create);
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {	}
}
