package objects;

import java.util.ArrayList;
import java.util.List;

public class WorldObjectStack {

	private List<WorldObject> stack;
	
	public WorldObjectStack() {
		stack = new ArrayList<WorldObject>();
	}
	
	public WorldObjectStack(WorldObject object, int numberOf) {
		this();
		for(int num = 0; num < numberOf; num++) {
			stack.add(new WorldObject(object));
		}
	}
	
	public WorldObjectStack(WorldObject object) {
		this(object, 1);
	}
	
	public WorldObject getWorldObject() {
		return stack.get(0);
	}
	
	public int getNumberOfWorldObjects() {
		return stack.size();
	}
}
