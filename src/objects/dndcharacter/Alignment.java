package objects.dndcharacter;



public class Alignment {

	public enum AlignmentStance { GOOD, LAWFUL_GOOD, EVIL, CHAOTIC_EVIL, UNALIGNED };
	
	private AlignmentStance alignment;
	
	public Alignment() {
		
	}
	
	public Alignment(AlignmentStance alignment) {
		this.alignment = alignment;
	}

	public AlignmentStance getAlignment() {
		return alignment;
	}
}
