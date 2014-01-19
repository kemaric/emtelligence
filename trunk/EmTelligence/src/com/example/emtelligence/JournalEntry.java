package com.example.emtelligence;

import java.util.Date;

public class JournalEntry {
	private int entryId;
	private String description;
	//private int score;
	public Date entryDate;
	private Emotion emotion;
	/**Full Constructor for an journal entry that takes in the description, 
	 * score and date. **/
	public JournalEntry(){
		
	}
	public JournalEntry(int entryID,String description,Date entryDate, Emotion emotion) {
		super();
		this.entryId = entryID;
		this.description = description;
		this.entryDate = entryDate;
		this.emotion = emotion;
	}
	
	public JournalEntry(Emotion emotion, String description) {
		super();
		this.description = description;
		this.entryDate = new Date();
		this.emotion = emotion;
	}
	
	/**Basic Constructor that only have a score but no description **/
	public JournalEntry(Date entrydate){
		super();
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
	
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	@Override
	public String toString() {
		return "JournalEntry [entryId=" + entryId + ", description="
				+ description + ", entryDate=" + entryDate
				+ ", emotion=" + emotion + "]";
	}
	
	
}
