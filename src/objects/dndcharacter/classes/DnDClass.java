package objects.dndcharacter.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objects.armor.Armor.ArmorType;
import objects.dndcharacter.Abilities.AbilityType;
import objects.weapons.Weapon.WeaponType;

public abstract class DnDClass {

	public enum ClassType { DEFAULT, CLERIC, FIGHTER, PALADIN, RANGER, ROGUE, WARLOCK, WARLORD, WIZARD };
	
	public enum ClassSkills { ARCANA, ATHLETICS, DIPLOMACY, ENDURANCE, HEAL, HISTORY, INSIGHT, INTIMIDATE, RELIGION, STREETWISE };
	
	public enum Role { CONTROLLER, DEFENDER, LEADER, STRIKER };
	
	public enum PowerSource { ARCANE, DIVINE, MARTIAL };
	
	private List<AbilityType> keyAbilities = new ArrayList<AbilityType>();
	
	private List<ArmorType> armorProficiencies = new ArrayList<ArmorType>();
	
	private List<WeaponType> weaponProficiencies = new ArrayList<WeaponType>();
	
	private List<ClassSkills> trainedSkills = new ArrayList<ClassSkills>();
	
	private Map<ClassSkills, AbilityType> skillToAbilityMap = new HashMap<ClassSkills, AbilityType>();

	public abstract Role getRole();
	public abstract PowerSource getPowerSource();
	protected abstract void setKeyAbilities();
	protected abstract void setArmorProficiencies();
	protected abstract void setWeaponProficiencies();
	public abstract int getStartHP(int constitution);
	public abstract int getHPModifier();
	public abstract int getDailyHealingSurges(int constitution);
	protected abstract void setSkillToAbilityMap();
	public abstract int getMinNumberOfTrainedSkills();	
	public abstract ClassType getClassType();	
	public abstract String toString();

	private static Map<ClassType, DnDClass> typeToClassMap;
	static {
		typeToClassMap = new HashMap<ClassType, DnDClass>();
		typeToClassMap.put(ClassType.DEFAULT, new DefaultClass());
		typeToClassMap.put(ClassType.CLERIC, new Cleric());
		typeToClassMap.put(ClassType.FIGHTER, new Fighter());
//		typeToClassMap.put(ClassType.PALADIN, new Paladin());
//		typeToClassMap.put(ClassType.RANGER, new Ranger());
//		typeToClassMap.put(ClassType.ROGUE, new Rogue());
//		typeToClassMap.put(ClassType.WARLOCK, new Warlock());
//		typeToClassMap.put(ClassType.WARLORD, new Warlord());
//		typeToClassMap.put(ClassType.WIZARD, new Wizard());
	};
	
	public static DnDClass getClassFromMap(ClassType type) {
		return typeToClassMap.get(type);
	}
	
	public Map<ClassSkills, AbilityType> getSkillToAbilityMap() {
		return this.skillToAbilityMap;
	}
	
	public List<AbilityType> getKeyAbilities() {
		return keyAbilities;
	}
	
	public List<ArmorType> getArmorProficiencies() {
		return armorProficiencies;
	}

	public List<ClassSkills> getTrainedSkills() {
		return trainedSkills;
	}

	public void setTrainedSkills(List<ClassSkills> trainedSkills) {
		this.trainedSkills = trainedSkills;
	}

	public List<WeaponType> getWeaponProficiencies() {
		return weaponProficiencies;
	}
}
