package objects.dndcharacter.races;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import enums.Enums.RaceType;
import enums.Enums.Size;

public class HumanTest {

	@Test
	public void testHumanIsMediumSized() throws Exception {
		assertEquals(Size.MEDIUM, Race.getRaceFromMap(RaceType.HUMAN).getSize());
	} 

}
