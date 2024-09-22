public class PlayBoard {

	private Creature[][] board; // the board
	int size; // the size of the board

	public PlayBoard(int size) {
		this.size = size;
		this.board = new Creature[size][size];
	}

	public String toString() { // print the board

		String stringBord = "";
		for (int y = 0; y < size; y += 1) {
			for (int x = 0; x < size; x += 1) {
				if (board[x][y] == null) {
					stringBord = stringBord.concat("  "); // space in the null
				} else {
					stringBord = stringBord.concat(this.board[x][y].getCreatureChar() + " "); // the char of the
																								// creature

				}
			}
			stringBord = stringBord.concat("\n"); // next
		}
		return (stringBord);
	}

	public boolean moveFrom(Slot from) { // move the Creatures
		Slot emptySlot = emptySlot();
		if (inTheRange(from)) {

			if (board[from.x][from.y].moveTo(emptySlot, this)) {
				board[emptySlot.x][emptySlot.y] = board[from.x][from.y];
				board[from.x][from.y] = null;

				return true;
			}
		}
		System.out.println("This creature cannot move to the empty cell. Please try again:");
		return false;
	}

	public void resetTheBoard() { // Put all the creatures on the board
		Slot[] randomSlots = randomSlots();
		int nCreatures = (int) Math.pow(size, 2) - 1;
		int random = (int) (Math.random() * 4) + 1;
		for (int i = 0; i < nCreatures; i++) {
			switch ((i + random) % 4) {
			case 0: {
				board[randomSlots[i].x][randomSlots[i].y] = new Unicorn(randomSlots[i]);
				break;
			}
			case 1: {
				board[randomSlots[i].x][randomSlots[i].y] = new Bigfoot(randomSlots[i]);
				break;
			}

			case 2: {
				board[randomSlots[i].x][randomSlots[i].y] = new Fenix(randomSlots[i]);
				break;
			}
			case 3: {
				board[randomSlots[i].x][randomSlots[i].y] = new Dragon(randomSlots[i]);
				break;
			}
			}
		}
	}

	public char getCratureChar(Slot P) { // return the first char of Creature
		if (this.board[P.x][P.y] == null) {
			return (' ');
		}
		return this.board[P.x][P.y].getCreatureChar();
	}

	public Slot emptySlot() { // Finds the empty slot
		for (int i = 0; i < board.length; i += 1) {
			for (int j = 0; j < board.length; j += 1) {
				if (board[i][j] == null) {
					return (new Slot(i, j));
				}
			}
		}
		return (new Slot(-1, -1));
	}

	private Slot[] randomSlots() { // Returns a random array of all slots on the board
		Slot[] slots = new Slot[(int) Math.pow(size, 2) - 1];
		int[][] board = new int[size][size];
		int plac;
		for (int i = 1; i < Math.pow(size, 2); i += 1) {
			plac = (int) (Math.random() * (Math.pow(size, 2)));
			for (int j = 1; j < Math.pow(size, 2); j += 1) {
				if (board[(plac % size)][(plac / size)] == 0) {
					board[(plac % size)][(plac / size)] = i;
					slots[i - 1] = new Slot(plac % size, plac / size);

					break;
				}
				plac = (plac + 1) % (int) (Math.pow(size, 2)); // אם לא תעבור לתא הבא,מהאחרון עוברים לראשות
			}
		}
		return (slots);

	}

	private boolean inTheRange(Slot from) { // Checks the slot on the board
		if (board[from.x][from.y] == null) {
			return false;
		}
		if (from.x > board.length || from.x < 0) {
			return false;
		}
		if (from.y > board.length || from.y < 0) {
			return false;
		}
		return true;
	}
}