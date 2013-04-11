package objects.dndcharacter.races;

import enums.Enums.Size;
import enums.Enums.Vision;

public class Human extends Race {

	@Override
	public Size getSize() {
		return Size.MEDIUM;
	}

	@Override
	public int getSpeed() {
		return 6;
	}

	@Override
	public Vision getVision() {
		return Vision.NORMAL;
	}
	
	@Override
	public String toString() {
		return "Human";
	}
}
