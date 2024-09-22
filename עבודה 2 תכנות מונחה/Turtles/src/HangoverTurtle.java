
public class HangoverTurtle extends DrunkTurtle {

	public HangoverTurtle() { // constructor
		super();
	}

	public void moveForward(double distance) { // advance Hangover forward
		double random = random(); // random [0,1)
		if (random <= 0.25) {
			super.moveForward(distance); // advance Drunk forward
		} else if (random >= 0.8) {
			super.moveSachy(distance);// advance turtle forward
		}
	}

}
