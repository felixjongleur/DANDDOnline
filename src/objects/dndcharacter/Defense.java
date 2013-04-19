package objects.dndcharacter;

import java.util.List;

import objects.armor.Armor;
import objects.armor.Armor.ArmorWeight;


public class Defense {
		
	public enum DefenseType { AC, FORTITUDE, REFLEX, WILL };
	
	public int getBaseDefense(int level) {
		return (int) (10 + Math.floor(level / 2));
	}
	
	public int getArmorClass(int level, List<Armor> armor, int dexterity, int intelligence) {
		int armorValue = Armor.getArmorValue(armor);
		if(armorValue == 0 || Armor.getHeaviestArmor(armor) == ArmorWeight.LIGHT) {
			armorValue += Math.max(Abilities.getModifier(dexterity), Abilities.getModifier(intelligence));
		}
		return getBaseDefense(level) + armorValue;
	}
	
	public int getFortitude(int level, int strength, int constitution) {
		return getBaseDefense(level) + Math.max(Abilities.getModifier(strength), Abilities.getModifier(constitution));
	}
	
	public int getReflex(int level, int dexterity, int intelligence, List<Armor> armor) {
		return getBaseDefense(level) + Math.max(Abilities.getModifier(dexterity), Abilities.getModifier(intelligence)) + Armor.getShieldBonus(armor);
	}
	
	public int getWill(int level, int wisdom, int charisma) {
		return getBaseDefense(level) + Math.max(Abilities.getModifier(wisdom), Abilities.getModifier(charisma));
	}
}
