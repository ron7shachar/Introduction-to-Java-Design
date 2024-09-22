import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Application {

	Vector<Student> students = new Vector<Student>();;
	Vector<Question> questions = new Vector<Question>();
	Vector<Ad> ads = new Vector<Ad>();
	Vector<Practice> practices = new Vector<Practice>();

	public Application(String import_questions) throws IOException { // constructor, import the questions
		importQuestions(import_questions);
	}

	public void addStudent(Student s) { // add new student to the students in the app
		students.add(s);
	}

	public void addAd(Ad ad) { // add new ad to the ads in the app
		ads.add(ad);
	}

	public void addQuestion(Question q) { // add new question to the questions in the app
		questions.add(q);
	}

	public void importQuestions(String import_questions) throws IOException { // import questions file and Update the questions
		BufferedReader inFile = null;
		try { // try to read the file
			FileReader fr = new FileReader(import_questions);
			inFile = new BufferedReader(fr);
		}
		catch (FileNotFoundException exception) { // if the file not found
			System.out.println("The file " + import_questions + " was not found.");
		} catch (IOException exception) {
		}
		String questionString = inFile.readLine(); 
		while (questionString != null) {
			questionString = inFile.readLine(); // reading the lines in the file
			if (questionString != null) {
				addQuestion(stringToQuestions(questionString));
			}
		}
		inFile.close();
	}

	private static Question stringToQuestions(String questionString) { // get Question as a string[] and return Question
		Question question;
		String[] arr = stringToArray(questionString);
		String content = arr[1];
		int level = (Integer.parseInt(arr[2]));
		char answer = arr[3].charAt(0);
		String[] choices = { arr[4], arr[5], arr[6], arr[7] };
		if (arr.length == 10) { // check which Question it is Quantitative
			String hint = arr[9];
			question = new EnglishQuestion(content, level, answer, choices, hint); // make a English Question
		} else {
			String formula = arr[8];
			question = new QuantitativeQuestion(content, level, answer, choices, formula); // make a Quantitative Question																				// Question
		}
		return question;
	}

	private static String[] stringToArray(String questionString) { // get Question as a string and return Questions as a array														
		String[] arr = questionString.split("\t");
		return (arr);
	}

	public void createPractice(String email) throws EmailStudentNotExist { // create Practice to the student own the given email
		Practice practice;
		Student student;
		Vector<Question> practiceQuestions;
		Ad randomAd = ads.elementAt((int) (Math.random() * (ads.size() - 1)));
		if (studentIndexByEmail(email) >= 0) {
			student = students.elementAt(studentIndexByEmail(email));
			practiceQuestions = practiceQuestions(student.getStudentLevel());
			practice = new Practice(student, practiceQuestions, randomAd);
			practices.add(practice);
			practice.runPractice();
			student.setGrades(practice.getQuantitativeGrade(), practice.getEnglishGrade());
			randomAd.pay();
			student.pay(2);
		} else {
			throw new EmailStudentNotExist();
		}
	}

	private Vector<Question> practiceQuestions(int level) { // create set of questions to a specific practice
		Vector<Question> practiceQ = new Vector<Question>();
		for (int i = 0; i < 2; i++) {
			practiceQ.addAll(questionsInTheStudentsLevel(i, level));
			practiceQ.addAll(questionInTheLowestLevel(i));
			practiceQ.addAll(questionInTheHighestLevel(i));
		}
		return practiceQ;
	}

	private Vector<Question> oneIssueQuestions(int issue) { // return vector of questions from just one issue
		Vector<Question> allQ = new Vector<Question>();
		allQ.addAll(questions);
		if (issue == 0) {
			for (int k = 0; k < allQ.size(); k++) {
				if (allQ.elementAt(k) instanceof EnglishQuestion) {
					allQ.remove(k);
				}
			}
		} else if (issue == 1) {
			for (int k = 0; k < allQ.size(); k++) {
				if (allQ.elementAt(k) instanceof QuantitativeQuestion) {
					allQ.remove(k);
				}
			}
		}
		allQ.sort(null);
		return (allQ);
	}

	private Vector<Question> questionsInTheStudentsLevel(int issue, int level) { // return vector of 4 questions In The Student's Level
		Vector<Question> q = new Vector<Question>();
		Vector<Question> allQ = new Vector<Question>();
		allQ.addAll(oneIssueQuestions(issue));
		for (int j = 0; j < 4; j++) {
			while (true) {
				int maxBorder = (allQ.size()-1) / 10 * level;
				int minBorder = (allQ.size()-1) / 10 * (level-1);
				int randomIndex = (int) (Math.random() * (maxBorder - minBorder) + minBorder);
				if (allQ.elementAt(randomIndex).level == level) {
					q.add(allQ.elementAt(randomIndex));
					allQ.remove(randomIndex);
					break;
				}
			}
		}
		return q;
	}

	private Vector<Question> questionInTheHighestLevel(int issue) { // return vector of 4 questions In The highest Level
		Vector<Question> q = new Vector<Question>();
		Vector<Question> allQ = new Vector<Question>();
		allQ.addAll(oneIssueQuestions(issue));
		for (int j = 0; j < 4; j++) {
			Question max = getMax(allQ);
			q.add(max);
			allQ.remove(max);
		}
		return q;
	}

	private Vector<Question> questionInTheLowestLevel(int issue) { // return vector of 4 questions In The lowest Level
		Vector<Question> q = new Vector<Question>();
		Vector<Question> allQ = new Vector<Question>();
		allQ.addAll(oneIssueQuestions(issue));
		allQ.sort(null);
		for (int j = 0; j < 4; j++) {
			Question min = getMin(allQ);
			q.add(min);
			allQ.remove(min);
		}
		return q;
	}

	private int studentIndexByEmail(String email) { // return the student own the given email
		for (int i = 0; i < students.size(); i++) {
			if (students.elementAt(i).getEmail() == email) {
				return i;
			}
		}
		return -1;
	}

	public void bestStudents() { // print the student with the highest scores
		Student smartest = smartestStudentsIndex();
		System.out.println(smartest.getFirstName() + " " + smartest.getLastName() + " has the best grade: "
				+ smartest.getTotalGrade());
		Student bigestProfit = theMostProfitableStudents();
		System.out.println(bigestProfit.getFirstName() + " " + bigestProfit.getLastName() + " has the bigest profit: "
				+ bigestProfit.profit());
	}

	public Student theMostProfitableStudents() { // find the student with the highest profit
		return (getMax(students));
	}

	public Student smartestStudentsIndex() { // find the index of the student with the highest grade
		int max = 0;
		for (int i = 0; i < students.size(); i++) {
			if (students.elementAt(i).getTotalGrade() > students.elementAt(max).getTotalGrade()) {
				max = i;
			}
		}
		return students.elementAt(max);
	}

	public static int totalRevenues(Vector<Profitable> p) { // return the total revenue got from all the elements in the vector
		int total = 0;
		for (int i = 0; i < p.size(); i++) {
			total += p.elementAt(i).profit();
		}
		return total;
	}

	public int updateQuestionsLevel() { // update Questions Level according to the practices that have been done
		int updated = 0;
		for (int i = 0; i < questions.size(); i++) {
			if ((questions.elementAt(i)).updateLevel()) {
				updated += 1;
			}
		}
		return updated;
	}

	public static <T> T getMax(Vector<? extends Comparable<T>> c) { // return the max element in the given vector
		int maxIndex = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.elementAt(i).compareTo((T) c.elementAt(maxIndex)) > 0) {
				maxIndex = i;
			}
		}
		return (T) c.elementAt(maxIndex);
	}

	public static <T> T getMin(Vector<? extends Comparable<T>> c) { // return the min element in the given vector
		int minIndex = 0;
		for (int i = 0; i < c.size(); i++) {
			if (c.elementAt(i).compareTo((T) c.elementAt(minIndex)) < 0) {
				minIndex = i;
			}
		}
		return (T) c.elementAt(minIndex);
	}
}
