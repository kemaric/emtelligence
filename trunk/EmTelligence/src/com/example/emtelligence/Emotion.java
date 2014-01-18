package com.example.emtelligence;

public class Emotion {
	public enum EmotionalValue {
		EXTREMELY_NEGATIVE(-3),VERY_NEGATIVE(-2),SOMEWHAT_NEGATIVE(-1),
		NEUTRAL(0),SOMEWHAT_POSITIVE(1),VERY_POSITIVE(2),EXTREMELY_POSITIVE(3);
		private final int id;
		private EmotionalValue(int id){this.id = id;}
		public int getValue(){return id;}
		
		public String toString(){return "" + id + "";}
	}
	
	private String feeling;
	private EmotionalValue ev;
	
	/**Getters and setters**/
	public EmotionalValue getEv() {
		return ev;
	}

	public void setEv(EmotionalValue ev) {
		this.ev = ev;
	}

	public String getFeeling() {
		return feeling;
	}

	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public Emotion(){
		this.feeling = "neutral";
		this.ev = EmotionalValue.NEUTRAL;
	}
	
	public Emotion(EmotionalValue ev, String feeling){
		this.feeling = feeling;
		this.ev = ev;
	}
	
}
