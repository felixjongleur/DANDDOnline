package combat;

import objects.dndcharacter.DnDCharacter;

public class CombatSimulator {

	public int fight(DnDCharacter attacker, DnDCharacter opponent, int attackRoll) {
		int damageDone = 0;
		// A natural one on an attack roll is always a miss
		if(attackRoll == 1) {
			return damageDone;
		}
		if(attackRoll == 20 || isAttackSuccessful(getModifiedAttackRoll(attacker, attackRoll), opponent)){			
			// A "Natural" Roll of 20 is a Critical Hit
			if(attackRoll == 20) {
				damageDone += getModifiedDamage(attacker, 2, true);
			} else {
				damageDone += getModifiedDamage(attacker, 1, false);
			}
			attacker.addExperience(10);
		}
		opponent.decrementHP(damageDone);
		return damageDone;
	}

	private boolean isAttackSuccessful(int attackRoll, DnDCharacter opposingCharacter) {
		return false;
	}

	public int getModifiedDamage(DnDCharacter attacker, int damage, boolean crit) {
		damage += attacker.getAbilities().getModifier(attacker.getAbilities().getStrength());
//		damage *= crit ? attacker.getClassType().getCritMultiplier() : 1;
		return Math.max(1, damage);
	}

	public int getModifiedAttackRoll(DnDCharacter attacker, int attackRoll) {
		int modifiedAttackRoll = attackRoll + attacker.getAbilities().getModifier(attacker.getAbilities().getStrength());
		
		if(modifiedAttackRoll > 0) {
			modifiedAttackRoll = Math.min(modifiedAttackRoll, 20);
		} else {
			modifiedAttackRoll = 0;
		}
		
//		modifiedAttackRoll += attacker.getClassType().getLevelBonusToRoll(attacker.getLevel());
		
		return modifiedAttackRoll;
	}
}
