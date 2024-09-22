
public class Puzzles {
	public int[][] bord; 					// מארך דו מימדי שמיצג את התאים בלוח
	private int size; 						// מיגות הלוח

	public Puzzles(int size) {
		bord = new int[size][size];
		this.size = size;
		resetTheBord();
	}

	private void resetTheBord() { 								// מסדר את הלוח באופן רנדומלי
		int plac;
		for (int i = 1; i < Math.pow(size, 2); i += 1) { 		// בוחר מספר
			plac = randomPlace(); 								// מיקום תארנדומלי
			for (int j = 1; j < Math.pow(size, 2); j += 1) {    // מריץ את הלולאה כמספר התאים
				if (bord[(plac % size)][(plac / size)] == 0) { 	// אם התא רק אז תסים i
					bord[(plac % size)][(plac / size)] = i;
					break;
				}
				plac = (plac + 1) % (int) (Math.pow(size, 2));	// אם לא תעבור לתא הבא,מהאחרון עוברים לראשות
			}
		}
	}

	public String toString() { 							// מיצר string של הלוח מוכן להתפסה
		String stringBord = "";
		for (int y = 0; y < size; y += 1) {
			for (int x = 0; x < size; x += 1) {
				if (bord[x][y] == 0) { 					// לא להדפיס את 0
					stringBord = stringBord.concat("  ");
				} else {
					stringBord = stringBord.concat(bord[x][y] + " ");
				}
				if (bord[x][y] < 10) { 					// דועג לרווחים שווים
					stringBord = stringBord.concat(" ");
				}
			}
			stringBord = stringBord.concat("\n"); 		// בסוף כל שורה שיקפוצ לשורה הבא
		}
		return (stringBord);
	}

	public boolean puzzleOrdered() { 							// האם כל חלק במקום שלו
		for (int i = 0; i < Math.pow(size, 2) - 1; i += 1) {
			if (bord[(i % size)][(i / size)] != i + 1) {		// מספיק שאחד לא במקום אז תחזיר false
				return false;
			}
		}
		return true;
	}

	public boolean moveNumber(int number) {	// להזיז מספר
		if (nearEmpty(number)) { 			// הווזזה חוקית?
			moveingNumber(number); 			// ההזזה של המספר
			return true;
		}
		return (false);

	}

	private void moveingNumber(int number) { 	// ההזזה של המספר
		int xnum = numberToX_Y(number)[0];
		int ynum = numberToX_Y(number)[1];
		int x0 = numberToX_Y(0)[0];
		int y0 = numberToX_Y(0)[1];
		bord[x0][y0] = number; 					// בתא הרק שים את number
		bord[xnum][ynum] = 0; 					// "תמחק" את number מהתא הישן
	}					

	public int[] numberToX_Y(int number) { 					// get number and return the coordinates,[0]=x,[1]=y
		int[] x_y = new int[2];
		for (int i = 0; i < Math.pow(size, 2); i += 1) { 	// רץ על התאים עד למצאת התא של number
			if (bord[(i % size)][(i / size)] == number) {
				x_y[0] = i % size;
				x_y[1] = i / size;
				break;
			}

		}
		return x_y;
	}

	private int randomPlace() { // נותן מספר תא באופן ארקאי
		return (int) (Math.random() * (Math.pow(size, 2)));

	}

	private boolean nearEmpty(int number) { // האם התא נמצא לייד תא רק
		int xnum = numberToX_Y(number)[0];
		int ynum = numberToX_Y(number)[1];
		int x0 = numberToX_Y(0)[0];
		int y0 = numberToX_Y(0)[1];

		if (xnum == x0) {
			if (ynum + 1 == y0 || ynum - 1 == y0) {
				return true;
			}
		}
		if (ynum == y0) {
			if (xnum + 1 == x0 || xnum - 1 == x0) {
				return true;
			}
		}
		System.out.println("Invalid move!");
		return false;
	}

}
