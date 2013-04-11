package objects.dndcharacter;

import gui.util.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import objects.WorldObject;
import objects.WorldObjectStack;

import org.newdawn.slick.Graphics;

public class BackPack  {
	
	private List<WorldObjectStack> contents;
	
	public BackPack() {
		
		contents = new ArrayList<WorldObjectStack>();
		
		for(int pos = 0; pos < 16; pos++) {
			contents.add(new WorldObjectStack());
		}
	}
	
	public void addObject(WorldObject object) {
		contents.add(new WorldObjectStack(object));
	}
	
	public void addObjectAt(WorldObject object, int position) {
		contents.set(position, new WorldObjectStack(object));
	}
	
	public void addObjectStackAt(WorldObjectStack objectStack, int position) {
		contents.set(position, objectStack);
	}
	
	public void draw(int x, int y, Graphics g) {
		ResourceManager.getInstance().getImage("BACK_PACK").draw(x, y);
		
		int counter = 0;
		for(WorldObjectStack stack : contents) {
			if(stack.getNumberOfWorldObjects() > 0) {
				int xPos = counter % 4;
				int yPos = counter / 4;
				stack.getWorldObject().getImage().draw(x + 24 + (47 * xPos), y + 61 + (47 * yPos));
				if(stack.getNumberOfWorldObjects() > 1) {
					g.drawString(Integer.toString(stack.getNumberOfWorldObjects()), x + 26 + (47 * xPos), y + 58 + (47 * yPos));
				}
			}
			counter++;
		}
		
//		testTree.draw(x + 24, y + 61);
//		testTree.draw(x + 24 + 47, y + 61);
//		testTree.draw(x + 24 + 47 + 47, y + 61);
	}
}
