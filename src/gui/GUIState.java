package gui;

import java.util.ArrayList;
import java.util.List;

import mdes.slick.sui.Button;
import objects.Tree;
import objects.World;
import objects.WorldObject;
import objects.WorldObjectStack;
import objects.dndcharacter.Alignment.AlignmentStance;
import objects.dndcharacter.DnDCharacter;
import objects.dndcharacter.classes.DnDClass.ClassType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import enums.Enums.RaceType;

public class GUIState extends BasicGameState {
	
	int stateID;
	
	Image bottomHUD, playerStats;
	World world;
	
	int xWidth, yWidth;
	int worldX, worldY;
	
	DnDCharacter currentCharacter;
	
	private boolean mainBag, bag1, bag2, bag3, bag4;
	private Button mainBagButton, bag1Button, bag2Button, bag3Button, bag4Button;
	
	private List<Button> buttons;
	
	public GUIState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		bottomHUD = new Image("data/HUDbottom.png");
		playerStats = new Image("data/playerStats.png");
		
		buttons = new ArrayList<Button>();
		
		createBagButtons();
		
		world = new World(12, 6);
				
		xWidth = 100;
		yWidth = 100;
		
		worldX = 40;
		worldY = 105;
		
		currentCharacter = new DnDCharacter(5, 5, "Thargor", ClassType.FIGHTER, RaceType.HUMAN, AlignmentStance.GOOD);
		currentCharacter.getBag(0).addObjectAt(new Tree(-1, -1), 0);

		currentCharacter.getBag(3).addObjectStackAt(new WorldObjectStack(new Tree(-1, -1), 5), 12);
		world.getWorldObjectAt(5, 5).setWorldObject(currentCharacter);
	}

	private void createBagButtons() {
//		mainBagButton = new Button(1172, 725, 39, 39, null, new ActionHandler() {			
//			@Override
//			public void onAction() {
//				mainBag = toggleBoolean(mainBag);
//			}
//		});
//		bag1Button = new Button(1126, 725, 39, 39, null, new ActionHandler() {			
//			@Override
//			public void onAction() {
//				bag1 = toggleBoolean(bag1);
//			}
//		});
//		bag2Button = new Button(1078, 725, 39, 39, null, new ActionHandler() {			
//			@Override
//			public void onAction() {
//				bag2 = toggleBoolean(bag2);
//			}
//		});
//		bag3Button = new Button(1030, 725, 39, 39, null, new ActionHandler() {			
//			@Override
//			public void onAction() {
//				bag3 = toggleBoolean(bag3);
//			}
//		});
//		bag4Button = new Button(983, 725, 39, 39, null, new ActionHandler() {			
//			@Override
//			public void onAction() {
//				bag4 = toggleBoolean(bag4);
//			}
//		});
		buttons.add(mainBagButton);
		buttons.add(bag1Button);
		buttons.add(bag2Button);
		buttons.add(bag3Button);
		buttons.add(bag4Button);		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
		for(WorldObject object : world.getWorldObjects()) {
			object.render(xWidth, yWidth, worldX, worldY);
			if(object.getWorldObject() != null) {
				object.getWorldObject().render(xWidth, yWidth, worldX, worldY);
			}
		}

		bottomHUD.draw(0, 0);
		playerStats.draw(0, 0);
		
		renderBags(g);

		for(Button button : buttons) {
//			button.render(g);
		}
		
		g.drawString(currentCharacter.getName(), 100, 18);
	}

	private void renderBags(Graphics g) {
		if(mainBag) { currentCharacter.getBag(0).draw(1025, 400, g); }
		if(bag1) 	{ currentCharacter.getBag(1).draw(1025, 100, g); }
		if(bag2)	{ currentCharacter.getBag(2).draw(800, 400, g);  }
		if(bag3) 	{ currentCharacter.getBag(3).draw(800, 100, g);  }
		if(bag4)	{ currentCharacter.getBag(4).draw(575, 400, g);  }
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
		if(sb.getCurrentStateID() == stateID) {
			Input input = gc.getInput();

			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				for(Button button : buttons) {
//					if(button.checkClick(input.getMouseX(), input.getMouseY())) {
//						button.getActionHandler().onAction();
//					}
				}
			}
			if(input.isKeyPressed(Input.KEY_B)) {
				if(mainBag || bag1 || bag2 || bag3 || bag4) {
					mainBag = bag1 = bag2 = bag3 = bag4 = false;
				} else {
					mainBag = toggleBoolean(mainBag);
					bag1 = toggleBoolean(bag1);
					bag2 = toggleBoolean(bag2);
					bag3 = toggleBoolean(bag3);
					bag4 = toggleBoolean(bag4);
				}
			}
		}
	}
	
	private boolean toggleBoolean(boolean booleanToToggle) {
		if(booleanToToggle) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void receiveObject(Object object) {
		// TODO Auto-generated method stub
		
	}
}
