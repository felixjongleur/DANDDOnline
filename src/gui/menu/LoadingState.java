package gui.menu;

import gui.util.managers.ResourceManager;

import java.io.IOException;

import kryonet.client.DnDClient;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class LoadingState extends BasicGameState {

	int stateID;
	boolean done;
	
	public static Font infoFont, font, labelFont;
	Image background;
	
	private DeferredResource nextResource;
	
	public LoadingState(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		background = ResourceManager.getInstance().getImage("LOADING_SCREEN_BACKGROUND");

		infoFont = ResourceManager.getInstance().getFont("MENU_BUTTON_FONT", 18);
		font = ResourceManager.getInstance().getFont("MENU_BUTTON_FONT", 22);		
		labelFont = ResourceManager.getInstance().getFont("MENU_BUTTON_FONT", 28);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {		
		g.setAntiAlias(true);
		background.getScaledCopy(DnDClient.SCREENWIDTH, DnDClient.SCREENHEIGHT).draw(0, 0);
		
		if (nextResource != null) {
			g.setFont(labelFont);
			g.drawString("Loading : "+nextResource.getDescription(), 400, 600);
		}
		
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
		
		g.fillRect(400,650,loaded*40,20);
		g.drawRect(400,650,total*40,20);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {		
		if(nextResource != null) {
			try {
				nextResource.load();

//				try { Thread.sleep(200); } catch (Exception e) {}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "+nextResource.getDescription(), e);
			}
			nextResource = null;
		}
		
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			sb.enterState(DnDClient.LOGINSSTATE, new FadeOutTransition(), new FadeInTransition());
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
