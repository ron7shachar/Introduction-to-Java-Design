
public class Ad implements Comparable<Ad>, Profitable {

	private String content;
	public int pricePerPractice;
	private int minAge;
	private int maxAge;
	private int profit;
	
	public Ad(String content, int pricePerPractice, int minAge, int maxAge) throws NotForYoungAd { // constructor with validation check
		this.content = content;
		this.pricePerPractice = pricePerPractice;
		this.maxAge = maxAge;
		this.minAge = minAge;
		if (minAge < 18) {
			if (isAdultThemedAd()) {
				throw new NotForYoungAd();
			}
		}	
	}

	@Override
	public int compareTo(Ad o) { // return the gap between tow ads' profits
		return (this.profit - o.profit);
	}
	
	public boolean isAdultThemedAd() { // return if the ad for adults or not
		return (this.content.toLowerCase().contains("xxx"));
	}
	
	public boolean suitableForStudent(Student s) { // check if tthe ad suitable For the given Student
		return (s.getAge() >= minAge && s.getAge() <= maxAge);
	}

	@Override
	public int profit() { // return profit
		return profit;
	}
	
	public String getContent() { // return content
		return content;
	}
	
	public void pay() { // add price to the profit
		if (pricePerPractice >= 0) {
			profit += pricePerPractice;
		}
	}

}
