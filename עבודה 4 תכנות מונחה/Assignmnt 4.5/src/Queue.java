import java.util.Vector;

public class Queue<E> {
	protected Vector<E> queue;
	
	public Queue() {
	queue = new Vector<E>();
	}
	
	public synchronized boolean isEmpty(){
		return (queue.isEmpty());
	}
	
	public synchronized boolean insert (E nom) throws InterruptedException {
		queue.add(nom);
		this.notifyAll();
		return true;
	}
	
	public synchronized E extract () throws InterruptedException {
		while (this.isEmpty()) {
			this.wait();
		}
		return queue.remove(0);
	}

	public synchronized int size() {       //////  I use it in proctor
		return queue.size();
	}
	
}