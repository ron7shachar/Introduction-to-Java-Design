import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class InformationSystem {
	HashMap<Integer,Test> informationSystem;

	public InformationSystem() { // constructor
		informationSystem = new HashMap<Integer,Test>();
	}
	
	public synchronized boolean isEmpty(){ // return is the system Empty
		return informationSystem.isEmpty();
	}
	
	public synchronized boolean insert(Test nom) { // add
		informationSystem.put(nom.getId(), nom);
		this.notifyAll();
		return true;
	}

	public synchronized Test find (int id) throws InterruptedException { // search for a test with given id
		while (this.isEmpty() || !informationSystem.containsKey(id)) { 
			Thread.sleep(2000);
			this.wait();
		}
		if (GUI.end) {return null;}
		Thread.sleep(2000);
		return informationSystem.get(id);
	}
}