import Turtle.*;

public class SmartTurtle extends Turtle {

	public SmartTurtle() { // constructor
		super();

	}

	public void draw(int sides, double size) { // draw a polygon in the given sides and size
		if (validDrawInput(sides, size)) {
			drawing(sides, size, 360 / sides);
		}

	}

	protected void drawing(int sides, double size, int angle) {// drawing in the given sides and size

		this.tailDown();
		for (int i = 1; i <= sides; i += 1) { // draw the lines
			this.turnRight(angle);
			this.moveForward(size);

		}
		this.tailUp();
	}

	protected static boolean validDrawInput(int sides, double size) { // check if the draw input is valid
		if (sides >= 3) {
			if (size > 0) {
				if (360 % sides == 0) {
					return true;
				}
			}
		}
		return false;
	}

}
