package com.example.emtelligence;

import java.util.Date;

public class JournalEntry {
	private int entryId;
	private String description;
	private int score;
	public Date entryDate;
	private Emotion emotion;
	/**Full Constructor for an journal entry that takes in the description, 
	 * score and date. **/
	public JournalEntry(String description, int score,
			Date entryDate, Emotion emotion) {
		super();
		this.description = description;
		this.score = score;
		this.entryDate = entryDate;
		this.emotion = emotion;
	}
	
	/**Basic Constructor that only have a score but no description **/
	public JournalEntry(Date entrydate){
		super();
		this.score = 0;
		this.entryDate = entrydate;
		this.emotion = new Emotion();
	}
	
	
	/**Standard Getters and Setters for the entry items.**/
	public int getEntryId() {
		return entryId;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "JournalEntry [entryId=" + entryId + ", description="
				+ description + ", score=" + score + ", entryDate=" + entryDate
				+ ", emotion=" + emotion + "]";
	}
	
	
}
