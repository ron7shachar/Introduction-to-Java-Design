import Turtle.*;

class TwoTurtles {

	public static void main(String[] args) {
		System.out.println("good morning world");
		Turtle romeo = new Turtle();
		Turtle juliet = new Turtle();
		romeo.show();
		juliet.show();
		romeo.tailDown();
		juliet.tailDown();
		drowHalfHeart(romeo, "Right");
		drowHalfHeart(juliet, "Left");
	}
	
	public static void drowHalfHeart(Turtle t, String side) {
		if (side == "Right") {
			t.turnRight(45);
			t.moveForward(50);
			t.turnRight(90);
			t.moveForward(90);
			t.turnRight(90);
		} else {
			t.turnLeft(45);
			t.moveForward(50);
			t.turnLeft(90);
			t.moveForward(90);
			t.turnLeft(90);
		}
		t.moveForward(140);
	}	
}