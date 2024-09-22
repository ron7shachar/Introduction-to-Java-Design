
public class Unicorn extends Creature{

	public Unicorn(Slot loc) {
		this.location = loc;
	}
	
	@Override
	public char getCreatureChar() {
		return 'U';
	}

	@Override
	protected boolean emptySlotIsAvailable(Slot empty, PlayBoard pb) {  // if there is an available move
		return (isTheEmptyAdjacent(empty, 2) || isTheEmptySlant(empty, 1));
	}
}