import java.util.Vector;

public class Practice {
	
	Student student;
	Vector<Question> questions;
	Ad ad;
	private int englishCounter;
	private int quantitativeCounter;
	private double englishCorrect;
	private double quantitativeCorrect;
	private double englishGrade;
	private double quantitativeGrade;

	public Practice(Student student, Vector<Question> questions, Ad ad) throws NotSuitableAdForTheStudent {  // constructor with validation check
		this.student = student;
		this.questions = questions;
		this.questions.sort(null);
		if (!ad.suitableForStudent(student)) {
			throw new NotSuitableAdForTheStudent();
		}
		this.ad = ad;
	}
		
	public Question getQuestionByNumber(int number) { // return Question By Number (index)
			return (questions.elementAt(number));
	}
	
	public int numOfQuestions() { // return num Of Questions
		return questions.size();
	}
	
	public void runPractice() { // init and run the practice
		System.out.println(this.ad.getContent());
		System.out.println("Welcome to the Practice");
		for (int i = 0; i < questions.size(); i ++) {
			if(questions.elementAt(i) instanceof QuantitativeQuestion) {
				quantitativeCounter ++;
				printFormula(i);
				printQuestion(i);
				System.out.println("Your answer: " + symbolizeStudentAns(questions.elementAt(i)));
			} else {
				englishCounter ++;
				printQuestion(i);
				printHint(i);
				System.out.println("Your answer: " + symbolizeStudentAns(questions.elementAt(i)));
			}			
		}
		calculateScores();
		printScores();	
	}
	
	private char symbolizeStudentAns(Question q) { // symbolize Student Ans by his level
		char answer = q.getAnswer();
		double studentLevel = student.getStudentLevel();
		if (Math.random() <= studentLevel/10) {
			addCorrectAns(q);
			q.answer(true);
			return answer;
		} else {
			q.answer(false);
			return wrongAnswer(answer);
		}
	}
	
	private char wrongAnswer(char correctAnswer) { // return random wrong answer
		Vector<String> options = new Vector<String>();
		options.add("a");
		options.add("b");
		options.add("c");
		options.add("d");
		options.remove(Character.toString(correctAnswer));
		
		double random = Math.random();
		if (random <= (1/3)) {
			return (options.elementAt(0)).charAt(0);
		} else if (random <= (2/3)) {
			return (options.elementAt(1)).charAt(0);
		} else {
			return (options.elementAt(2)).charAt(0);
		}
	}
	
	private void printQuestion(int index) { // print Question
		System.out.println("Question number " + String.valueOf(index+1) + ": " + questions.elementAt(index).getContent());
		System.out.println("The options:");
		for (String string : (questions.elementAt(index)).getChoices()) {
			System.out.println(string);
		}
	}
	
	private void printFormula(int index) { // print Formula
		System.out.println(((QuantitativeQuestion)(questions.elementAt(index))).getFormula());
	}
	
	private void printHint(int index) { // print Hint
		System.out.println(((EnglishQuestion)(questions.elementAt(index))).getHint());
	}
	
	private void addCorrectAns(Question q) { // add Correct Ans to the counter
		if (q instanceof QuantitativeQuestion) {
			quantitativeCorrect ++;
		} else {
			englishCorrect ++;
		}
	}
	
	private void printScores() { // print Scores
		System.out.println("Practice was finished, your math score is: " + String.valueOf(quantitativeGrade*100) + "%.");
		System.out.println("Practice was finished, your english score is: " + String.valueOf(englishGrade*100) + "%.");
	}
	
	private void calculateScores() { // calculate Scores
		quantitativeGrade = quantitativeCorrect/quantitativeCounter;
		englishGrade = englishCorrect/englishCounter;
	}
	
	public double getEnglishGrade() { // return English Grade
		return englishGrade;
	}

	public double getQuantitativeGrade() { // return Quantitative Grade
		return quantitativeGrade;
	}

}
