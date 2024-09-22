
import java.util.Scanner;

public class Game {
	private static int size = 4; // the size of the board
	private static PlayBoard playBoard = new PlayBoard(size);// pointer to the board
	private static int maxSteps; // the max Steps in a game
	private static Scanner scan = new Scanner(System.in); // to get input

	public static void main(String[] args) {
		reset();
		play();
		System.out.println(4);
	}

	public static void reset() { // reset the Game
		playBoard.resetTheBoard();
		askMaxSteps();
	}

	public static void play() { // "Connects the user to the game"
		for (int i = 1; i <= maxSteps; i += 1) {
			System.out.println(playBoard);  // print the board
			ChooseMove();
			if (finish()) { // if finish print "Congratulations" and and the game
				System.out.println("Congratulations! the mystical creatures found their place!");
				return;
			}
		}
		System.out.println("The mystical creatures did not find their place!"); // else  gave up
	}

	private static void askMaxSteps() { // ask for the Number Of Moves
		int sc; // answer
		do {
			System.out.print("Number Of Moves:"); // ask for the Number Of Moves
			sc = scan.nextInt(); 

		} while (sc < 1); // If the enhancer is unnatural ask again
		maxSteps = sc;

	}

	public static void ChooseMove() { // ask for the move
		int sc;
		System.out.println("Please insert the cell that contains the creature you want to move (row, then column):"); // שאלה
		do {

			sc = scan.nextInt();
		} while (!playBoard.moveFrom(new Slot(sc % 10, sc / 10))); // Move the creature if it does not move. Ask again
	}

	public static boolean finish() { // Checks if the game is Completed
		Slot P1 ;
		for (int i = 0; i < Math.pow(size, 2); i += 1) { // check all the slot on  board 
			P1 = new Slot((i) % size, (i) / size);
			char name = playBoard.getCratureChar(P1);
			for (Slot P2 = new Slot((P1.x + 1) % size, P1.y); P2.x != P1.x; P2.x = (P2.x+1)%size) { // check the row
				if (playBoard.getCratureChar(P2) == name) { 
					return false;
				}
			}
			for (Slot P2 = new Slot(P1.x, (P1.y + 1) % size); P2.y != P1.y; P2.y = (P2.y+1)%size) { // check the column
				if (playBoard.getCratureChar(P2) == name) {
					return false;
				}
			}
		}
		return true;
	}

}