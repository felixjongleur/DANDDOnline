package objects.dndcharacter.classes;

import static org.junit.Assert.assertEquals;
import objects.dndcharacter.DnDCharacter;
import objects.dndcharacter.classes.DnDClass.ClassType;

import org.junit.Test;

import combat.CombatSimulator;

import enums.Enums.RaceType;

public class RogueTest {

	@Test
	public void testThatCriticalHitsDeal3xDamage() throws Exception {
		DnDCharacter me = new DnDCharacter("", ClassType.ROGUE, RaceType.DEFAULT, null);
		assertEquals(15, new CombatSimulator().getModifiedDamage(me, 5, true));
	}
}
