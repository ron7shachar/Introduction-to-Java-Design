
public class GrumpyTurtle extends SmartTurtle {

	public GrumpyTurtle() { // constructor
		super();
	}

	public void draw(int sides, double size) { // draw a polygon in the given sides and size
		if (validDrawInput(sides, size)) {
			if (random() < 0.6) {
				this.drawing(sides - 1, size, 360 / sides); // drawing a polygon with n - 1 sides
				if (random() < 0.3) {
					scribble();
				}
			} else {
				this.drawing(sides, size, -360 / sides); // drawing opposite polygon
				if (random() < 0.2) {
					this.tailUp(); //
					this.turnLeft((int) (360 * random())); // run away
					this.moveForward(500); //
				}
			}

		}
	}

	private void scribble() { // draw a scribble
		this.drawing(5, 80, -150);

	}

	private double random() { // give random number in [0,1)
		return Math.random();
	}
}
