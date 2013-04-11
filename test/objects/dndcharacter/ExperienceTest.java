package objects.dndcharacter;

import static org.junit.Assert.*;

import objects.dndcharacter.DnDCharacter;

import org.junit.Test;

public class ExperienceTest {

	@Test
	public void testNewCharacterHasZeroXP() throws Exception {
		DnDCharacter me = new DnDCharacter();
		assertEquals(0, me.getCurrentXP());
	}

	@Test
	public void testWhenAttackIsSuccessful_Gain10XP() throws Exception {
		DnDCharacter me = new DnDCharacter();
		DnDCharacter you = new DnDCharacter();
		me.attack(20, you);
		assertEquals(10, me.getCurrentXP());
	}

	@Test
	public void testWhenAttackFails_Gain0XP() throws Exception {
		DnDCharacter me = new DnDCharacter();
		DnDCharacter you = new DnDCharacter();
		me.attack(1, you);
		assertEquals(0, me.getCurrentXP());
	}
	
	@Test
	public void testWhenLevelingUpExperienceIsRemainderOutOf1000() throws Exception {
		DnDCharacter me = new DnDCharacter();
		me.addExperience(2467);
		assertEquals(467, me.getCurrentXP());
	}
}
