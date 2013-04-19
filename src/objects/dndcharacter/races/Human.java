package objects.dndcharacter.races;

import objects.dndcharacter.Abilities.AbilityType;
import objects.dndcharacter.Defense.DefenseType;
import enums.Enums.Size;
import enums.Enums.Vision;

public class Human extends Race {
	
	private String description = "Of all the civilized races, humans are the most adaptable and diverse. Human settlements can " +
			  					 "be found almost anywhere, and human morals, customs, and interests vary greatly. Humans are " +
			  					 "decisive and sometimes rash. They explore the darkest reaches of the world in search of " +
			  					 "knowledge and power.";
//			  "  - Ability Scores : +2 One Ability Score\n\n" +
//			  "  - Bonus At-Will Power : One Extra Power\n\n" +
//			  "  - Bonus Feat : One Extra Feat\n\n" +
//			  "  - Bonus Skill : One Extra Skill\n\n" +
//			  "  - Defense Bonus : +1 to Fortitude, Reflex and Will\n\n" +
//			  "  - Favors : Any Class";
	
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

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setRacialTraits() {
		getRacialTraits().getAbilities().put(AbilityType.ANY, 2);
		getRacialTraits().getDefenses().put(DefenseType.FORTITUDE, 1);
		getRacialTraits().getDefenses().put(DefenseType.REFLEX, 1);
		getRacialTraits().getDefenses().put(DefenseType.WILL, 1);
	}
}
