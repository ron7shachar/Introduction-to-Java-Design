import java.util.Date;

public class Test {
	private int id;
	private Date date;
	private int classNumber;
	private boolean[] answers;
	private Queue<Test> firstTAQueue;
	private Queue<Test> secondTAQueue;
	private Queue<Test> lecturerQueue;
	private Queue<Test> exerciseCheckerQueue;
	private Queue<Test> iemSecretaryQueue;
	
	public double scoreWithoutFactor = 0.0;
	public double scoreAfterFactor= 0.0;
	public double finalScore= 0.0;
	
	enum Status {
		signedByProctor,
		tested1,
		tested2,
		approvedByTheLecturerAndGivenFactor,
		weighedWithWorks,
		EnteredIntoTheSystems,
		scanned,
		ReadByTheStudent
	}
	
	public Status status;
	
	
	public Test (int id ,Date date) {
		this.id = id;
		this.date = date;
	}
	
	public void setAnswers(boolean[] answers) {
		this.answers = answers;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public boolean[] getAnswers() {
		return answers;
	}
	
	public void setClassNumber(int classNum) {
		this.classNumber = classNum;
	}

}