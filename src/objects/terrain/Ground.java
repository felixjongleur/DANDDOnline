package objects.terrain;

import gui.util.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;

public class Ground extends Terrain {

	static List<Image> groundImages;
	
	boolean imageLoaded;
	
	public Ground(int x, int y) {
		super(x, y);

		if(groundImages == null) {
			generateImages();
		}
		Random rand = new Random();
		setImage(groundImages.get(rand.nextInt(groundImages.size())));
	}

	private void generateImages() {
		if(groundImages == null) {
			Image image = ResourceManager.getInstance().getImage("GROUND");
			groundImages = new ArrayList<Image>();
			
			for(int x = 0; x < image.getWidth(); x += 100) {
				for(int y = 0; y < image.getHeight(); y += 100) {
					groundImages.add(image.getSubImage(x, y, 100, 100));
					groundImages.add(image.getSubImage(x, y, 100, 100).getFlippedCopy(true, false));
					groundImages.add(image.getSubImage(x, y, 100, 100).getFlippedCopy(false, true));
					groundImages.add(image.getSubImage(x, y, 100, 100).getFlippedCopy(true, true));
				}
			}
		}
	}
}
