package objects.dndcharacter;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import objects.dndcharacter.Abilities;
import objects.dndcharacter.DnDCharacter;

import org.junit.Test;

public class AbilitiesTest {
	
	Map<Integer, Integer> scoreModifierMap = new HashMap<Integer, Integer>();
	{
		scoreModifierMap.put(1, -5);
		scoreModifierMap.put(2, -4);
		scoreModifierMap.put(3, -4);
		scoreModifierMap.put(4, -3);
		scoreModifierMap.put(5, -3);
		scoreModifierMap.put(6, -2);
		scoreModifierMap.put(7, -2);
		scoreModifierMap.put(8, -1);
		scoreModifierMap.put(9, -1);
		scoreModifierMap.put(10, 0);
		scoreModifierMap.put(11, 0);
		scoreModifierMap.put(12, 1);
		scoreModifierMap.put(13, 1);
		scoreModifierMap.put(14, 2);
		scoreModifierMap.put(15, 2);
		scoreModifierMap.put(16, 3);
		scoreModifierMap.put(17, 3);
		scoreModifierMap.put(18, 4);
		scoreModifierMap.put(19, 4);
		scoreModifierMap.put(20, 5);
	}

	@Test
	public void testScoreModifierTable() throws Exception {
		Abilities abilities = new Abilities();
		for(Entry<Integer, Integer> scoreModifier : scoreModifierMap.entrySet()){
			assertEquals("Expected: "+scoreModifier.getValue()+" for a score of: "+scoreModifier.getKey(),
					scoreModifier.getValue(), (Integer)abilities.getModifier(scoreModifier.getKey()));
		}
	}

	@Test
	public void testStrengthModifierAddsToDamageDealt() throws Exception {
		DnDCharacter me = new DnDCharacter();
		DnDCharacter you = new DnDCharacter();
		me.setStrength(20);
		int roll = 16;
		assertEquals(6, me.attack(roll, you));
	}
	
	@Test
	public void testStrengthModifierCantMakeDamageLessThan1() throws Exception {
		DnDCharacter me = new DnDCharacter();
		DnDCharacter you = new DnDCharacter();
		me.setStrength(1);
		assertEquals(1, me.attack(19, you));
	}
	
	@Test
	public void testStrengthModifierCantMakeDamageLessThan1EvenOnCrit() throws Exception {
		DnDCharacter me = new DnDCharacter();
		DnDCharacter you = new DnDCharacter();
		me.setStrength(1);
		assertEquals(1, me.attack(20, you));
	}
	
	@Test
	public void testConstitutionModifierAddedToHP_HighBound() throws Exception {
		DnDCharacter me = new DnDCharacter();
		me.setConstitution(20);
		assertEquals(10, me.getHP());
	}
	
	@Test
	public void testConstitutionModifierAddedToHP_LowBound() throws Exception {
		DnDCharacter me = new DnDCharacter();
		me.setConstitution(1);
		assertEquals(1, me.getHP());
	}
}
