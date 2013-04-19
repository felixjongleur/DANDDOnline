package objects.dndcharacter.races;

import java.util.HashMap;
import java.util.Map;

import enums.Enums.RaceType;
import enums.Enums.Size;
import enums.Enums.Vision;

public abstract class Race {
	
	private RacialTrait racialTrait = new RacialTrait();
	
	private static Map<RaceType, Race> typeToRaceMap;
	static {
		typeToRaceMap = new HashMap<RaceType, Race>();
		typeToRaceMap.put(RaceType.DEFAULT, new DefaultRace());
		typeToRaceMap.put(RaceType.DRAGONBORN, new Dragonborn());
		typeToRaceMap.put(RaceType.DWARF, new Dwarf());		
		typeToRaceMap.put(RaceType.ELADRIN, new Eladrin());
		typeToRaceMap.put(RaceType.ELF, new Elf());
		typeToRaceMap.put(RaceType.HALFELF, new HalfElf());
		typeToRaceMap.put(RaceType.HALFLING, new Halfling());
		typeToRaceMap.put(RaceType.HUMAN, new Human());
		typeToRaceMap.put(RaceType.TIEFLING, new Tiefling());
	};
	
	public static Race getRaceFromMap(RaceType type) {
		return typeToRaceMap.get(type);
	}
	
	public Race() {
		setRacialTraits();
	}

	public abstract Size getSize();

	public abstract int getSpeed();

	public abstract Vision getVision();
	
	public abstract String toString();
	
	public abstract String getDescription();
	
	public abstract void setRacialTraits();

	public RacialTrait getRacialTraits() {
		return racialTrait;
	}
}
