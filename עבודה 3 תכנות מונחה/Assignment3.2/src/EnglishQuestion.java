
public class EnglishQuestion extends Question{

	private String hint;

	public EnglishQuestion(String content, int level, char answer, String[] choices, String hint) {  // constructor
		super(content, level, answer, choices);
		this.hint = hint;
	}
	
	public String getHint() {
		return hint;
	}
	@Override
	public boolean updateLevel() {
		boolean change = false;
		if (this.counterSuccesses / this.counterAttempts > 0.8) {// if the % of Success is > 80 % then up the level by 1
			if (this.level < 10) {
				level++;
				change = true;
			}
		}
		if (this.counterSuccesses / this.counterAttempts < 0.2) {// if the % of Success is < 20 % then up the level by 1
			if (this.level >= 1) {
				level -= 1;
				change = true;
			}
		}
		return change;
	}
	
}