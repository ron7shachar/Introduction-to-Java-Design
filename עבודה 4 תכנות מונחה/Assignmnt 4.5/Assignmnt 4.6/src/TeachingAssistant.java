
public class TeachingAssistant implements Runnable, Profitable {
	
	private Queue<Test> myTeachingAssistantQueue;
	private Queue<Test> secondTAQueue;
	private Queue<Test> lecturerQueue;
	private static boolean[] answers = randomAnswers();
	private double p_error;
	private int salaryPerSecond = 3;
	private double totalSalary = 0;
	
	public TeachingAssistant(double p_error, Queue<Test> myTeachingAssistantQueue, Queue<Test> secondTAQueue, Queue<Test> lecturerQueue) { // constructor
		this.myTeachingAssistantQueue = myTeachingAssistantQueue;
		this.secondTAQueue = secondTAQueue;
		this.lecturerQueue = lecturerQueue;
		this.p_error = p_error;
	}

	@Override
	public void run() {
		while (!GUI.getEnd()) { // while not end massage from the lecturer
			Test test;
		  	try {
				//Print.print("befor extract TA");
		  		test = myTeachingAssistantQueue.extract();
		  		if (test == null) {break;}
				//Print.print("after extract TA");
		  		
	  			long workMiliSeconds = (long) (Math.random() * (2500-1500) + 1500); // count the salary
	  			totalSalary = totalSalary + (workMiliSeconds/1000)*salaryPerSecond;
	  			Thread.sleep(workMiliSeconds);
	  			
		  		if (test.status == Test.Status.signedByProctor) { // cheak the test first time
		  			test.scoreWithoutFactor = checkTest(p_error, test);
		  			test.status = Test.Status.tested1;
		  			secondTAQueue.insert(test);
		  		} else {
		  			test.scoreWithoutFactor = checkTest(p_error/2, test); // cheak the test second time
		  			test.status = Test.Status.tested2;
		  			lecturerQueue.insert(test);
		  		}
		  	} catch (InterruptedException e) {}
		}	
	}
	
	private static boolean[] randomAnswers() { // create the right answers
		boolean[] answers = new boolean[20];
		for (boolean ans : answers) {
			if (Math.random() <= 0.5) {
				ans = true;
			}
		}
		return null;
	}
	
	private double checkTest(double pError, Test t) { // check the test with probability to wrong
		int trueCounter = 0;
		for (boolean b : t.getAnswers()) {
			if (b || Math.random() <= pError) {
				trueCounter ++;
			}
		}
		return (trueCounter * 5);
	}

	@Override
	public double profit() {
		return totalSalary;
	}

}
