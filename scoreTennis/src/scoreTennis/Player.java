package scoreTennis;

public class Player {
	private int score;
	private int numberSet;
	private boolean advantage;
	private boolean victory;

	public Player() {
		this.score = 0;
		this.numberSet = 0;
		this.advantage = false;
		this.victory = false;
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getNumberSet() {
		return numberSet;
	}

	public void setNumberSet(int numberSet) {
		this.numberSet = numberSet;
	}

	public boolean isAdvantage() {
		return advantage;
	}

	public void setAdvantage(boolean advantage) {
		this.advantage = advantage;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}
	
	public int resetScore() {
		return this.score = 0;
	}
	
	public void winSet() {
		this.numberSet++;
		this.resetScore();
		this.setAdvantage(false);
	}
	
	public void looseSet() {
		this.resetScore();
	}
	
	public void looseAdvantage() {
		this.setAdvantage(false);
		this.backEquality();
	}
	
	public void backEquality() {
		this.setScore(40);
	}
	
}
