package gui.menu;

import gui.util.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import kryonet.DnDNetwork.GetCharacters;
import kryonet.client.DnDClient;
import mdes.slick.sui.Button;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.Label;
import mdes.slick.sui.Popup;
import mdes.slick.sui.Sui;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import mdes.slick.sui.event.MouseAdapter;
import mdes.slick.sui.event.MouseEvent;
import objects.dndcharacter.DnDCharacter;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {
	
	int stateID;
	Display display;
	GameContainer gc;
	StateBasedGame sb;
	Container dungeons, characterContainer;
	public static Container currentCharacter;
	
	Button createCharacterButton, deleteCharacterButton;
	Button createDungeonButton, deleteDungeonButton;
	Button loginEditButton, backButton;
	
	Popup popup;

	List<DnDCharacter> characters;
	List<Container> characterContainers;
	
	boolean charactersLoaded;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.enter(gc, sb);
		init(gc, sb);
		DnDClient.getCharacters(LoginState.usernameText);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {		
		this.gc = gc;
		this.sb = sb;
		charactersLoaded = false;
		gc.getGraphics().setBackground(Sui.getTheme().getBackground());
		
		display = new Display(gc);
	
		createDungeonContainer();		
		createCharacterContainer();		
		otherButtons();
		createPopups();
	}

	private void createPopups() {
		popup = new Popup();			
		popup.setSize(300, 100);
		popup.setLocationRelativeTo(display);
		popup.setBackground(new Color(0, 0, 0, 0.6f));
		popup.setOpaque(true);
		popup.setVisible(false);			

		Label label = new Label("Loading Characters ...");
		label.setFont(LoadingState.font);
		label.setSize(300, 100);
		label.setLocationRelativeTo(popup);
		label.setForeground(Color.white);
		popup.add(label);
		
		display.add(popup);
	}
	
	private void otherButtons() {
		loginEditButton = new Button("Login - Edit");
		loginEditButton.setFont(LoadingState.font);
		loginEditButton.pack();
		loginEditButton.setForeground(Color.white);
		loginEditButton.setLocationRelativeTo(display);
		loginEditButton.setY(createCharacterButton.getAbsoluteY());
		loginEditButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		loginEditButton.packImage();
		loginEditButton.setVisible(false);
		display.add(loginEditButton);
		
		ActionListener loginEditAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.enterState(DnDClient.WORLDCHATSTATE, new FadeOutTransition(), new FadeInTransition());
			}
		};		
		loginEditButton.addActionListener(loginEditAction);		
		
		backButton = new Button("Back");
		backButton.setFont(LoadingState.font);
		backButton.pack();
		backButton.setForeground(Color.white);
		backButton.setLocationRelativeTo(display);
		backButton.setY(deleteCharacterButton.getAbsoluteY());
		backButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		backButton.packImage();
		display.add(backButton);
		
		ActionListener backAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.enterState(DnDClient.LOGINSSTATE, new FadeOutTransition(), new FadeInTransition());
			}
		};		
		backButton.addActionListener(backAction);
	}

	private void createDungeonContainer() {
		dungeons = new Container();
		dungeons.setBounds(10, 10, DnDClient.SCREENWIDTH / 4 - 50, DnDClient.SCREENHEIGHT - 10);
		dungeons.setBackground(new Color(0, 0, 0, 0.6f));
		dungeons.setOpaque(true);
		
		Label dungeonLabel = new Label("Dungeons");
		dungeonLabel.setForeground(Color.white);
		dungeonLabel.setFont(LoadingState.labelFont);
		dungeonLabel.pack();
		dungeonLabel.setLocationRelativeTo(dungeons);
		dungeonLabel.setY(0);
		dungeons.add(dungeonLabel);
		
		createDungeonButton = new Button("Create New");
		createDungeonButton.setFont(LoadingState.font);
		createDungeonButton.pack();
		createDungeonButton.setForeground(Color.white);
		createDungeonButton.setLocationRelativeTo(dungeons);
		createDungeonButton.setY(dungeons.getHeight() - createDungeonButton.getHeight() - 50);
		createDungeonButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		createDungeonButton.packImage();
		dungeons.add(createDungeonButton);
		
		deleteDungeonButton = new Button("Delete Dungeon");
		deleteDungeonButton.setFont(LoadingState.font);
		deleteDungeonButton.pack();
		deleteDungeonButton.setForeground(Color.white);
		deleteDungeonButton.setLocationRelativeTo(dungeons);
		deleteDungeonButton.setY(dungeons.getHeight() - createDungeonButton.getHeight() - 5);
		deleteDungeonButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		deleteDungeonButton.packImage();
		dungeons.add(deleteDungeonButton);
		display.add(dungeons);
	}

	private void createCharacterContainer() {
		characterContainer = new Container();
		characterContainer.setBounds(DnDClient.SCREENWIDTH * 3/4, 10, DnDClient.SCREENWIDTH / 4 - 10, DnDClient.SCREENHEIGHT - 10);
		characterContainer.setBackground(new Color(0, 0, 0, 0.6f));
		characterContainer.setOpaque(true);
		
		Label characterLabel = new Label("Characters");
		characterLabel.setForeground(Color.white);
		characterLabel.setFont(LoadingState.labelFont);
		characterLabel.pack();
		characterLabel.setLocationRelativeTo(characterContainer);
		characterLabel.setY(0);
		characterContainer.add(characterLabel);
		
		createCharacterButton = new Button("Create New");
		createCharacterButton.setFont(LoadingState.font);
		createCharacterButton.pack();
		createCharacterButton.setForeground(Color.white);
		createCharacterButton.setLocationRelativeTo(characterContainer);
		createCharacterButton.setY(characterContainer.getHeight() - createCharacterButton.getHeight() - 50);
		createCharacterButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		createCharacterButton.packImage();
		characterContainer.add(createCharacterButton);
		
		ActionListener createCharacterAction = new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				sb.enterState(DnDClient.CREATECHARSTATE, null, new FadeInTransition());
			}
		};
		
		createCharacterButton.addActionListener(createCharacterAction);
		
		deleteCharacterButton = new Button("Delete Character");
		deleteCharacterButton.setFont(LoadingState.font);
		deleteCharacterButton.pack();
		deleteCharacterButton.setForeground(Color.white);
		deleteCharacterButton.setLocationRelativeTo(characterContainer);
		deleteCharacterButton.setY(characterContainer.getHeight() - createCharacterButton.getHeight() - 5);
		deleteCharacterButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		deleteCharacterButton.packImage();
		characterContainer.add(deleteCharacterButton);
		display.add(characterContainer);
	}
	
	private void addCharacters(List<DnDCharacter> characters) {
		this.characters = characters;
		characterContainers = new ArrayList<Container>();
		int yOffset = 0;
		for(DnDCharacter character : characters) {
			final Container container = new Container();
			container.setBounds(5, 40 + yOffset, characterContainer.getWidth() - 10, 70);
			container.setBackground(new Color(0, 0, 0, 0));
			container.setOpaque(true);
			yOffset += 70;
			
			MouseAdapter mouse = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					currentCharacter = container;
				}
			};			
			container.addMouseListener(mouse);
			
			Label name = new Label(character.getName());
			name.setName("characterName");
			name.setGlassPane(true);
			name.setX(2);
			name.setForeground(Color.red);
			name.setFont(LoadingState.font);
			name.pack();
			container.add(name);
			
			Label info = new Label(character.toString());
			info.setGlassPane(true);
			info.setForeground(Color.white);
//			info.setFont(infoFont);
			info.pack();
			info.setLocation(2, name.getHeight() + 5);
			container.add(info);			
			
			characterContainer.add(container);
			characterContainers.add(container);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {		
		g.setAntiAlias(true);
		ResourceManager.getInstance().getImage("NEW_GAME_SCREEN_BACKGROUND").getScaledCopy(DnDClient.SCREENWIDTH, DnDClient.SCREENHEIGHT).draw(0, 0);
		
		if(charactersLoaded) {
			popup.setVisible(false);
			loginEditButton.setVisible(false);
			for(Container container : characterContainers) {
				if(currentCharacter == container) {
					loginEditButton.setVisible(true);
					currentCharacter.setBackground(new Color(255, 255, 0, 0.6f));
				} else {
					container.setBackground(new Color(0, 0, 0, 0));
				}
			}	
		} else {
			popup.setVisible(true);
		}
		
		display.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void receiveObject(Object object) {
		if(object instanceof GetCharacters) {
			GetCharacters characters = (GetCharacters)object;
			addCharacters(characters.results);
			charactersLoaded = true;
		}
		
	}
}
