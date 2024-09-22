
public class Fenix extends Creature{

	public Fenix(Slot loc) {
		this.location = loc;
	}

	@Override
	public char getCreatureChar() {
		return 'F';
	}

	@Override
	protected boolean emptySlotIsAvailable(Slot empty, PlayBoard pb) {  // if there is an available move
		return (isTheEmptyAdjacent(empty, pb.size-1));
	}
}