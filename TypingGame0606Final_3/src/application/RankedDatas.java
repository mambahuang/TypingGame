package application;

public class RankedDatas {
	int playTime;
	double enteredWordsPerMin, accuracy;
	String playerName, rank;
	
	public RankedDatas(String rank, int playTime, double enteredWordsPerMin, double accuracy, String playerName) {
		this.rank = rank;
		this.playTime = playTime;
		this.enteredWordsPerMin = enteredWordsPerMin;
		this.accuracy = accuracy;
		this.playerName = playerName;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public double getEnteredWordsPerMin() {
		return enteredWordsPerMin;
	}

	public void setEnteredWordsPerMin(double enteredWordsPerMin) {
		this.enteredWordsPerMin = enteredWordsPerMin;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}	
	
}
