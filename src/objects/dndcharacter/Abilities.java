package objects.dndcharacter;
import objects.util.Range;

public class Abilities {

	public static enum AbilityType { STRENGTH, CONSTITUTION, DEXTERITY, INTELLIGENCE, WISDOM, CHARISMA };
	
	private Range strength, constitution, dexterity, intelligence, wisdom, charisma;

	public static int MINIMUM_ABILITY_VALUE = 1;
	public static int MAXIMUM_ABILITY_VALUE = Integer.MAX_VALUE;
	
	public Abilities() {
		
	}
	
	public Abilities(int defaultValue){
		strength = createAbility(defaultValue);
		dexterity = createAbility(defaultValue); 
		constitution = createAbility(defaultValue);
		wisdom = createAbility(defaultValue);
		intelligence = createAbility(defaultValue);
		charisma = createAbility(defaultValue);
	}
	
	protected Range createAbility(int defaultValue) {
		return new Range(MINIMUM_ABILITY_VALUE, defaultValue, MAXIMUM_ABILITY_VALUE);
	}
	
	public int getStrength() {
		return strength.getValue();
	}

	public void setStrength(int strength) {
		this.strength.setValue(strength);
	}

	public int getDexterity() {
		return dexterity.getValue();
	}

	public void setDexterity(int dexterity) {
		this.dexterity.setValue(dexterity);
	}

	public int getConstitution() {
		return constitution.getValue();
	}

	public void setConstitution(int constitution) {
		this.constitution.setValue(constitution);
	}

	public int getWisdom() {
		return wisdom.getValue();
	}

	public void setWisdom(int wisdom) {
		this.wisdom.setValue(wisdom);
	}

	public int getIntelligence() {
		return intelligence.getValue();
	}

	public void setIntelligence(int intelligence) {
		this.intelligence.setValue(intelligence);
	}

	public int getCharisma() {
		return charisma.getValue();
	}

	public void setCharisma(int charisma) {
		this.charisma.setValue(charisma);
	}

	public static int getAbilityModifier(int value) {
		return value / 2 - 5;
	}

}
