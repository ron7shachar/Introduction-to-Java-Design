import Turtle.Turtle;

public class DrunkTurtle extends Turtle {

	public DrunkTurtle() { // constructor
		super();
	}

	public void moveForward(double distance) { // advance Drunk forward
		super.moveForward(distance * random());
		double random = random(); // random number in [0,1)
		if (random <= 0.25) { // in 25% chance turn Left
			super.turnLeft(50);
		}
		if (random >= 0.5) { // in 50% chance go backward random(distance)
			super.moveForward(-distance * random());
		} else {
			super.moveForward(distance * random());
		}
	}

	public void turnLeft(int degrees) { // turn Drunk left
		super.turnLeft((int) (degrees * 2 * random()));
	}

	public void turnRight(int degrees) { // turn Drunk right
		super.turnRight((int) (degrees * 2 * random()));
	}

	public void moveSachy(double distance) { // make the drunk Turtle move as simple Turtle
		super.moveForward(distance);
	}

	public void turnLeftSachy(int degrees) { // turn Drunk left
		super.turnLeft(degrees);
	}

	public void turnRightSachy(int degrees) { // turn Drunk right
		super.turnRight(degrees);
	}

	protected double random() { // give random number in [0,1)
		return Math.random();
	}
}
