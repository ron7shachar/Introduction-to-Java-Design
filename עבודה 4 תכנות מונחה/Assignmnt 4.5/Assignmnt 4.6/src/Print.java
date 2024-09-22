
public class Print extends Thread {
	
	public synchronized static void print(String s) { // syncronized print
		System.out.println(s);
	}

}
