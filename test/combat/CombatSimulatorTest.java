package combat;
import static org.junit.Assert.assertEquals;

import objects.dndcharacter.DnDCharacter;

import org.junit.Test;


public class CombatSimulatorTest {
	
	@Test
	public void testAttackSucceedsAndDefendersHpIsDecrementedByOne() throws Exception {
		DnDCharacter opponent = new DnDCharacter();
		new CombatSimulator().fight(new DnDCharacter(), opponent, 19);
		assertEquals(4, opponent.getHP());
	}
	
	@Test
	public void testAttackDoesntSucceedDefendersHpIsNotDecremented() throws Exception {
		DnDCharacter opponent = new DnDCharacter();
		new CombatSimulator().fight(new DnDCharacter(), opponent, 2);
		assertEquals(5, opponent.getHP());
	}
	
	@Test
	public void testNaturalTwentyDealsDoubleDamage() throws Exception {
		DnDCharacter opponent = new DnDCharacter();
		new CombatSimulator().fight(new DnDCharacter(), opponent, 20);
		assertEquals(1, opponent.getHP());
	}
	
	@Test
	public void testNaturalOneIsAMiss() throws Exception {
		assertEquals(0, new CombatSimulator().fight(new DnDCharacter(), new DnDCharacter(), 1));
	}
	
	@Test
	public void testStrengthModifierAddsToAttackRoll() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setStrength(20);
		int roll = 10;
		assertEquals(roll + 5, new CombatSimulator().getModifiedAttackRoll(questCharacter, roll));
	}
	
	@Test
	public void testStrengthModifierCantMakeAttackRollGreaterThan20() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setStrength(20);
		int roll = 16;
		assertEquals(20, new CombatSimulator().getModifiedAttackRoll(questCharacter, roll));
	}
	
	@Test
	public void testStrengthModifierCantMakeAttackRollLowerThan0() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.setStrength(1);
		int roll = 4;
		assertEquals(0, new CombatSimulator().getModifiedAttackRoll(questCharacter, roll));
	}
	
	@Test
	public void testForEveryEvenLevelAddOneToAttackRoll_EvenLevel() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.addExperience(1000);
		assertEquals(11, new CombatSimulator().getModifiedAttackRoll(questCharacter, 10));
	}
	
	@Test
	public void testForEveryEvenLevelAddOneToAttackRoll_OddLevel() throws Exception {
		DnDCharacter questCharacter = new DnDCharacter();
		questCharacter.addExperience(2000);
		assertEquals(11, new CombatSimulator().getModifiedAttackRoll(questCharacter, 10));
	}
}
