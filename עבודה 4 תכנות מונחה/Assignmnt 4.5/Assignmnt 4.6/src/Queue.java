import java.util.Vector;

public class Queue<E> {
	protected Vector<E> queue;
	
	public Queue() {
	queue = new Vector<E>();
	}
	
	public synchronized boolean isEmpty(){
		return (queue.isEmpty());
	}
	
	public synchronized boolean insert (E nom) throws InterruptedException { // add element
		queue.add(nom);
		this.notifyAll();
		return true;
	}
	
	public synchronized E extract () throws InterruptedException { // remove element if exist
		while (this.isEmpty()) {
			this.wait();
		}
		if (GUI.end) {return null;} // suitable to the try to kill the threads
		return queue.remove(0);
	}

	public synchronized int size() {
		return queue.size();
	}
	
}