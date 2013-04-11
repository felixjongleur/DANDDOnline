package objects.dndcharacter;

import static org.junit.Assert.assertEquals;

import objects.dndcharacter.Defense;

import org.junit.Test;

public class DefenseTest {

	@Test
	public void testBaseAmountOfArmor() throws Exception {
		assertEquals(10, new Defense().getArmor());
	}
	
}
