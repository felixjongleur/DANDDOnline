package objects.armor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Armor {
	
	public enum ArmorWeight { LIGHT, HEAVY };
	public enum ArmorType { CLOTH, LEATHER, HIDE, CHAINMAIL, SCALE, PLATE, LIGHT_SHIELD, HEAVY_SHIELD };
	
	private static Map<ArmorType, ArmorWeight> weightToArmorType = new HashMap<ArmorType, ArmorWeight>();
	
	static {
		weightToArmorType.put(ArmorType.CLOTH, ArmorWeight.LIGHT);
		weightToArmorType.put(ArmorType.LEATHER, ArmorWeight.LIGHT);
		weightToArmorType.put(ArmorType.HIDE, ArmorWeight.LIGHT);

		weightToArmorType.put(ArmorType.CHAINMAIL, ArmorWeight.HEAVY);
		weightToArmorType.put(ArmorType.SCALE, ArmorWeight.HEAVY);
		weightToArmorType.put(ArmorType.PLATE, ArmorWeight.HEAVY);
	}
	
	public static ArmorWeight getArmorWeight(ArmorType type) {
		return weightToArmorType.get(type);
	}	
	
	public static int getArmorValue(List<Armor> armor) {
		int armorTotal = 0;
		for(Armor armorPiece : armor) {
			// TODO : Add armor value to armor
//			armorTotal += armorPiece.getArmorValue();
		}
		return armorTotal;
	}
	
	public static ArmorWeight getHeaviestArmor(List<Armor> armor) {
		for(Armor armorPiece : armor) {
			// TODO : Add type to armor
//			if(Armor.getArmorWeight(armorPiece.getType()) == ArmorWeight.HEAVY) {
//				return ArmorWeight.HEAVY;
//			}
		}
		return ArmorWeight.LIGHT;
	}
	
	public static int getShieldBonus(List<Armor> armor) {
		for(Armor armorPiece : armor) {
//			if(armorPiece.getArmorType() == ArmorType.LIGHT_SHIELD || armorPiece.getArmorType() == ArmorType.HEAVY_SHIELD) {
//				return armorPiece.getBonus();
//			}
		}
		return 0;
	}
}
