package objects.dndcharacter.classes;

import static org.junit.Assert.assertEquals;
import objects.dndcharacter.classes.DnDClass.ClassType;

import org.junit.Test;

public class ClassTest {

	@Test
	public void testConstructorFighter() throws Exception {
		assertEquals(Fighter.class, DnDClass.getClassFromMap(ClassType.FIGHTER).getClass());
	}

	@Test
	public void testConstructorRogue() throws Exception {
		assertEquals(Cleric.class, DnDClass.getClassFromMap(ClassType.CLERIC).getClass());
	}
}
