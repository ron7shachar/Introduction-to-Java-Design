
public class Proctor implements Runnable {

	private String name;
	private int age;
	private static int nStudents;
	private static int studentsCounter = 0;
	
	private Queue<Student> proctorQueue;
	private Queue<Test> firstTAQueue;
	private Queue<Test> secondTAQueue;
	
	public Proctor(Queue<Student> proctorQueue, Queue<Test> firstTAQueue, Queue<Test> secondTAQueue) {
		this.proctorQueue = proctorQueue;
		this.firstTAQueue = firstTAQueue;
		this.secondTAQueue = secondTAQueue;
	}
	
	@Override
	public void run() {
		while (studentsCounter < nStudents) {
			Student student;
		  	try {
		  		student = proctorQueue.extract();
		  		studentsCounter ++;
		  		Test test;
		  		test = student.studentTest.extract();
		  		test.setClassNumber(student.getClassNumber());
		  		test.status = Test.Status.signedByProctor;
		  		
		  		long workMiliSeconds = (long) (Math.random() * (3000-1000) + 1000);
		  		Thread.sleep(workMiliSeconds);
		  		
		  		if (firstTAQueue.size() <= secondTAQueue.size()) {
		  			firstTAQueue.insert(test);
		  		} else {
		  			secondTAQueue.insert(test);
		  		}
		  	} catch (InterruptedException e) {}	  				
		}
	}
	
}
