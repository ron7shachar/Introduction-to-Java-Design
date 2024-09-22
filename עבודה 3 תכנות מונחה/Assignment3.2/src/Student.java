
public class Student implements Comparable<Student>, Profitable {

	private String email;
	private String firstName;
	private String lastName;
	private int age;
	private int profit;
	double mathGrade; // [1,1000]
	double englishGrade; // [1,1000]
	private int studentLevel; // [1,10]
	private double totalGrade;

	public Student(String email, String firstName, String lastName, int age) throws NotValidEmail { // constructor
		this.email = checkEmail(email);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.profit = 10;
		this.studentLevel = 1;
	}

	public void setGrades(double mathGrade, double englishGrade) { // update all the things that depend on the Grades
		if (validGrade(mathGrade) && validGrade(englishGrade)) {
			this.mathGrade = mathGrade;
			this.englishGrade = englishGrade;
			this.totalGrade = mathGrade * 700 + englishGrade * 300;
			studentLevel = (int) (totalGrade / 100);
			if (studentLevel == 0) {
				studentLevel = 1;
			}
		}
	}

	public void setMathGrade(double mathGrade) { // set Math Grade if valid
		if (validGrade(mathGrade)) {
			this.mathGrade = mathGrade;
			setGrades(mathGrade, this.englishGrade);
		}
	}

	public void setEnglishGrade(double englishGrade) { // set English Grade if valid
		if (validGrade(englishGrade)) {
			this.englishGrade = englishGrade;
			setGrades(this.mathGrade, englishGrade);
		}
	}

	public void pay(int pay) { // pay
		if (pay >= 0) { // valid payment
			profit += pay;
		}
	}

	public double getMathGrade() {
		return mathGrade;
	}

	public double getEnglishGrade() {
		return englishGrade;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public double getTotalGrade() {
		return totalGrade;
	}

	public int getStudentLevel() {
		return studentLevel;
	}

	@Override
	public int compareTo(Student o) {
		return (this.profit - o.profit);
	}

	private static String checkEmail(String email) throws NotValidEmail { // check valid email
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				return email;
			}
		}
		throw new NotValidEmail();
	}

	private static boolean validGrade(double drade) { // check if the grade in the rage
		if (drade >= 0 && drade <= 1) {
			return true;
		}
		return false;
	}

	@Override
	public int profit() {
		return profit;
	}

}