
public class Proctor implements Runnable {

	private String name;
	private int age;
	static int nStudents;
	private static int studentsCounter = 0;
	
	private Queue<Student> proctorQueue;
	private Queue<Test> firstTAQueue;
	private Queue<Test> secondTAQueue;
	
	public Proctor(Queue<Student> proctorQueue, Queue<Test> firstTAQueue, Queue<Test> secondTAQueue) { // constructor
		this.proctorQueue = proctorQueue;
		this.firstTAQueue = firstTAQueue;
		this.secondTAQueue = secondTAQueue;
	}
	
	@Override
	public void run() {
		while (studentsCounter < nStudents) { // while not did all the students
			Student student;
		  	try {
		  		
				//Print.print("befor extract proctor - student");
		  		student = proctorQueue.extract();
		  		if (student == null) {break;}
				//Print.print("after extract proctor - student");

		  		studentsCounter ++;
		  		Test test;
				//Print.print("befor extract proctor - test");
		  		test = student.studentTest.extract(); // get the test from the student and treat it
		  		if (test == null) {break;}
				//Print.print("after extract proctor - test");
		  		test.setClassNumber(student.getClassNumber());
		  		test.status = Test.Status.signedByProctor;
		  		
		  		long workMiliSeconds = (long) (Math.random() * (3000-1000) + 1000); // count the work time
		  		Thread.sleep(workMiliSeconds);
		  		
		  		if (firstTAQueue.size() <= secondTAQueue.size()) { // continue to the suitable teacher assistant
		  			firstTAQueue.insert(test);
		  		} else {
		  			secondTAQueue.insert(test);
		  		}
		  	} catch (InterruptedException e) {}	  				
		}
	}
	
}
