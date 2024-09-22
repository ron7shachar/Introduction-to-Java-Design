public class BoundedQueue<E> extends Queue<E> {
	int size;  //  the max size of the Queue

	public BoundedQueue(int size) {
		super();
		this.size = size;
	}
		
	public synchronized boolean insert (E nom) throws InterruptedException{
		while (queue.size() == size) {
			this.wait();
		}
		return this.insert(nom);
	}
	
	public synchronized E extract() throws InterruptedException {
		return this.extract();
	}
	
}