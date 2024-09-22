
public class Print extends Thread {
	
	public synchronized static void print(String s) {
		System.out.println(s);
	}

}
