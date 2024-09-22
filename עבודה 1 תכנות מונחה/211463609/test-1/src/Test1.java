
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzles bord = new Puzzles(getBoardSize());	// יצירת לוח
		play(bord); 								// תחילת "משחק"

	}

	public static void play(Puzzles bord) {
		int number_Of_Moves = 0;					// סופר של מספר מהלכים
		int max_Moves = getMaxMoves(); 				// מספר מהלחים מקסימאלי
		while (!bord.puzzleOrdered()) {				// לרוץ בלולאה כל עוד הלוח לא פתור
			System.out.println(bord); 				// מדפיס את המצב הנוכחי של הלוח
			number_Of_Moves += 1;
			if (number_Of_Moves >= max_Moves + 1) {	// עאם הגעתי למספר המהלכים המקסימלי אז לבתר
				System.out.println("I Give Up!");
				return;
			}
			getNumber(bord);						// לקבל מספר להזזה מהמשתמש

		}
	}

	public static int getBoardSize() { 		// לקבל גודל לוח מהמשתמש
		Scanner scan = new Scanner(System.in);
		int sc; // תשובה
		do {
			System.out.print("Board Size:");// לשאול את המשתמש מה גודל הלוח
			sc = scan.nextInt();			// תשובה

		} while (!sizeInRange(sc));			// אם התשובה לא בטווח את לשאול עוד הפעם
		return (sc);
	}

	public static int getMaxMoves() { 				// לקבל מספר מהלחים מקסימלי מהמשתמש
		Scanner scan = new Scanner(System.in);
		int sc; // תשובה
		do {
			System.out.print("Number Of Moves:"); 	// לשאול את המשתמש מה מספר המהלכים המקסימאלי
			sc = scan.nextInt();					// תשובה

		} while (sc < 1);							// אם התשובה לא בטווח את לשאול עוד הפעם
		return (sc);

	}

	public static void getNumber(Puzzles bord) {	// לקבל מהמשתמש איזה מספר הוא רוצה להזיז
		Scanner scan = new Scanner(System.in);
		int sc;// תשובה
		do {
			System.out.print("Your move:");			// שאלה
			sc = scan.nextInt();// תשובה

		} while (!bord.moveNumber(sc));				// אם התשובה לא בטווח את לשאול עוד הפעם
	}

	public static boolean sizeInRange(int size) { 	// בודר אם הגודל בטווח ואם לא מדפיס שגיא
		if (size < 9 && size > 1) {
			return (true);
		}
		System.out.println("Invalid Size!");
		return (false);
	}

}
