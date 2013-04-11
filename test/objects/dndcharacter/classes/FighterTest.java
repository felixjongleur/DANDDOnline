package objects.dndcharacter.classes;

import static org.junit.Assert.assertEquals;
import objects.dndcharacter.DnDCharacter;
import objects.dndcharacter.classes.DnDClass.ClassType;

import org.junit.Test;

import enums.Enums.RaceType;

public class FighterTest {

	@Test
	public void testSkillToAbilityMap() throws Exception {
		DnDCharacter me = new DnDCharacter("", ClassType.FIGHTER, RaceType.DEFAULT, null);
		assertEquals(7, me.getClassType().getSkillToAbilityMap().size());
	}
	
	@Test
	public void testName() throws Exception {
		
	}
}
