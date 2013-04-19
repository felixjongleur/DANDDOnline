package objects.dndcharacter.classes;


public class DefaultClass extends DnDClass {

	@Override
	public int getHPModifier() {
		return 5;
	}

	@Override
	public Role getRole() {
		return null;
	}

	@Override
	public ClassType getClassType() {
		return ClassType.DEFAULT;
	}

	@Override
	public int getStartHP(int constitution) {
		return 0;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public PowerSource getPowerSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setSkillToAbilityMap() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setKeyAbilities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setArmorProficiencies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setWeaponProficiencies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDailyHealingSurges(int constitution) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinNumberOfTrainedSkills() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClassTraits() {
		// TODO Auto-generated method stub
		
	}
}
