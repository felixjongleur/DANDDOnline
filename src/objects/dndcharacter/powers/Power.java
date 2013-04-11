package objects.dndcharacter.powers;

public abstract class Power {
	
	public enum PowerType { AT_WILL };
	
	public abstract PowerType getPowerType();
	public abstract String getName();
	public abstract String getDescription();
	
}
