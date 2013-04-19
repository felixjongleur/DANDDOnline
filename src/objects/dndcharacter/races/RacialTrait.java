package objects.dndcharacter.races;

import java.util.HashMap;
import java.util.Map;

import objects.dndcharacter.Abilities.AbilityType;
import objects.dndcharacter.Defense.DefenseType;
import objects.dndcharacter.classes.DnDClass.ClassSkills;

public class RacialTrait {

	private Map<AbilityType, Integer> abilities;
	private Map<ClassSkills, Integer> skills;
	private Map<DefenseType, Integer> defenses;
	
	public RacialTrait() {
		abilities = new HashMap<AbilityType, Integer>();
		skills = new HashMap<ClassSkills, Integer>();
		defenses = new HashMap<DefenseType, Integer>();
	}

	public Map<AbilityType, Integer> getAbilities() {
		return abilities;
	}

	public Map<ClassSkills, Integer> getSkills() {
		return skills;
	}
	
	public Map<DefenseType, Integer> getDefenses() {
		return defenses;
	}
}
