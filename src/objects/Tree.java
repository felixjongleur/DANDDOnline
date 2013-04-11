package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree extends WorldObject {

	public Tree(int x, int y) {
		super(x, y);
		
		try {
			setImage(new Image("data/tree.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
