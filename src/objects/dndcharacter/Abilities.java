package objects.dndcharacter;

public class Abilities {

	public static enum AbilityType { ANY, STRENGTH, CONSTITUTION, DEXTERITY, INTELLIGENCE, WISDOM, CHARISMA };
	
	private int strength, constitution, dexterity, intelligence, wisdom, charisma;
	
	public Abilities() {
		setDefaults();
	}
	
	private void setDefaults() {
		this.strength = 10;
		this.constitution = 10;
		this.dexterity = 10;
		this.intelligence = 10;
		this.wisdom = 10;
		this.charisma = 10;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public static int getModifier(int value) {
		return value / 2 - 5;
	}
}
