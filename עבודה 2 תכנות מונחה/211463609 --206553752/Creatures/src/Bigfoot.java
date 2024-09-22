
public class Bigfoot extends Creature{

	public Bigfoot(Slot loc) {
		this.location = loc;
	}
		
	@Override
	public char getCreatureChar() {
		return 'B';
	}

	@Override
	protected boolean emptySlotIsAvailable(Slot empty, PlayBoard pb) {
		return (isTheEmptyAdjacent(empty, 1)); // if the empty slot touches me direct
	}
}