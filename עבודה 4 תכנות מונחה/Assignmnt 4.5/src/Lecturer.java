import java.util.Vector;

public class Lecturer implements Runnable{
	
	private String name;
	public Queue<Test> lecturerQueue;
	public Queue<Test> exerciseCheckerQueue;
	private static Vector<Integer> BestStudents = new Vector<Integer>();
	private static int nStudents = 0;
	private static int totalGradesBeforeFactor = 0;
	private static int totalGradesAfterFactor = 0;
	private static boolean scanningOver;
	private static Vector<? extends Profitable> emplyees;
	
	public Lecturer(String name, Queue<Test> lecturerQueue, Queue<Test> exerciseCheckerQueue, Vector<? extends Profitable> eVector) {
		this.name = name;
		this.lecturerQueue = lecturerQueue;
		this.exerciseCheckerQueue = exerciseCheckerQueue;
		emplyees = eVector;
	}
	
	@Override
	public void run() {
		while (scanningOver) { // TODO there are no more tests
			Test test;
		  	try {
		  		Thread.sleep(1000);
		  		
		  		test = lecturerQueue.extract();
		  		nStudents ++;
		  		totalGradesBeforeFactor += test.scoreWithoutFactor;
		  		
		  		double gradeAfterFactor = giveFactor(test);
		  		test.scoreAfterFactor = gradeAfterFactor;
		  		totalGradesAfterFactor += gradeAfterFactor;
		  		
		  		if (gradeAfterFactor > 95) {
		  			BestStudents.add(test.getId());
		  		}
		  		
		  		test.status = Test.Status.approvedByTheLecturerAndGivenFactor;
		  		exerciseCheckerQueue.insert(test);
		  		
		  	} catch (InterruptedException e) {}
		}	
	}
	
	private double giveFactor(Test t) {
		double beforFactor = t.scoreWithoutFactor;
		if (beforFactor <= 55 && beforFactor >= 50) {
			return 56;
		} else if (beforFactor > 56) {
			return (Math.min(100, beforFactor+5));
		}
		return beforFactor;
	}

	public static void finishingScans() {
		scanningOver = true;
		GUI.setEnd(true);
		printConclusion();	
	}
	
	private static void printConclusion() {
		double averageBeforeFactor = totalGradesBeforeFactor/nStudents;
		double averageAfterFactor = totalGradesAfterFactor/nStudents;
		String conclusionOutput = "Test is over! Grades are published, and here are the results: ";
		conclusionOutput += "\r\n Number of students: " + nStudents;
		conclusionOutput += "\r\n Total avarage before factor: " + averageBeforeFactor;
		conclusionOutput += "\r\n Total avarage after factor: " + averageAfterFactor;
		conclusionOutput += "\r\n The best students: ";
		for (Integer student : BestStudents) {
			conclusionOutput += "\r\n" + student;
		}
		double total = 0;
		for (Profitable employee : emplyees) {
			total += employee.profit();
		}
		conclusionOutput += "\r\n The total salary of the employees: " + total;

		Print.print(conclusionOutput);	
	}

}
