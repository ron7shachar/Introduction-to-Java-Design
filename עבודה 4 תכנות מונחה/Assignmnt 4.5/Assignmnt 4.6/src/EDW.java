
public class EDW implements Runnable {
	
	static int totalNumberOfTests;
	private static int testsCounter = 0;
	private BoundedQueue<Test> EDW_Queue;
	private InformationSystem informationSystem;
	private String name;
	
	public EDW (String name, BoundedQueue<Test> EDW_Queue, InformationSystem informationSystem) { // constructor
		this.name = name;
		this.EDW_Queue = EDW_Queue;
		this.informationSystem = informationSystem;
	}
	
	@Override
	public void run() {
		while (testsCounter < totalNumberOfTests) { // there are more tests
			Test test;
			try {
				//Print.print("befor extract EDW");
				test = EDW_Queue.extract();
				//Print.print("after extract EDW");
				if (test == null) {break;}
				test.status = Test.Status.scanned;
				Thread.sleep(4000);
				this.informationSystem.insert(test);
				testsCounter ++;
				Print.print("Exam Scanned for " + test.getId());
			} catch (InterruptedException e) {}
		}
		if (!GUI.getEnd()) {Lecturer.finishingScans();} // send massage to the lecturer about finish
	}

}