package objects.dndcharacter.classes;

import objects.armor.Armor.ArmorType;
import objects.dndcharacter.Abilities;
import objects.dndcharacter.Abilities.AbilityType;
import objects.weapons.Weapon.WeaponType;

public class Fighter extends DnDClass {

	@Override
	public int getHPModifier() {
		return 6;
	}

	@Override
	public Role getRole() {
		return Role.DEFENDER;
	}

	@Override
	public ClassType getClassType() {
		return ClassType.FIGHTER;
	}

	@Override
	public int getStartHP(int constitution) {
		return 15 + constitution;
	}

	@Override
	public int getDailyHealingSurges(int constitution) {
		return 9 + Abilities.getAbilityModifier(constitution);
	}	
	
	@Override
	public String toString() {
		return "Fighter";
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.MARTIAL;
	}

	@Override
	public int getMinNumberOfTrainedSkills() {
		return 3;
	}

	@Override
	protected void setSkillToAbilityMap() {
		getSkillToAbilityMap().put(ClassSkills.ATHLETICS,  AbilityType.STRENGTH);
		getSkillToAbilityMap().put(ClassSkills.ENDURANCE,  AbilityType.CONSTITUTION);
		getSkillToAbilityMap().put(ClassSkills.HEAL, 	   AbilityType.WISDOM);
		getSkillToAbilityMap().put(ClassSkills.INTIMIDATE, AbilityType.CHARISMA);
		getSkillToAbilityMap().put(ClassSkills.STREETWISE, AbilityType.CHARISMA);
	}

	@Override
	protected void setKeyAbilities() {
		getKeyAbilities().add(AbilityType.STRENGTH);
		getKeyAbilities().add(AbilityType.DEXTERITY);
		getKeyAbilities().add(AbilityType.WISDOM);
		getKeyAbilities().add(AbilityType.CONSTITUTION);
	}

	@Override
	protected void setArmorProficiencies() {
		getArmorProficiencies().add(ArmorType.CLOTH);
		getArmorProficiencies().add(ArmorType.LEATHER);
		getArmorProficiencies().add(ArmorType.HIDE);
		getArmorProficiencies().add(ArmorType.CHAINMAIL);
		getArmorProficiencies().add(ArmorType.SCALE);
		getArmorProficiencies().add(ArmorType.LIGHT_SHIELD);
		getArmorProficiencies().add(ArmorType.HEAVY_SHIELD);
	}

	@Override
	protected void setWeaponProficiencies() {
		getWeaponProficiencies().add(WeaponType.SIMPLE_MELEE);
		getWeaponProficiencies().add(WeaponType.MILITARY_MELEE);
		getWeaponProficiencies().add(WeaponType.SIMPLE_RANGED);
		getWeaponProficiencies().add(WeaponType.MILITARY_RANGED);
	}
}
