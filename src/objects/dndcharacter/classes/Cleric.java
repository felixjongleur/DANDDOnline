package objects.dndcharacter.classes;

import objects.armor.Armor.ArmorType;
import objects.dndcharacter.Abilities;
import objects.dndcharacter.Abilities.AbilityType;
import objects.dndcharacter.Defense.DefenseType;
import objects.weapons.Weapon.WeaponType;

public class Cleric extends DnDClass {

	private String description = "Battle leaders who are invested with divine power. They blast foes with magical prayers, " +
			  					 "bolster and heal companions, and lead the way to victory with a mace in one hand and a " +
			  					 "holy symbol in the other. Clerics run the gamut from humble servants of the common folk " +
			  					 "to ruthless enforcers of evil gods.";
	
	public Cleric() {
		super();
	}
	
	@Override
	public Role getRole() {
		return Role.LEADER;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.DIVINE;
	}

	@Override
	protected void setKeyAbilities() {
		getKeyAbilities().add(AbilityType.WISDOM);
		getKeyAbilities().add(AbilityType.STRENGTH);
		getKeyAbilities().add(AbilityType.CHARISMA);
	}

	@Override
	protected void setArmorProficiencies() {
		getArmorProficiencies().add(ArmorType.CLOTH);
		getArmorProficiencies().add(ArmorType.LEATHER);
		getArmorProficiencies().add(ArmorType.HIDE);
		getArmorProficiencies().add(ArmorType.CHAINMAIL);
	}

	@Override
	protected void setWeaponProficiencies() {
		getWeaponProficiencies().add(WeaponType.SIMPLE_MELEE);
		getWeaponProficiencies().add(WeaponType.SIMPLE_RANGED);
	}

	@Override
	public int getStartHP(int constitution) {
		return 12 + constitution;
	}

	@Override
	public int getHPModifier() {
		return 5;
	}

	@Override
	public int getDailyHealingSurges(int constitution) {
		return 7 + Abilities.getModifier(constitution);
	}

	@Override
	protected void setSkillToAbilityMap() {
		getSkillToAbilityMap().put(ClassSkills.ARCANA,    AbilityType.INTELLIGENCE);
		getSkillToAbilityMap().put(ClassSkills.DIPLOMACY, AbilityType.CHARISMA);
		getSkillToAbilityMap().put(ClassSkills.HEAL,      AbilityType.WISDOM);
		getSkillToAbilityMap().put(ClassSkills.HISTORY,   AbilityType.INTELLIGENCE);
		getSkillToAbilityMap().put(ClassSkills.INSIGHT,   AbilityType.WISDOM);
		getSkillToAbilityMap().put(ClassSkills.RELIGION,  AbilityType.INTELLIGENCE);
	}

	@Override
	public int getMinNumberOfTrainedSkills() {
		return 4;
	}

	@Override
	public ClassType getClassType() {
		return ClassType.CLERIC;
	}

	@Override
	public String toString() {
		return "Cleric";
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setClassTraits() {
		getClassTraits().getDefenses().put(DefenseType.WILL, 2);
	}
}
