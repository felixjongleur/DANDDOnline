package gui.menu;

import gui.util.AlertMessagePopup;
import gui.util.managers.ResourceManager;
import kryonet.DnDNetwork.CreateAccount;
import kryonet.DnDNetwork.Login;
import kryonet.client.DnDClient;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.Label;
import mdes.slick.sui.Popup;
import mdes.slick.sui.Sui;
import mdes.slick.sui.TextField;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import mdes.slick.sui.event.KeyEvent;
import mdes.slick.sui.event.KeyListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class LoginState extends BasicGameState {

	private int stateID;
	
	private Container login, createNew;
	
	private int menuX = 540, menuY = 450;
	
	private TextField username, password, email;
	private Label usernameLabel, passwordLabel, emailLabel;
	private Button loginButton, createButton, submitButton, backButton;
	
	private AlertMessagePopup popup;
	
	private Image background;
	
	public static String usernameText;
	
	Display display;
	GameContainer gc;
	StateBasedGame sb;
	
	public LoginState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		username.setText("");
		password.setText("");
		email.setText("");
		
		if(!popup.hasFocus()) {
			username.grabFocus();
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {		
		this.gc = gc;
		this.sb = sb;
		gc.getGraphics().setBackground(Sui.getTheme().getBackground());
		background = ResourceManager.getInstance().getImage("MAIN_MENU_IMAGE");
		
		display = new Display(gc);						
		
		createNew = new Container();
		createNew.setBounds(menuX, menuY + 120, 200, 160);
		createNew.setVisible(false);
		display.add(createNew);
		
		login = new Container();
		login.setBounds(menuX, menuY + 120, 200, 100);
		display.add(login);
		
		createLabels();		
		createPopUps();
		createTextFields();
		createButtons();

		username.grabFocus();		
	}

	private void createPopUps() {
		popup = new AlertMessagePopup(display);
		KeyListener popupKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER) {
					popup.setVisible(false);
					username.grabFocus();
				}
			}
		};		
		popup.addKeyListener(popupKeyListener);
	}

	private void createButtons() {
		loginButton = new Button("Login");
		loginButton.setBounds(0, 20, 200, 30);
		loginButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		loginButton.setFont(LoadingState.font);
		loginButton.setForeground(Color.white);
		loginButton.setFocusColor(Color.yellow);
		login.add(loginButton);
		
        ActionListener textAction = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	DnDClient.checkLogin(username.getText(), password.getText());
            } 
        };
        
        KeyListener loginKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER) {
					loginButton.grabFocus();
					loginButton.press();
				}
				if(e.getKeyCode() == Input.KEY_TAB) 
					createButton.grabFocus();
			}
		};
         
		loginButton.addKeyListener(loginKeyListener);
        loginButton.addActionListener(textAction);
		
        createButton = new Button("Create");
        createButton.setBounds(0, 70, 200, 30);
        createButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
        createButton.setFont(LoadingState.font);
        createButton.setForeground(Color.white);
        createButton.setFocusColor(Color.yellow);
        login.add(createButton);
        
        ActionListener createAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.enterState(DnDClient.LOGINSSTATE, null, new FadeInTransition());
				createNew.setVisible(true);
				login.setVisible(false);
				username.grabFocus();
			}
		};
		
        KeyListener createKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER)
					createButton.press();
				if(e.getKeyCode() == Input.KEY_TAB) 
					username.grabFocus();
			}
		};
         
		createButton.addKeyListener(createKeyListener);
		createButton.addActionListener(createAction);
         
		Button quit = new Button("Quit");
		quit.setFont(LoadingState.font);
		quit.pack();
		quit.setForeground(Color.white);
		quit.setFocusColor(Color.yellow);
		quit.setLocation(menuX + 600, createButton.getAbsoluteY());
		quit.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		quit.packImage();
		display.add(quit);
		
		ActionListener quitAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				gc.exit();
			}
		};
		
		quit.addActionListener(quitAction);

		submitButton = new Button("Submit");
		submitButton.setBounds(0, 80, 200, 30);
		submitButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		submitButton.setFont(LoadingState.font);
		submitButton.setForeground(Color.white);
		submitButton.setFocusColor(Color.yellow);
		createNew.add(submitButton);
		
		ActionListener submitAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
            	DnDClient.checkCreate(username.getText(), password.getText(), email.getText());
			}
		};
		
        KeyListener submitKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER)
					submitButton.press();
				if(e.getKeyCode() == Input.KEY_TAB) 
					backButton.grabFocus();
			}
		};
         
		submitButton.addKeyListener(submitKeyListener);		
		submitButton.addActionListener(submitAction);
		
		backButton = new Button("Back");
		backButton.setBounds(0, 130, 200, 30);
		backButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		backButton.setFont(LoadingState.font);
		backButton.setForeground(Color.white);
		backButton.setFocusColor(Color.yellow);
		createNew.add(backButton);
		
		ActionListener backAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.enterState(DnDClient.LOGINSSTATE, null, new FadeInTransition());
				createNew.setVisible(false);
				login.setVisible(true);
				username.grabFocus();
			}
		};
		
        KeyListener backKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER)
					backButton.press();
				if(e.getKeyCode() == Input.KEY_TAB) 
					username.grabFocus();
			}
		};
         
		backButton.addKeyListener(backKeyListener);	
		backButton.addActionListener(backAction);
	}

	private void createTextFields() {
		username = new TextField(10, LoadingState.font);
		username.setBackground(new Color(0, 0, 0, 0.6f));
		username.setOpaque(true);
		username.setLocation(menuX, menuY + 30);
		username.setWidth(200);
		display.add(username);	
        
		KeyListener usernameTab = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_TAB) {
					password.grabFocus();
				}
				
				if(e.getKeyCode() == Input.KEY_ENTER) { }
			}
		};		
		
		username.addKeyListener(usernameTab);
		
		password = new TextField(10, LoadingState.font);
		password.setLocation(menuX, menuY + 90);
		password.setWidth(200);
		password.setMaskEnabled(true);
		display.add(password);
		
        ActionListener passwordEnter = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
				if(!email.isShowing()) {
					loginButton.press();
				}
            } 
        };	

		KeyListener passwordTab = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_TAB) {
					if(email.isShowing()) {
						email.grabFocus();
					} else {
						loginButton.grabFocus();
					}
				}
			}
		};
        
        password.addKeyListener(passwordTab);
        password.addActionListener(passwordEnter);
		
		email = new TextField(10, LoadingState.font);
		email.setLocation(0, 30);
		email.setWidth(200);
		createNew.add(email);
		
        ActionListener emailEnter = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	submitButton.press();
            } 
        };	

		KeyListener emailTab = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_TAB) {
					submitButton.grabFocus();
				}
			}
		};
		
		email.addActionListener(emailEnter);
		email.addKeyListener(emailTab);
	}

	private void createLabels() {		
		usernameLabel = new Label("Username");
		usernameLabel.setBounds(menuX, menuY, 200, 30);
		usernameLabel.setForeground(Color.black);
		usernameLabel.setFont(LoadingState.labelFont);
		display.add(usernameLabel);
		
		passwordLabel = new Label("Password");
		passwordLabel.setBounds(menuX, menuY + 60, 200, 30);
		passwordLabel.setForeground(Color.black);
		passwordLabel.setFont(LoadingState.labelFont);
		display.add(passwordLabel);
		
		emailLabel = new Label("Email");
		emailLabel.setBounds(0, 0, 200, 30);
		emailLabel.setForeground(Color.black);
		emailLabel.setFont(LoadingState.labelFont);
		createNew.add(emailLabel);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {		
		g.setAntiAlias(true);
		background.getScaledCopy(DnDClient.SCREENWIDTH, DnDClient.SCREENHEIGHT).draw(0, 0);		
		display.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {          
		display.update(gc, delta);
	}
	
	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void receiveObject(Object object) {
		if(object instanceof Login) {
			Login login = (Login)object;
			if(login.accepted) {
				usernameText = login.username;
				gc.getInput().clearKeyPressedRecord();
				sb.enterState(DnDClient.MAINMENUSTATE, new FadeOutTransition(), new FadeInTransition());	
			} else {
				((Label) popup.getChildByName("invalidLoginLabel")).setText(login.errorMessage);
				popup.setVisible(true);
				popup.grabFocus();
			}			
		}
		
		if(object instanceof CreateAccount) {
			CreateAccount createAccount = (CreateAccount)object;
			if(createAccount.accepted) {
				sb.enterState(DnDClient.LOGINSSTATE, null, new FadeInTransition());
				createNew.setVisible(false);
				login.setVisible(true);
				((Label) popup.getChildByName("invalidLoginLabel")).setText("Account Created");
				popup.setVisible(true);
				popup.grabFocus();
			} else {
				((Label) popup.getChildByName("invalidLoginLabel")).setText(createAccount.error);
				popup.setVisible(true);
				popup.grabFocus();
			}
		}
	}
}