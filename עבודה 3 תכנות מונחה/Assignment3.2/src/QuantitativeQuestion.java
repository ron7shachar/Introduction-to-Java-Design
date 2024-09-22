
public class QuantitativeQuestion extends Question {

	private String formula;

	public QuantitativeQuestion(String content, int level, char answer, String[] choices, String formula) {  // constructor
		super(content, level, answer, choices);
		this.formula = formula;
	}

	public String getFormula() {
		return formula;
	}
	public boolean updateLevel() { // update the level
		boolean change = false;
		if (this.counterSuccesses / this.counterAttempts > 0.7) {  // if the % of Success is > 70 % then up the level by 1
			if (this.level < 10) {
				level++;
				change = true;
			}
		}
	
		if (this.counterSuccesses / this.counterAttempts < 0.25) { // if the % of Success is < 25 % then down the level by 1
			if (this.level >= 1) {
				level -= 1;
				change = true;
			}
		}
		return change;
	}

}