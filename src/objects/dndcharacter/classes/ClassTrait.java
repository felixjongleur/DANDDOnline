package objects.dndcharacter.classes;

import java.util.HashMap;
import java.util.Map;

import objects.dndcharacter.Defense.DefenseType;

public class ClassTrait {
	private Map<DefenseType, Integer> defenses;
	
	public ClassTrait() {
		defenses = new HashMap<DefenseType, Integer>();
	}

	public Map<DefenseType, Integer> getDefenses() {
		return defenses;
	}
}
