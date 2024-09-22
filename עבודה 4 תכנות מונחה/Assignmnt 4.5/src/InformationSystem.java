import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class InformationSystem {
	HashMap<Integer,Test> informationSystem;

	public InformationSystem() {
		informationSystem = new HashMap<Integer,Test>();
	}
	
	public synchronized boolean isEmpty(){
		return informationSystem.isEmpty();
	}
	
	public synchronized boolean insert(Test nom) {
		informationSystem.put(nom.getId(), nom);
		this.notifyAll();
		return true;
	}

	public synchronized Test find (int id) throws InterruptedException {
		while (this.isEmpty() || !informationSystem.containsKey(id)) {
			Thread.sleep(2000);
			this.wait();
		}
		Thread.sleep(2000);
		return informationSystem.get(id);
	}
}