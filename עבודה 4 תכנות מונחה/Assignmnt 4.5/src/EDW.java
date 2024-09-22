
public class EDW implements Runnable {
	
	private static int totalNumberOfTests;
	private static int testsCounter = 0;
	private BoundedQueue<Test> EDW_Queue;
	private InformationSystem informationSystem;
	private String name;
	
	public  EDW (String name, BoundedQueue<Test> EDW_Queue, InformationSystem informationSystem) {
		this.name = name;
		this.EDW_Queue = EDW_Queue;
		this.informationSystem = informationSystem;
	}
	
	@Override
	public void run() {
		while (testsCounter < totalNumberOfTests) {
			Test test;
			try {
				test = EDW_Queue.extract();
				test.status = Test.Status.scanned;
				Thread.sleep(4000);
				this.informationSystem.insert(test);
				Print.print("Exam Scanned for " + test.getId());
			} catch (InterruptedException e) {}
		}
		Lecturer.finishingScans();
	}

}