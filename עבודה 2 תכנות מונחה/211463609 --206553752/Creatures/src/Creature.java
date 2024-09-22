
public abstract class Creature {
	
	protected Slot location = new Slot(0,0);
	protected int x = location.x;
	protected int y = location.y;

	protected Creature() { // constructor
		super();
	}
	
	abstract public char getCreatureChar(); // Abstract get Creature Char

	public boolean moveTo(Slot emptySlot, PlayBoard pb) { // Abstract move to the empty slot if possible
		if (emptySlotIsAvailable(emptySlot, pb)) {
            location = emptySlot;
            return true;
		}
		return false;
	}
	
	protected abstract boolean emptySlotIsAvailable(Slot empty, PlayBoard pb); // check if move to empty slot is available
	
	protected boolean isTheEmptyAdjacent(Slot empty, int steps) { // if the empty slot adjacent to the current creature direct in given steps
		return (isLeftSlotEmpty(empty, steps) || isRightSlotEmpty(empty, steps) || isUpSlotEmpty(empty, steps) || isDownSlotEmpty(empty, steps));
		}
	
	protected boolean isTheEmptySlant(Slot empty, int steps) { // if the empty slot adjacent to the current creature slant in given steps
		return (isLeftUpSlotEmpty(empty, steps) || isLeftDownSlotEmpty(empty, steps) || isRightUpSlotEmpty(empty, steps) || isRightDownSlotEmpty(empty, steps));
		}

	protected boolean isLeftUpSlotEmpty(Slot empty, int steps) { // if the empty slot adjacent to the current creature Left-Up slant in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!beginOfRow(currentSlot) && !beginOfCulomn(currentSlot)) {
				nextSlot = upSlot(leftSlot(currentSlot));
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
	}

	protected boolean isLeftDownSlotEmpty(Slot empty, int steps) { // if the empty slot adjacent to the current creature Left-down slant in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!beginOfRow(currentSlot) && !endOfCulomn(currentSlot)) {
				nextSlot = downSlot(leftSlot(currentSlot));
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
	}
	
	protected boolean isRightUpSlotEmpty(Slot empty, int steps) { // if the empty slot adjacent to the current creature right-Up slant in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!endOfRow(currentSlot) && !beginOfCulomn(currentSlot)) {
				nextSlot = upSlot(rightSlot(currentSlot));
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
	}

	protected boolean isRightDownSlotEmpty(Slot empty, int steps) { // if the empty slot adjacent to the current creature right-Down slant in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!endOfRow(currentSlot) && !endOfCulomn(currentSlot)) {
				nextSlot = downSlot(rightSlot(currentSlot));
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
	}

	protected boolean isLeftSlotEmpty(Slot empty, int steps){ // if the empty slot adjacent to the current creature direct left in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!beginOfRow(currentSlot)) {
				nextSlot = leftSlot(currentSlot);
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}				
		}
		return false;
    }

	protected boolean isRightSlotEmpty(Slot empty, int steps){ // if the empty slot adjacent to the current creature direct right in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!endOfRow(currentSlot)) {
				nextSlot = rightSlot(currentSlot);
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
    }

	protected boolean isUpSlotEmpty(Slot empty, int steps){ // if the empty slot adjacent to the current creature direct up in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!beginOfCulomn(currentSlot)) {
				nextSlot = upSlot(currentSlot);
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
    }

	protected boolean isDownSlotEmpty(Slot empty, int steps){ // if the empty slot adjacent to the current creature direct down in given steps
		Slot currentSlot = location;
		Slot nextSlot;
		for (int i = 0; i < steps; i++) {
			if(!endOfCulomn(currentSlot)) {
				nextSlot = downSlot(currentSlot);
				if (nextSlot.compareTo(empty) == 0) {
					return true;
				}
				currentSlot = nextSlot;
			}
		}
		return false;
    }

	protected boolean beginOfRow(Slot s){ // is the given slot in the begin of the row in the board
        if (s.y == 0){
            return true;
        }
        return false;
    }

	protected boolean endOfRow(Slot s){ // is the given slot in the end of the row in the board
        if (s.y == 4-1){
            return true;
        }
        return false;
    }
	
	protected boolean beginOfCulomn(Slot s){ // is the given slot in the begin of the row in the board
        if (s.x == 0){
            return true;
        }
        return false;
    }

	protected boolean endOfCulomn(Slot s){ // is the given slot in the end of the row in the board
        if (s.x == 4-1){
            return true;
        }
        return false;
    }
	
	protected Slot leftSlot(Slot s) { // return the slot which left to the given slot
		if (beginOfRow(s)) { // so there is no left slot
            return null;
        }
        else {
            return new Slot(s.x, s.y-1); // return the left slot
        }
	}
	
	protected Slot rightSlot(Slot s) { // return the slot which right to the given slot
		if (endOfRow(s)) { // so there is no right slot
            return null;
        }
        else {
            return new Slot(s.x, s.y+1); // return the right slot
        }
	}
	
	protected Slot upSlot(Slot s){ // return the slot which up to the given slot
        if (s.x == 0) { // so there is no up slot
            return null;
        }
        else {
            return new Slot(s.x-1, s.y); // return the slot above
        }
    }

	protected Slot downSlot(Slot s){ // return the slot which down to the given slot
        if (s.x == 4-1) { // so there is no down slot
            return null;
        }
        else {
            return new Slot(s.x+1, s.y); // return the slot under
        }
    }
}