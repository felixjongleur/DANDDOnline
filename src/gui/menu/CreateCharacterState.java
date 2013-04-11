package gui.menu;

import gui.util.AlertMessagePopup;
import gui.util.managers.ResourceManager;
import kryonet.DnDNetwork.CreateCharacter;
import kryonet.client.DnDClient;
import mdes.slick.sui.Button;
import mdes.slick.sui.Component;
import mdes.slick.sui.Container;
import mdes.slick.sui.Display;
import mdes.slick.sui.Label;
import mdes.slick.sui.ScrollPane;
import mdes.slick.sui.Sui;
import mdes.slick.sui.TextArea;
import mdes.slick.sui.TextField;
import mdes.slick.sui.ToggleButton;
import mdes.slick.sui.ToggleButtonGroup;
import mdes.slick.sui.event.ActionEvent;
import mdes.slick.sui.event.ActionListener;
import mdes.slick.sui.event.ChangeEvent;
import mdes.slick.sui.event.ChangeListener;
import mdes.slick.sui.event.KeyEvent;
import mdes.slick.sui.event.KeyListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class CreateCharacterState extends BasicGameState {

	int stateID;
	Display display;
	GameContainer gc;
	StateBasedGame sb;
	
	Container characterContainer;
	TextArea classDescription, raceDescription;
	ScrollPane classPane, racePane;
	
	ToggleButton dragonbornButton, halflingButton, dwarfButton, elfButton;
	ToggleButton eladrinButton, humanButton, tieflingButton, halfElfButton;
	
	ToggleButton maleButton, femaleButton;
	
	ToggleButton clericButton, fighterButton, paladinButton, rangerButton;
	ToggleButton rogueButton, warlockButton, warlordButton, wizardButton;
	
	Label characterClass;

	ToggleButtonGroup raceGroup;
	ToggleButtonGroup classGroup;	
	ToggleButtonGroup genderGroup;
	
	TextField characterName;
	Button acceptButton;

	private AlertMessagePopup popup;
	private boolean render = false;
	
	String genderForImages = "MALE";
	
	String humanDescripton =      "             " +
								  "HUMAN\n\n            " +
								  "Of all the civilized races, humans are the most adaptable and diverse. Human settlements can " +
								  "be found almost anywhere, and human morals, customs, and interests vary greatly. Humans are " +
								  "decisive and sometimes rash. They explore the darkest reaches of the world in search of " +
								  "knowledge and power.\n\n" +
								  "  - Ability Scores : +2 One Ability Score\n\n" +
								  "  - Bonus At-Will Power : One Extra Power\n\n" +
								  "  - Bonus Feat : One Extra Feat\n\n" +
								  "  - Bonus Skill : One Extra Skill\n\n" +
								  "  - Defense Bonus : +1 to Fortitude,\n" +
								  "                                Reflex and Will\n\n" +
								  "  - Favors : Any Class";
	String dragonbornDescripton = "             " +
								  "DRAGONBORN\n\n            " +
								  "Born to fight, dragonborn are a race   of wandering mercenaries, soldiers and adv- enturers. " +
								  "Long ago, their empire contended for worldwide domination, but now only a    few rootless clans " +
								  "of these honorable warr-  iors remain to pass on their legends of    ancient glory.\n\n" +
								  "  - Ability Scores : +2 Strength; +2 Charisma\n\n" +
								  "  - Skill Bonuses  : +2 History ; +2 Intimidate\n\n" +
								  "  - Favors : Warlord, Fighter and Paladin" +
								  "\n\n\n\n\n";
	
	String clericDescription =    "             " +
								  "CLERIC\n\n            " +
								  "Battle leaders who are invested with divine power. They blast foes with magical prayers, " +
								  "bolster and heal companions, and lead the way to victory with a mace in one hand and a " +
								  "holy symbol in the other. Clerics run the gamut from humble servants of the common folk " +
								  "to ruthless enforcers \nof evil gods.\n\n" +
								  "  - Role : Leader \n\n" +
								  "  - Power Source : Divine \n\n" +
								  "  - Key Abilities : Wisdom, Strength,\n" +
								  "                          Charisma \n\n" +
								  "  - Armor Proficiencies : Cloth, Leather, \n" +
								  "                                     Hide, Chainmail \n\n" +
								  "  - Weapon Proficiencies : Simple Melee, \n" +
								  "                                         Simple Ranged \n\n" +
								  "  - Implement : Holy symbol \n\n" +
								  "  - Bonus to Defense : +2 Will \n\n" +
								  "  - Trained Skills : Religion \n\n" +
								  "  - Build Options : Battle Cleric, \n" +
								  "                            Devoted Cleric";
	String fighterDescription =   "             " +
								  "FIGHTER\n\n            " +
								  "Determined combat adepts that are trained to protect the other members of\ntheir adventuring " +
								  "groups. Fighters define \nthe front line by bashing and slicing foes \ninto submission while " +
								  "reflecting enemy atta-  cks through the use of heavy armor. Fighters draw weapons for gold, " +
								  "for glory, for duty, \nand for the mere joy of unrestrained martial exercise.\n\n" +
								  "  - Role : Defender \n\n" +
								  "  - Power Source : Martial \n\n" +
								  "  - Key Abilities : Strength, Dexterity\n" +
								  "                        Wisdom, Constitution \n\n" +
								  "  - Armor Proficiencies : Cloth, Leather, \n" +
								  "                                   Hide, Chainmail, Scale \n\n" +
								  "  - Weapon Proficiencies : Simple Melee,\n" +
								  "                                         Military Melee,\n" +
								  "                                         Simple Ranged,\n" +
								  "                                         Military Ranged \n\n" +
								  "  - Bonus to Defense : +2 Fortitude \n\n" +
								  "  - Build Options : Great Weapon Fighter, \n" +
								  "                             Guardian Fighter";
	
	public CreateCharacterState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.enter(gc, sb);
		init(gc, sb);
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		this.gc = gc;
		this.sb = sb;
		
		gc.getGraphics().setBackground(Sui.getTheme().getBackground());
		
		display = new Display(gc);
		
		raceGroup = new ToggleButtonGroup();
		classGroup = new ToggleButtonGroup();	
		genderGroup = new ToggleButtonGroup();
		
		createCharacterContainer();
		createRaceContainer();
		createClassContainer();
		createOptions();		
	}
	
	private void createOptions() {
		Label label = new Label("Name");
		label.setSize(100, 30);
		label.setLocationRelativeTo(display);
		label.setY(DnDClient.SCREENHEIGHT - 150);
		label.setForeground(Color.white);
		label.setFont(LoadingState.labelFont);
		display.add(label);
		
		characterName = new TextField(10);
		characterName.setMaxChars(10);
		characterName.setForeground(Color.white);
		characterName.setLocationRelativeTo(display);
		characterName.setY(label.getAbsoluteY() + 35);
		display.add(characterName);	
		
		KeyListener characterListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_TAB) {
					acceptButton.grabFocus();
				}
				
				if(e.getKeyCode() == Input.KEY_ENTER) {
					acceptButton.press();
				}
			}
		};	
		characterName.addKeyListener(characterListener);
		
		acceptButton = new Button("Accept");
		acceptButton.setSize(100, 30);
		acceptButton.setLocationRelativeTo(display);
		acceptButton.setY(characterName.getAbsoluteY() + 30);
		acceptButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
		acceptButton.setFont(LoadingState.font);
		acceptButton.packImage();
		acceptButton.setForeground(Color.white);
		acceptButton.setFocusColor(Color.yellow);
		display.add(acceptButton);
		
        ActionListener textAction = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	DnDClient.checkNewCharacter(LoginState.usernameText, characterName.getText(), classGroup.getSelectedButton().getName(), raceGroup.getSelectedButton().getName());
            } 
        };
        
        KeyListener acceptKeyListener = new KeyListener() {			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == Input.KEY_ENTER) {
					acceptButton.press();
				}
				if(e.getKeyCode() == Input.KEY_TAB) 
					characterName.grabFocus();
			}
		};
        acceptButton.addKeyListener(acceptKeyListener);
        acceptButton.addActionListener(textAction);
        
        popup = new AlertMessagePopup(display);
        
        Button backButton = new Button("Back");
        backButton.setSize(100, 30);
        backButton.setLocationRelativeTo(display);
        backButton.setY(acceptButton.getAbsoluteY() + 40);
        backButton.setImage(ResourceManager.getInstance().getImage("MENU_BUTTON"));
        backButton.setFont(LoadingState.font);
        backButton.packImage();
        backButton.setForeground(Color.white);
        backButton.setFocusColor(Color.yellow);
		display.add(backButton);
        
        ActionListener backButtonAction = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
				sb.enterState(DnDClient.MAINMENUSTATE, new FadeOutTransition(), new FadeInTransition());
            } 
        };        
        backButton.addActionListener(backButtonAction);
	}
	
	private void createClassContainer() {		
		classDescription = new TextArea();
		classDescription.setSize(300, characterContainer.getHeight() / 2 - 25);
		classDescription.setBackground(new Color(0, 0, 0, 0.6f));
		classDescription.setForeground(Color.white);
		classDescription.setOpaque(true);
		classDescription.setEditable(false);
		
		classPane = new ScrollPane(classDescription);
		classPane.setLocation(DnDClient.SCREENWIDTH - classDescription.getWidth() - 25, racePane.getAbsoluteY() + racePane.getHeight() + 50);
		classPane.setBackground(new Color(0, 0, 0, 0f));
		classPane.setOpaque(true);
		classPane.setSize(classDescription.getWidth(), classDescription.getHeight());
		System.out.println(classDescription.getWidth());
		display.add(classPane);
		
		ChangeListener textChanged = new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				classPane.scrollToTop();
			}
		};
		
		classDescription.addChangeListener(textChanged);
	}
	
	private void createRaceContainer() {		
		raceDescription = new TextArea();
		raceDescription.setSize(300, characterContainer.getHeight() / 2 - 25);
		raceDescription.setBackground(new Color(0, 0, 0, 0.6f));
		raceDescription.setForeground(Color.white);
		raceDescription.setOpaque(true);
		raceDescription.setEditable(false);
		raceDescription.setText(humanDescripton);
		
		racePane = new ScrollPane(raceDescription);
		racePane.setLocation(DnDClient.SCREENWIDTH - raceDescription.getWidth() - 25, characterContainer.getAbsoluteY());
		racePane.setBackground(new Color(0, 0, 0, 0f));
		racePane.setOpaque(true);
		racePane.setSize(raceDescription.getWidth(), raceDescription.getHeight());
		System.out.println(raceDescription.getWidth());
		display.add(racePane);
				
		ChangeListener textChanged = new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				racePane.scrollToTop();
			}
		};

		raceDescription.addChangeListener(textChanged);
	}
	
	private void createEmptySquares() {				
		createEmptySquaresForGroups(raceGroup);	
		createEmptySquaresForGroups(classGroup);	
		createEmptySquaresForGroups(genderGroup);
	}
	
	private void createEmptySquaresForGroups(ToggleButtonGroup group) {
		for(ToggleButton button : group.getGroup()) {
			createEmptySquare(button);
		}
	}
	
	private void createEmptySquare(Component object) {
		ResourceManager.getInstance().getImage("EMPTY_SQUARE").getScaledCopy((int)object.getWidth(), (int)object.getHeight()).draw(object.getAbsoluteX(), object.getAbsoluteY());		
	}

	private void createCharacterContainer() {
		characterContainer = new Container();
		characterContainer.setBounds(25, 25, 250, DnDClient.SCREENHEIGHT - 50);
		characterContainer.setBackground(new Color(0, 0, 0, 0.6f));
		characterContainer.setOpaque(true);
		
		int xStart = 40;
		int yStart = 55;
		int yOffset = 60;
		
		dragonbornButton = new ToggleButton();
		dragonbornButton.setName("DRAGONBORN");
		dragonbornButton.setBounds(xStart, yStart, 60, 60);
		dragonbornButton.setSelected(true);
		dragonbornButton.setText(dragonbornDescripton);
		dragonbornButton.setForeground(new Color(0, 0, 0, 0.0f));
		dragonbornButton.setGroup(raceGroup);
		characterContainer.add(dragonbornButton);

		halflingButton = new ToggleButton();
		halflingButton.setBounds(xStart, yStart + yOffset, 60, 60);
		halflingButton.setGroup(raceGroup);
		characterContainer.add(halflingButton);
		
		dwarfButton = new ToggleButton();
		dwarfButton.setBounds(xStart, yStart + 2 * yOffset, 60, 60);
		dwarfButton.setGroup(raceGroup);
		characterContainer.add(dwarfButton);
		
		elfButton = new ToggleButton();
		elfButton.setBounds(xStart, yStart + 3 * yOffset, 60, 60);
		elfButton.setGroup(raceGroup);
		characterContainer.add(elfButton);
		
		eladrinButton = new ToggleButton();
		eladrinButton.setBounds(xStart + 60 + xStart, yStart, 60, 60);
		eladrinButton.setGroup(raceGroup);
		characterContainer.add(eladrinButton);

		humanButton = new ToggleButton();
		humanButton.setName("HUMAN");
		humanButton.setForeground(new Color(0, 0, 0, 0.0f));
		humanButton.setText(humanDescripton);
		humanButton.setBounds(xStart + 60 + xStart, yStart + yOffset, 60, 60);
		humanButton.setGroup(raceGroup);
		characterContainer.add(humanButton);
		
		
		tieflingButton = new ToggleButton();
		tieflingButton.setBounds(xStart + 60 + xStart, yStart + 2 * yOffset, 60, 60);
		tieflingButton.setGroup(raceGroup);
		characterContainer.add(tieflingButton);
		
		halfElfButton = new ToggleButton();
		halfElfButton.setBounds(xStart + 60 + xStart, yStart + 3 * yOffset, 60, 60);
		halfElfButton.setGroup(raceGroup);
		characterContainer.add(halfElfButton);
		
		maleButton = new ToggleButton();
		maleButton.setSelected(true);
		maleButton.setForeground(new Color(0, 0, 0, 0.0f));
		maleButton.setText("MALE");
		maleButton.setRolloverImage(ResourceManager.getInstance().getImage("MALE_SYMBOL_SELECTED"));
		maleButton.setSelectedImage(ResourceManager.getInstance().getImage("MALE_SYMBOL_SELECTED"));
		maleButton.setBounds(65, yStart + 4 * yOffset + 20, 50, 50);
		maleButton.setImage(ResourceManager.getInstance().getImage("MALE_SYMBOL"));
		maleButton.setGroup(genderGroup);
		characterContainer.add(maleButton);
		
		femaleButton = new ToggleButton();
		femaleButton.setForeground(new Color(0, 0, 0, 0.0f));
		femaleButton.setText("FEMALE");
		femaleButton.setRolloverImage(ResourceManager.getInstance().getImage("FEMALE_SYMBOL_SELECTED"));
		femaleButton.setSelectedImage(ResourceManager.getInstance().getImage("FEMALE_SYMBOL_SELECTED"));
		femaleButton.setBounds(125, yStart + 4 * yOffset + 20, 50, 50);
		femaleButton.setImage(ResourceManager.getInstance().getImage("FEMALE_SYMBOL"));
		femaleButton.setGroup(genderGroup);
		characterContainer.add(femaleButton);
		
		genderForImages = genderGroup.getSelectedButton().getText();
		setRaceImages();
		
//		characterClass = new Label("TEST");
//		characterClass.setForeground(Color.white);
//		characterClass.setFont(LoadingState.labelFont);
//		characterClass.pack();
//		characterClass.setLocationRelativeTo(characterContainer);
//		characterClass.setY(yStart + 4 * yOffset + 80);
//		characterContainer.add(characterClass);
		
		xStart = 5;
		yStart = yStart + 4 * yOffset + 120;
				
		clericButton = new ToggleButton();
		clericButton.setBounds(xStart, yStart, 60, 60);
		clericButton.setForeground(new Color(0, 0, 0, 0.0f));
		clericButton.setImage(ResourceManager.getInstance().getImage("CLERIC"));
		clericButton.setSelected(true);
		clericButton.setText(clericDescription);
		clericButton.setName("CLERIC");
		clericButton.setGroup(classGroup);
		characterContainer.add(clericButton);

		fighterButton = new ToggleButton();
		fighterButton.setBounds(xStart + 60, yStart, 60, 60);
		fighterButton.setForeground(new Color(0, 0, 0, 0.0f));
		fighterButton.setImage(ResourceManager.getInstance().getImage("FIGHTER"));
		fighterButton.setGroup(classGroup);
		fighterButton.setText(fighterDescription);
		fighterButton.setName("FIGHTER");
		characterContainer.add(fighterButton);
		
		paladinButton = new ToggleButton();
		paladinButton.setBounds(xStart + 2 * 60, yStart, 60, 60);
		paladinButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		paladinButton.setGroup(classGroup);
		characterContainer.add(paladinButton);
		
		rangerButton = new ToggleButton();
		rangerButton.setBounds(xStart + 3 * 60, yStart, 60, 60);
		rangerButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		rangerButton.setGroup(classGroup);
		characterContainer.add(rangerButton);
		
		rogueButton = new ToggleButton();
		rogueButton.setBounds(xStart, yStart + 60, 60, 60);
		rogueButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		rogueButton.setGroup(classGroup);
		characterContainer.add(rogueButton);

		warlockButton = new ToggleButton();
		warlockButton.setForeground(new Color(0, 0, 0, 0.0f));
		warlockButton.setBounds(xStart + 60, yStart + 60, 60, 60);
		warlockButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		warlockButton.setGroup(classGroup);
		characterContainer.add(warlockButton);
		
		
		warlordButton = new ToggleButton();
		warlordButton.setBounds(xStart + 2 * 60, yStart + 60, 60, 60);
		warlordButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		warlordButton.setGroup(classGroup);
		characterContainer.add(warlordButton);
		
		wizardButton = new ToggleButton();
		wizardButton.setBounds(xStart + 3 * 60, yStart + 60, 60, 60);
		wizardButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		wizardButton.setGroup(classGroup);
		characterContainer.add(wizardButton);
		
		display.add(characterContainer);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		g.setAntiAlias(true);
		packImages();
		
		ResourceManager.getInstance().getImage("CREATE_CHARACTER_BACKGROUND").getScaledCopy(DnDClient.SCREENWIDTH, DnDClient.SCREENHEIGHT).draw(0, 0);		

		if(render) {
			display.render(gc, g);
			createEmptySquares();		
			raceGroup.getSelectedButton().getImage().draw(racePane.getAbsoluteX() - 15, racePane.getAbsoluteY() - 15);
			ResourceManager.getInstance().getImage("EMPTY_SQUARE").getScaledCopy(60, 60).draw(racePane.getAbsoluteX() - 15, racePane.getAbsoluteY() - 15);
			classGroup.getSelectedButton().getImage().draw(classPane.getAbsoluteX() - 15, classPane.getAbsoluteY() - 15);
			ResourceManager.getInstance().getImage("EMPTY_SQUARE").getScaledCopy(60, 60).draw(classPane.getAbsoluteX() - 15, classPane.getAbsoluteY() - 15);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {	
		genderForImages = genderGroup.getSelectedButton().getText();
		setRaceImages();
		raceDescription.setText(raceGroup.getSelectedButton().getText());
		classDescription.setText(classGroup.getSelectedButton().getText());		
		
		display.update(gc, delta);
		render = true;
	}

	@Override
	public void receiveObject(Object object) {
		if(object instanceof CreateCharacter) {
			CreateCharacter character = (CreateCharacter)object;
			if(character.accepted) {
				sb.enterState(DnDClient.MAINMENUSTATE, null, new FadeInTransition());
			} else {
				((Label) popup.getChildByName("invalidLoginLabel")).setText(character.error);
				popup.setVisible(true);
				popup.grabFocus();
			}
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	private void packImages() {
		dragonbornButton.packImage();
		halflingButton.packImage();
		dwarfButton.packImage();
		elfButton.packImage();
		eladrinButton.packImage();
		humanButton.packImage();
		tieflingButton.packImage();
		halfElfButton.packImage();
		maleButton.packImage();
		femaleButton.packImage();
		clericButton.packImage();
		fighterButton.packImage();
		paladinButton.packImage();
		rangerButton.packImage();
		rogueButton.packImage();
		warlockButton.packImage();
		warlordButton.packImage();
		wizardButton.packImage();
	}
	
	private void setRaceImages() {
		dragonbornButton.setImage(ResourceManager.getInstance().getImage(genderForImages+"_DRAGONBORN"));
		halflingButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		dwarfButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		elfButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		eladrinButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		humanButton.setImage(ResourceManager.getInstance().getImage(genderForImages+"_HUMAN"));
		tieflingButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
		halfElfButton.setImage(ResourceManager.getInstance().getImage("EMPTY_BAG_SQUARE"));
	}
}
