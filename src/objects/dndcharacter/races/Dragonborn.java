package objects.dndcharacter.races;

import objects.dndcharacter.Abilities.AbilityType;
import objects.dndcharacter.classes.DnDClass.ClassSkills;
import enums.Enums.Size;
import enums.Enums.Vision;

public class Dragonborn extends Race {
	
	String descripton = "Born to fight, dragonborn are a race of wandering mercenaries, soldiers and adventurers. " +
								  "Long ago, their empire contended for worldwide domination, but now only a few rootless clans " +
								  "of these honorable warriors remain to pass on their legends of ancient glory.";

//								  "  - Favors : Warlord, Fighter and Paladin";
	
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
		return "Dragonborn";
	}

	@Override
	public String getDescription() {
		return descripton;
	}

	@Override
	public void setRacialTraits() {
		getRacialTraits().getAbilities().put(AbilityType.STRENGTH, 2);
		getRacialTraits().getAbilities().put(AbilityType.CHARISMA, 2);
		getRacialTraits().getSkills().put(ClassSkills.HISTORY, 2);
		getRacialTraits().getSkills().put(ClassSkills.INTIMIDATE, 2);
	}

}
