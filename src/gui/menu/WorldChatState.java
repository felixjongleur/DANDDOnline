package gui.menu;

import gui.util.managers.ResourceManager;
import kryonet.DnDNetwork.Message;
import kryonet.DnDNetwork.UpdateNames;
import kryonet.client.DnDClient;
import mdes.slick.sui.Button;
import mdes.slick.sui.Display;
import mdes.slick.sui.Label;
import mdes.slick.sui.ScrollPane;
import mdes.slick.sui.Sui;
import mdes.slick.sui.TextArea;
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

public class WorldChatState extends BasicGameState {

	private int stateID;
	
	Display display;
	GameContainer gc;
	StateBasedGame sb;
	
	TextArea chatWindow, playerWindow, messageField;
	ScrollPane pane;
	
	Button backButton, sendButton;
	
	private Image background;
	
	private int startingRows = 35;
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.enter(gc, sb);
		init(gc, sb);

		DnDClient.loginCharacter(((Label)MainMenuState.currentCharacter.getChildByName("characterName")).getText());
	}
	
	public WorldChatState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		this.gc = gc;
		this.sb = sb;
		gc.getGraphics().setBackground(Sui.getTheme().getBackground());
		
		display = new Display(gc);
		
		background = ResourceManager.getInstance().getImage("WORLD_CHAT_IMAGE");
	
		chatWindow = new TextArea(76, startingRows);
		chatWindow.setBackground(new Color(0, 0, 0, 0.6f));
		chatWindow.setForeground(Color.white);
		chatWindow.setOpaque(true);
		chatWindow.setEditable(false);
		
		pane = new ScrollPane(chatWindow);
		pane.setLocation(125, 75);
		pane.setBackground(new Color(0, 0, 0, 0f));
		pane.setOpaque(true);
		pane.setSize(chatWindow.getWidth(), chatWindow.getHeight() + 2);		
		display.add(pane);
		
		String str = "This is an example of a really long string ... This is an example of a really long string ... This is an example of a really long string ... This is an example of a really long string ... This is an example of a really long string ... This is an example of a really long string ... ";
		messageField = new TextArea(65, 2);
		messageField.setLocation(125, chatWindow.getAbsoluteY() + chatWindow.getHeight() + 12);
		messageField.setBackground(new Color(0, 0, 0, 0.6f));
		messageField.setForeground(Color.white);
		messageField.setOpaque(true);
		messageField.setAutoResize(false);
		messageField.setNewLine(false);
		display.add(messageField);
		
		KeyListener newMessage = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {				
				if(e.getKeyCode() == Input.KEY_ENTER) {
					DnDClient.sendChatMessage(messageField.getText());
					messageField.setText("");
				}
			}
		};		
		
		messageField.addKeyListener(newMessage);

		playerWindow = new TextArea(15, 38);
		playerWindow.setLocation(chatWindow.getAbsoluteX() + chatWindow.getWidth() + 10, 75);
		playerWindow.setBackground(new Color(0, 0, 0, 0.6f));
		playerWindow.setForeground(Color.white);
		playerWindow.setOpaque(true);
		playerWindow.setEditable(false);
		display.add(playerWindow);	
		
		createButtons();
	}

	private void createButtons() {	
		backButton = new Button("Back");
		backButton.setFont(LoadingState.font);
		backButton.pack();
		backButton.setForeground(Color.white);
		backButton.setLocation(messageField.getAbsoluteX() - 100, messageField.getAbsoluteY());
		backButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		backButton.packImage();
		display.add(backButton);
		
		ActionListener backAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				DnDClient.disconnectCharacter();
				sb.enterState(DnDClient.MAINMENUSTATE, new FadeOutTransition(), new FadeInTransition());
			}
		};		
		backButton.addActionListener(backAction);		

		sendButton = new Button("Send");
		sendButton.setFont(LoadingState.font);
		sendButton.pack();
		sendButton.setForeground(Color.white);
		sendButton.setLocation(messageField.getAbsoluteX() + messageField.getWidth() + 35, messageField.getAbsoluteY());
		sendButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		sendButton.packImage();
		display.add(sendButton);
		
		ActionListener sendAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				DnDClient.sendChatMessage(messageField.getText());
				messageField.setText("");
			}
		};		
		sendButton.addActionListener(sendAction);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		background.getScaledCopy(DnDClient.SCREENWIDTH, DnDClient.SCREENHEIGHT).draw(0, 0);
		display.render(gc, g);
	}

	@Override
	public void receiveObject(Object object) {		
		if(object instanceof Message) {
			Message chatMessage = (Message)object;
			// Ignore the object if the chat message is invalid.
			String message = chatMessage.text;
			if (message == null) return;
			chatWindow.setText(chatWindow.getText() + message + "\n");
			
			if(chatWindow.getLineCount() > startingRows) {
				pane.scrollToBottom();
			}
		}
		
		if(object instanceof UpdateNames) {
			UpdateNames players = (UpdateNames)object;
			playerWindow.setText("");
			
			for(String player : players.names) {
				playerWindow.setText(playerWindow.getText() + player + "\n");
			}
		}
	}
    
    public void update(GameContainer container, StateBasedGame sb, int delta) throws SlickException {
        display.update(container, delta);
    }

	@Override
	public int getID() {
		return stateID;
	}
}
