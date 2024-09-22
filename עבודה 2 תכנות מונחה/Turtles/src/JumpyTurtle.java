
public class JumpyTurtle extends SmartTurtle {
	private static double Cycle = 5.0; // the length of one dotted line
	private boolean tailDown = false; // if the tail down it true else false

	public JumpyTurtle() { // constructor
		super();
		this.tailUp();
	}

	public void moveForward(double distance) { // advance forward in given distance
		int direction = 1; // the direction of the distance
		if (tailDown) {
			if (distance < 0) {// check the distance
				direction = -1;
			}
			for (int i = 1; i <= direction * distance / Cycle; i += 1) { // print the dotted line
				if (i % 2 != 0) {
					super.tailDown();
				} else {
					super.tailUp();
				}
				super.moveForward(direction * Cycle);
			}
		} else {
			super.moveForward(distance);
		}
	}

	public void tailDown() { // lower the tail
		tailDown = true;
	}

	public void tailUp() { // lift the tail
		tailDown = false;
		super.tailUp();
	}
}
