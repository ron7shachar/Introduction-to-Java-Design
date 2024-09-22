import java.util.Scanner;
import Turtle.*;

class Army {

	static Scanner myObj = new Scanner(System.in);

	public static void main(String[] args) {
		ptintMenu();
		int[] quantitiesPerType = getTurtlesQuantities();
		Turtle[] turtlesArmy = creatSoliders(quantitiesPerType);
		tailUpAllTurtles(turtlesArmy);
		showAllTurtles(turtlesArmy);
		lineAllTurtles(turtlesArmy);
		tailDownAllTurtles(turtlesArmy);
		goDirectAllTurtles(turtlesArmy, 35);
		turnLeftAllTurtles(turtlesArmy, 20);
		goDirectAllTurtles(turtlesArmy, 55);
		drawHexagonAllTurtles(turtlesArmy, 50);
		hideAllTurtles(turtlesArmy);
	}

	public static void ptintMenu() {
		System.out.println("Choose the type of the turtle:\r\n" + "1.	Turtle\r\n" + "2.	Smart\r\n"
				+ "3.	Drunk\r\n" + "4.	Jumpy\r\n" + "5.	Grumpy\r\n" + "6.	Hangover\r\n" + "");
	}

	public static int[] getTurtlesQuantities() {
		int[] quantities = new int[7];
		int nSoliders = 0;
		for (int i = 0; i < 6; i++) {
			int quantity = Integer.parseInt(myObj.nextLine());
			quantities[i] = quantity;
			nSoliders += quantity;
		}
		quantities[6] = nSoliders;
		return quantities;
	}

	public static Turtle[] creatSoliders(int[] quantities) {
		Turtle[] turtlesArmy = new Turtle[quantities[6]];
		int turtlesArmyIndex = 0;
		for (int i = 0; i < 6; i++) { // go over the quantities
			for (int j = 0; j < quantities[i]; j++) { // create the current type turtle several times according to the
														// quantity
				Turtle t;
				t = createTurtleByType(i + 1);
				turtlesArmy[turtlesArmyIndex] = t; // create new turtle and add to the army
				turtlesArmyIndex = turtlesArmyIndex + 1;
			}
		}
		return turtlesArmy;
	}

	public static Turtle createTurtleByType(int typeNumber) {
		switch (typeNumber) {
		case 1: {
			return new Turtle();
		}
		case 2: {
			return new SmartTurtle();
		}
		case 3: {
			return new DrunkTurtle();
		}
		case 4: {
			return new JumpyTurtle();
		}
		case 5: {
			return new GrumpyTurtle();
		}
		case 6: {
			return new HangoverTurtle();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + typeNumber);
		}
	}

	public static void showAllTurtles(Turtle[] turtlesArmy) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].show();
		}
	}

	public static void hideAllTurtles(Turtle[] turtlesArmy) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].hide();
		}
	}

	public static void tailDownAllTurtles(Turtle[] turtlesArmy) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].tailDown();
		}
	}

	public static void tailUpAllTurtles(Turtle[] turtlesArmy) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].tailUp();
		}
	}

	public static void lineAllTurtles(Turtle[] turtlesArmy) {
		int nTurtles = turtlesArmy.length;
		int distanceFromTheEnd = nTurtles * 120 / 2;
		for (int i = 0; i < turtlesArmy.length; i++) {
			if (turtlesArmy[i] instanceof DrunkTurtle || turtlesArmy[i] instanceof HangoverTurtle) {
				((DrunkTurtle) turtlesArmy[i]).turnLeftSachy(90);
				((DrunkTurtle) turtlesArmy[i]).moveSachy(distanceFromTheEnd - 120 * i);
				((DrunkTurtle) turtlesArmy[i]).turnRightSachy(90);
				((DrunkTurtle) turtlesArmy[i]).moveSachy(-200); // TODO: update the distance
			} else {
				turtlesArmy[i].turnLeft(90);
				turtlesArmy[i].moveForward(distanceFromTheEnd - 120 * i);
				turtlesArmy[i].turnRight(90);
				turtlesArmy[i].moveForward(-200); // TODO: update the distance

			}
		}

	}

	public static void goDirectAllTurtles(Turtle[] turtlesArmy, int distance) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].moveForward(distance);
		}
	}

	public static void turnLeftAllTurtles(Turtle[] turtlesArmy, int angle) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			turtlesArmy[i].turnLeft(angle);
		}
	}

	public static void drawHexagonAllTurtles(Turtle[] turtlesArmy, double size) {
		for (int i = 0; i < turtlesArmy.length; i++) {
			if (turtlesArmy[i] instanceof SmartTurtle) {
				((SmartTurtle) turtlesArmy[i]).draw(6, size); // TODO: update the method to draw an hexagon
			}
		}
	}

}
