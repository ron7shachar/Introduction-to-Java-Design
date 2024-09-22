public class BoundedQueue<E> extends Queue<E> {
	int size;  //  the max size of the Queue

	public BoundedQueue(int size) { // constructor
		super();
		this.size = size;
	}
		
	public synchronized boolean insert (E nom) throws InterruptedException{ // add new element
		while (queue.size() == size) {
			this.wait();
		}
		return super.insert(nom);
	}
	
	public synchronized E extract() throws InterruptedException { // remove element
		E element = super.extract();
		this.notifyAll();
		return element;
	}
	
}