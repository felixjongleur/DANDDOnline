package objects;

import org.newdawn.slick.Image;

public class WorldObject {

	protected int x, y;
	
	private Image image;
	private WorldObject worldObject;
	
	public WorldObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public WorldObject(WorldObject copyWorldObject) {
		this.x = copyWorldObject.x;
		this.y = copyWorldObject.y;
		this.image = copyWorldObject.image;
		if(copyWorldObject.worldObject != null) {
			this.worldObject = new WorldObject(copyWorldObject.worldObject);
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void render(int xWidth, int yWidth, int worldX, int worldY) {		
		if(image != null) {
			getImage().draw(x * xWidth + worldX, y * yWidth + worldY);
		}	
	}
	
	public void setWorldObject(WorldObject worldObject) {
		this.worldObject = worldObject;
	}
	
	public WorldObject getWorldObject() {
		return worldObject;
	}
}
