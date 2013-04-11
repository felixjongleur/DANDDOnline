package objects.dndcharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import objects.util.Range;

public class Experience {
	
	private static Map<Integer, List<Integer>> levelToXPFeatsAndPowers;
	static {
		levelToXPFeatsAndPowers = new HashMap<Integer, List<Integer>>();
		// Level -> XP, Feats, At-Will, Encounter, Daily, Utility, Ability Scores
		levelToXPFeatsAndPowers.put(1,  new ArrayList<Integer>(Arrays.asList(1000, 	  1,  2, 1, 1, 0, 0)));
		levelToXPFeatsAndPowers.put(2,  new ArrayList<Integer>(Arrays.asList(2250, 	  2,  2, 2, 1, 1, 0)));
		levelToXPFeatsAndPowers.put(3,  new ArrayList<Integer>(Arrays.asList(3750, 	  2,  2, 2, 1, 1, 0)));
		levelToXPFeatsAndPowers.put(4,  new ArrayList<Integer>(Arrays.asList(5500, 	  3,  2, 2, 2, 1, 2)));
		levelToXPFeatsAndPowers.put(5,  new ArrayList<Integer>(Arrays.asList(7500, 	  3,  2, 2, 2, 1, 0)));
		levelToXPFeatsAndPowers.put(6,  new ArrayList<Integer>(Arrays.asList(10000,	  4,  2, 2, 2, 2, 0)));
		levelToXPFeatsAndPowers.put(7,  new ArrayList<Integer>(Arrays.asList(13000,   4,  2, 3, 2, 2, 0)));
		levelToXPFeatsAndPowers.put(8,  new ArrayList<Integer>(Arrays.asList(16500,   5,  2, 3, 3, 2, 2)));
		levelToXPFeatsAndPowers.put(9,  new ArrayList<Integer>(Arrays.asList(20500,   5,  2, 3, 3, 2, 0)));
		levelToXPFeatsAndPowers.put(10, new ArrayList<Integer>(Arrays.asList(26000,   6,  2, 3, 3, 3, 0)));
		levelToXPFeatsAndPowers.put(11, new ArrayList<Integer>(Arrays.asList(32000,   7,  2, 4, 3, 3, 0)));
		levelToXPFeatsAndPowers.put(12, new ArrayList<Integer>(Arrays.asList(39000,   8,  2, 4, 3, 4, 0)));
		levelToXPFeatsAndPowers.put(13, new ArrayList<Integer>(Arrays.asList(47000,   8,  2, 4, 3, 4, 0)));
		levelToXPFeatsAndPowers.put(14, new ArrayList<Integer>(Arrays.asList(57000,   9,  2, 4, 3, 4, 2)));
		levelToXPFeatsAndPowers.put(15, new ArrayList<Integer>(Arrays.asList(69000,   9,  2, 4, 3, 4, 0)));
		levelToXPFeatsAndPowers.put(16, new ArrayList<Integer>(Arrays.asList(83000,   10, 2, 4, 3, 5, 0)));
		levelToXPFeatsAndPowers.put(17, new ArrayList<Integer>(Arrays.asList(99000,   10, 2, 4, 3, 5, 0)));
		levelToXPFeatsAndPowers.put(18, new ArrayList<Integer>(Arrays.asList(119000,  11, 2, 4, 3, 5, 2)));
		levelToXPFeatsAndPowers.put(19, new ArrayList<Integer>(Arrays.asList(143000,  11, 2, 4, 3, 5, 0)));
		levelToXPFeatsAndPowers.put(20, new ArrayList<Integer>(Arrays.asList(175000,  12, 2, 4, 4, 5, 0)));
		levelToXPFeatsAndPowers.put(21, new ArrayList<Integer>(Arrays.asList(210000,  13, 2, 4, 4, 5, 0)));
		levelToXPFeatsAndPowers.put(22, new ArrayList<Integer>(Arrays.asList(255000,  14, 2, 4, 4, 6, 0)));
		levelToXPFeatsAndPowers.put(23, new ArrayList<Integer>(Arrays.asList(310000,  14, 2, 4, 4, 6, 0)));
		levelToXPFeatsAndPowers.put(24, new ArrayList<Integer>(Arrays.asList(375000,  15, 2, 4, 4, 6, 2)));
		levelToXPFeatsAndPowers.put(25, new ArrayList<Integer>(Arrays.asList(450000,  15, 2, 4, 4, 6, 0)));
		levelToXPFeatsAndPowers.put(26, new ArrayList<Integer>(Arrays.asList(550000,  16, 2, 4, 4, 7, 0)));
		levelToXPFeatsAndPowers.put(27, new ArrayList<Integer>(Arrays.asList(675000,  16, 2, 4, 4, 7, 0)));
		levelToXPFeatsAndPowers.put(28, new ArrayList<Integer>(Arrays.asList(825000,  17, 2, 4, 4, 7, 2)));
		levelToXPFeatsAndPowers.put(29, new ArrayList<Integer>(Arrays.asList(1000000, 17, 2, 4, 4, 7, 0)));
		levelToXPFeatsAndPowers.put(30, new ArrayList<Integer>(Arrays.asList(9999999, 18, 2, 4, 4, 7, 0)));
	};
	
	private Range totalExperience = new Range(0, 0, Integer.MAX_VALUE);
	
	public Experience() {
		
	}
	
	public Experience(int experience){
		this.totalExperience.setValue(experience);
	}
	
	public int getCurrentExperience() {
		return totalExperience.getValue();
	}
	
	public void addToExperience(int value) {
		totalExperience.add(value);
	}
	
	public int getLevel() {
		for(Entry<Integer, List<Integer>> map : levelToXPFeatsAndPowers.entrySet()) {
			if(totalExperience.getValue() < map.getValue().get(0)) {
				return map.getKey();
			}
		}
		return 0;
	}
}
