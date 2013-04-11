package objects.dndcharacter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import objects.dndcharacter.classes.DnDClass.ClassType;

import org.junit.Test;

import enums.Enums.RaceType;

public class DnDCharacterTest {

	@Test
	public void testCharacterConstruction() {
		new DnDCharacter("");
	}

	@Test
	public void testCharacterConstructerSetsName() throws Exception {
		assertEquals ("Name", new DnDCharacter("Name").getName());
	}
	
	@Test
	public void testCharacterHasTheNameSetOnIt() throws Exception {
		DnDCharacter character = new DnDCharacter();
		character.setName("Name");
	}
	
	@Test
	public void testNewCharacterHasFiveHP() throws Exception {
		assertEquals(5, new DnDCharacter().getHP());
	}
	
	@Test
	public void testWhenHPIsLessThanOneCharacterIsDead() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setHP(0);
		assertTrue(questCharacter.isDead());
	}
	
	@Test
	public void testWhenHPIsLessThanZeroCharacterIsDead() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setHP(-1);
		assertTrue(questCharacter.isDead());
	}
	
	@Test
	public void testWhenHPIsGreaterThanZeroCharacterIsAlive() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setHP(1);
		assertFalse(questCharacter.isDead());
	}
	
	@Test
	public void testAbilityDefaultValue_strength() throws Exception {
		assertEquals(10, new DnDCharacter().getStrength());
	}
	
	@Test
	public void testAbilityDesfaultValue_dexterity() throws Exception {
		assertEquals(10, new DnDCharacter().getDexterity());
	}
	
	@Test
	public void testAbilityDesfaultValue_constitution() throws Exception {
		assertEquals(10, new DnDCharacter().getConstitution());
	}
	
	@Test
	public void testAbilityDesfaultValue_Wisdom() throws Exception {
		assertEquals(10, new DnDCharacter().getWisdom());
	}
	
	@Test
	public void testAbilityDesfaultValue_Intelligence() throws Exception {
		assertEquals(10, new DnDCharacter().getIntelligence());
	}
	
	@Test
	public void testAbilityDesfaultValue_Charisma() throws Exception {
		assertEquals(10, new DnDCharacter().getCharisma());
	}
	
	@Test
	public void testNewCharacterDefaultsToLevel1() throws Exception {
		DnDCharacter me = new DnDCharacter();
		assertEquals(1, me.getLevel());
	}
	
	@Test
	public void testEvery1000HitPointsLevelsUpCharacter() throws Exception {
		DnDCharacter me = new DnDCharacter();
		me.addExperience(2000);
		assertEquals(3, me.getLevel());
	}
	
	@Test
	public void testEveryLevelIncreasesBaseHPBy5() throws Exception {
		DnDCharacter me = new DnDCharacter();
		me.addExperience(2000);
		assertEquals(15, me.getHP());
	}
	
	@Test
	public void testHumanRoguesAgeIsBetween16and19() throws Exception {
		int age = new DnDCharacter("", ClassType.ROGUE, RaceType.HUMAN, null).getAge();
		assertTrue(age >= 16 && age <= 19);
	}
	
	@Test
	public void testHumanFightersAgeIsBetween16and19() throws Exception {
		int age = new DnDCharacter("", ClassType.FIGHTER, RaceType.HUMAN, null).getAge();
		assertTrue(age >= 16 && age <= 21);
	}
}
