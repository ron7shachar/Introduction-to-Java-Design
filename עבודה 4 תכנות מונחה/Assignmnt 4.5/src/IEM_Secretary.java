
public class IEM_Secretary implements Runnable {
	
	private Queue<Test> iemSecretaryQueue;
	private BoundedQueue<Test> EDW_Queue;
	private String name;
	private boolean only70plas;
	
	
	public IEM_Secretary(String name1 , boolean only70plas, Queue<Test> iemSecretaryQueue, BoundedQueue<Test> EDW_Queue ) {
	this.name = name;	
	this.only70plas = only70plas;
	this.iemSecretaryQueue = iemSecretaryQueue;
	this.EDW_Queue = EDW_Queue;
	}
	
	@Override
	public void run() {
		while (!GUI.getEnd()) {
			try {
				Test test = iemSecretaryQueue.extract();
				if (only70plas && test.scoreAfterFactor<=70) {
					iemSecretaryQueue.insert(test);	
				} else {
					sleep();
					test.status = Test.Status.EnteredIntoTheSystems;
					EDW_Queue.insert(test);
				}		
			} catch (InterruptedException e) {}
		}
	}
	
	private void sleep() {
		try {
			if (only70plas) {
				Thread.sleep(2000);
			} else {
			Thread.sleep(3000);
			}
		} catch (InterruptedException e) {}
	}

}