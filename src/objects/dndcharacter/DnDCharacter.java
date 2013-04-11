package objects.dndcharacter;

import java.util.ArrayList;
import java.util.List;

import objects.WorldObject;
import objects.dndcharacter.Alignment.AlignmentStance;
import objects.dndcharacter.classes.DnDClass;
import objects.dndcharacter.classes.DnDClass.ClassType;
import objects.dndcharacter.races.Race;

import combat.CombatSimulator;

import enums.Enums.RaceType;
import gui.util.managers.ResourceManager;

public class DnDCharacter extends WorldObject {

	public static enum Gender { MALE, FEMALE };
	
	private Gender gender;
	private int weight; // lbs
	private int height; // inches
	private int age;
	private DnDClass classType;
	private Race raceType;
	private String name;
	private int hitPoints;
	private Defense armor;
	private Experience xp;
	private Alignment alignment;
	private Abilities abilities;
	private Language language;
	private List<BackPack> bags;

	public Abilities getAbilities() {
		return abilities;
	}

	public void setAbilities(Abilities abilities) {
		this.abilities = abilities;
	}

	public DnDCharacter(){
		this("");
	};
	
	public DnDCharacter(String name) {
		this(name, ClassType.DEFAULT, RaceType.DEFAULT, AlignmentStance.UNALIGNED);
	}
	
	public DnDCharacter(String name, ClassType classType, RaceType raceType, AlignmentStance alignment) {
		this(0, 0, name, classType, raceType, alignment);
	}

	public DnDCharacter(int x, int y, String name, ClassType classType, RaceType raceType, AlignmentStance alignment) {
		super(x, y);
		this.setName(name);
		this.setArmor(new Defense());
		this.classType = DnDClass.getClassFromMap(classType);
		this.raceType  = Race.getRaceFromMap(raceType);
		this.language = new Language(raceType);
		this.setAlignment(new Alignment(alignment));
		this.abilities = new Abilities(10);
		this.hitPoints = this.classType.getStartHP(abilities.getConstitution());
		this.xp = new Experience(0);
		this.setAge(generateAge());
		
		bags = new ArrayList<BackPack>();
		
		for(int num = 0; num < 5; num++) {
			bags.add(new BackPack());
		}
		
		String imageName = this.raceType + "_" + this.classType;
		setImage(ResourceManager.getInstance().getImage(imageName.toUpperCase()));
	}
	
	@Override
	public String toString() {
		return "Lvl. " + xp.getLevel() + " - " + raceType.toString() + " " + classType.toString(); 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCurrentXP() {
		return xp.getCurrentExperience();
	}
	
	public void addExperience(int points) {
		int oldLevel = getLevel();
		
		xp.addToExperience(points);
		
		int levelsGained = getLevel() - oldLevel;
		for(int level = 0; level < levelsGained; level++) { // only if level gained
			levelGained();
		}
	}
	
	private void levelGained() {
		hitPoints += classType.getHPModifier();
	}

	public int attack(int attackRoll, DnDCharacter opposingCharacter) {
		return new CombatSimulator().fight(this, opposingCharacter, attackRoll);
	}

	public int getLevel() {
		return xp.getLevel();
	}

	public void decrementHP(int hp) {
		hitPoints -= hp;
	}

	public int getArmor() {
		//return 10 + armorBonus + shieldBonus + abilities.getModifier(abilities.getDexterity()) + sizeModifier;
		return Math.max(0, armor.getArmor() + abilities.getAbilityModifier(abilities.getDexterity()));
	}

	public void setDefense(int defense) {
		armor.setArmor(defense);
	}
	
	public void setArmor(Defense armor) {
		this.armor = armor;
	}

	public void setHP(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getHP() {
		
		if(hitPoints == 0) {
			return 0;
		}
		
		int constitutionModifier = abilities.getAbilityModifier(abilities.getConstitution());		
		if(hitPoints + constitutionModifier < 1) {
			return 1;
		}
		
		return hitPoints + constitutionModifier;
	}
	
	public boolean isDead() {
		return getHP() <= 0;
	}		
	
	private int generateAge() {
//		if(raceType.getClass() == Human.class && classType.getClass() == Rogue.class) {
//			return Dice.roll(1, 4, 15);
//		} else if(raceType.getClass() == Human.class && classType.getClass() == Fighter.class) {
//			return Dice.roll(1, 6, 15);
//		}
		return 0 ;
	}

	public int getStrength() {
		return abilities.getStrength();
	}

	public int getDexterity() {
		return abilities.getDexterity();
	}

	public int getConstitution() {
		return abilities.getConstitution();
	}

	public int getWisdom() {
		return abilities.getWisdom();
	}

	public int getIntelligence() {
		return abilities.getIntelligence();
	}

	public int getCharisma() {
		return abilities.getCharisma();
	}

	public void setStrength(int strength) {
		abilities.setStrength(strength);		
	}

	public void setDexterity(int dexterity) {
		abilities.setDexterity(dexterity);
	}

	public void setConstitution(int constitution) {
		abilities.setConstitution(constitution);
	}

	public DnDClass getClassType() {
		return classType;
	}

	public void setClassType(DnDClass classType) {
		this.classType = classType;
	}

	public Race getRaceType() {
		return raceType;
	}

	public void setRaceType(Race raceType) {
		this.raceType = raceType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public BackPack getBag(int number) {
		return bags.get(number);
	}
}
