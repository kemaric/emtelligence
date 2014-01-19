package com.example.emtelligence;

public class Emotion {
	public enum EmotionalValue {
		VERY_NEGATIVE(-3),MODERATELY_NEGATIVE(-2),SOMEWHAT_NEGATIVE(-1),
		NEUTRAL(0),SOMEWHAT_POSITIVE(1),MODERATELY_POSITIVE(2),VERY_POSITIVE(3);
		private final int id;
		private EmotionalValue(int id){this.id = id;}
		public int getValue(){return id;}
		
		/**Gets the EmotionalValue object based on the value input.**/
		public static EmotionalValue valueOf(int val) {
		    for (EmotionalValue category : values()) {
		        if (category.equals(val)) {
		            return category;
		        }
		    }    
		    throw new IllegalArgumentException(String.valueOf(val));
		}
		//public String toString(){return "" + id + "";}
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
