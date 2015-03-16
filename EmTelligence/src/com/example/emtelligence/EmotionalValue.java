package com.example.emtelligence;

public enum EmotionalValue {
	VERY_NEGATIVE(-3),MODERATELY_NEGATIVE(-2),SOMEWHAT_NEGATIVE(-1),
	NEUTRAL(0),SOMEWHAT_POSITIVE(1),MODERATELY_POSITIVE(2),VERY_POSITIVE(3);
	
	private int value;
	
	private EmotionalValue(int val){
		this.value = val;
	}
	
	/**Gets the EmotionalValue object based on the value input.**/
	public static EmotionalValue valueOf(int val) {
	    for (EmotionalValue category : values()) {
	    	int catval = category.value;
	        if (catval == val) {
	            return category;
	        }
	    }    
	    throw new IllegalArgumentException(String.valueOf(val));
	}
	
	public int getValue(){ return value;};
	/*public static EmotionalValue valueOf(String val) {
	    for (EmotionalValue category : values()) {
	    	int catval = category.getValue();
	        if (catval == val) {
	            return category;
	        }
	    }    
	    throw new IllegalArgumentException(String.valueOf(val));
	}*/
}
