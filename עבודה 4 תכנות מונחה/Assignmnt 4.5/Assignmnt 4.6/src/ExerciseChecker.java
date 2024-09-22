import java.util.TreeMap;

public class ExerciseChecker implements Runnable, Profitable {

	private String name;
	private int salaryPerSecond = 1;
	private double totalSalary = 0;
	private Queue<Test> exerciseCheckerQueue;
	private Queue<Test> iemSecretaryQueue;
	private TreeMap<Integer, double[]> gradeList;
	double assignmentsGrade ;
	
	public ExerciseChecker(String name, TreeMap<Integer,double[]> gradeList, Queue<Test> exerciseCheckerQueue, Queue<Test> iemSecretaryQueue) {
		this.name = name;
		this.gradeList = gradeList;
		this.exerciseCheckerQueue = exerciseCheckerQueue;
		this.iemSecretaryQueue = iemSecretaryQueue;
	} // constructor
		
	@Override
	public void run() {
		while (!GUI.getEnd()) { // while not end massage from the lecturer
			try {
				//Print.print("befor extract EC");
				Test test = exerciseCheckerQueue.extract();
				if (test == null) {break;}
				//Print.print("after extract EC");
				
				long workMiliSeconds = (long) (Math.random() * (2500-1500) + 1500); // count the salary
	  			totalSalary = totalSalary + (workMiliSeconds/1000)*salaryPerSecond;
	  			Thread.sleep(workMiliSeconds);
	  			
				double[] gradeList = this.gradeList.get(test.getId());  // update grade
				assignmentsGrade = 0.02*gradeList[0]+0.04*gradeList[1]+0.06*gradeList[2]+0.08*gradeList[3];
				test.finalScore = 0.8 * test.scoreAfterFactor + 0.2* assignmentsGrade;
				
				test.status = Test.Status.weighedWithWorks; // change status
				this.iemSecretaryQueue.insert(test); // continue
				
			} catch (InterruptedException e) {}
		}
	}

	@Override
	public double profit() {
		return totalSalary;
	}

}
