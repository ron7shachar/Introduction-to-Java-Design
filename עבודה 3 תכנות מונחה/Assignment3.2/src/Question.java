
public abstract class Question implements Comparable<Question> {

	private String content;
	protected  int level;
	private char answer;
	private String[] choices;
	protected double counterAttempts;  // count the attempts
	protected double counterSuccesses; //count the successes

	public Question(String content, int level, char answer, String[] choices) throws NotValidLevel {  // constructor
		this.content = content;
		this.level = checkLevel(level);
		this.answer = answer;
		this.choices = choices;
	}

	private static int checkLevel(int level) throws NotValidLevel { // check if the level in the range 
		if (level > 0 && level <= 10) {
			return level;
		}
		throw new NotValidLevel();
	}

	public void answer(boolean answer) { // update the counterAttempts and the counterSuccesses
		counterAttempts += 1.0;
		if (answer) {
			counterSuccesses += 1.0;
		}
	}
	
	
	public String getContent() { 
		return content;
	}
	public int getLevel() {
		return level;
	}

	public char getAnswer() {
		return answer;
	}

	public String[] getChoices() {
		return choices;
	}

	public abstract boolean updateLevel();

	public int compareTo(Question o) { 
		return (this.level - o.level);
	}
}
