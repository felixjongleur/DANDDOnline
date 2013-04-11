package objects;

import java.util.ArrayList;
import java.util.List;

import objects.terrain.Ground;

import org.newdawn.slick.SlickException;


public class World {
	
	private int xMax, yMax;
	
	private List<WorldObject> worldObjects;

	public World(int xMax, int yMax) throws SlickException {
		this.xMax = xMax;
		this.yMax = yMax;
		worldObjects = new ArrayList<WorldObject>();

		for(int y = 0; y < yMax; y++) {
			for(int x = 0; x < xMax; x++) {
				worldObjects.add(convertXandYToLocation(x, y), new Ground(x, y));
			}
		}
	}
	
	private int convertXandYToLocation(int x, int y) {
		return y * xMax + x;
	}
	
	public List<WorldObject> getWorldObjects() {
		return worldObjects;
	}
	
	public WorldObject getWorldObjectAt(int x, int y) {
		return worldObjects.get(convertXandYToLocation(x, y));
	}
	
	public void setWorldObjectAt(WorldObject worldObject, int x, int y) {
		worldObjects.set(convertXandYToLocation(x, y), worldObject);
	}
	
	public int getXMax() {
		return xMax;
	}

	public int getYMax() {
		return yMax;
	}
}
