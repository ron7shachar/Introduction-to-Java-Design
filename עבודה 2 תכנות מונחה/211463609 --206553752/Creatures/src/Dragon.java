
public class Dragon extends Creature{

	public Dragon(Slot loc) {
		this.location = loc;
	}

	@Override
	public char getCreatureChar() {
		return 'D';
	}

	@Override
	protected boolean emptySlotIsAvailable(Slot empty, PlayBoard pb) { // if there is an available move
		if (isBigFootNearTheEmpty(empty,pb)) {
			return (isTheEmptyAdjacent(empty, pb.size-1) || isTheEmptySlant(empty, pb.size-1));
		} else {
			return (isTheEmptyAdjacent(empty, pb.size-1));
		}
	}
	
	private boolean isBigFootNearTheEmpty(Slot empty, PlayBoard pb) { // if in some of the near slots there is a Bigfoot
		Slot right = rightSlot(empty);
		if (right != null) { if(pb.getCratureChar(right) == 'B') {return true;}} ;
		Slot left = rightSlot(empty);
		if (left != null) { if(pb.getCratureChar(left) == 'B') {return true;}} ;
		Slot up = rightSlot(empty);
		if (up != null) { if(pb.getCratureChar(up) == 'B') {return true;}} ;
		Slot down = downSlot(empty);
		if (down != null) { if(pb.getCratureChar(down) == 'B') {return true;}} ;
		return false;
	}
}