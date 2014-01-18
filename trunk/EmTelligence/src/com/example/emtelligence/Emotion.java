package com.example.emtelligence;

public class Emotion {
	private enum EmotionalValue {
		EXTREMELY_NEGATIVE,VERY_NEGATIVE,SOMEWHAT_NEGATIVE,
		NEUTRAL,SOMEWHAT_POSITIVE,VERY_POSITIVE,EXTREMELY_POSITIVE;
	}
	public String feeling;
	private EmotionalValue ev;
	
	/**Getters and setters**/
	public EmotionalValue getEv() {
		return ev;
	}

	public void setEv(EmotionalValue ev) {
		this.ev = ev;
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
