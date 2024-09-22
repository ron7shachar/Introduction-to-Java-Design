import java.util.Date;

public class Student implements Runnable {

	private int id;
	private String name;
	private int classNumber;
	private double p;
	private double x;
	private int worksGrades[] =  new int[4];
	private Queue<Student> proctorsQueue;
	private InformationSystem informationSystem;
	public BoundedQueue<Test> studentTest = new BoundedQueue<Test>(1);
	
	public Student (int id, String name ,int classNumber, double level, double rate, int[] worksGrades, Queue<Student> proctorsQueue, InformationSystem informationSystem) {
		this.id = id;
		this.name = name;
		this.classNumber = classNumber;
		this.p = level;
		this.x = rate;
		this.worksGrades = worksGrades;
		this.proctorsQueue = proctorsQueue;
		this.informationSystem = informationSystem;
	} // constructor
	
	@Override
	public void run() {
		Test myTest = new Test(id, new Date()); // new test
		try {
			boolean[] answers = solveTheTest(); 
			myTest.setAnswers(answers);
			studentTest.insert(myTest);
			this.proctorsQueue.insert(this); // go to proctors
			myTest = this.informationSystem.find(id); // try to get the cheaked test
			if (myTest != null) {
			myTest.status = Test.Status.ReadByTheStudent;
			studentTest.insert(myTest);
			}
		} catch (InterruptedException e) {}
	}
	
	public int getClassNumber() {
		return classNumber;
	}
	
	private boolean[] solveTheTest() throws InterruptedException { // simulate the random answers by the probability
		boolean[] answers = new boolean[20];
		double time = x*1000.0;
		for (int i = 0; i<20; i++) {			
			Thread.sleep((long) time);
			if (Math.random() <= p) {
				answers[i] = true;
			}
		}
		return answers;
	}

}
