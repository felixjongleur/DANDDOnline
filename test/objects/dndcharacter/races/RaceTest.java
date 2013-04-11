package objects.dndcharacter.races;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import enums.Enums.RaceType;

public class RaceTest {
	@Test
	public void testConstructorHuman() throws Exception {
		assertEquals(Human.class, Race.getRaceFromMap(RaceType.HUMAN).getClass());
	}

}
