package com.example.emtelligence;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class EmotionActivity extends Activity{

	private ViewFlipper switcher;
	private ImageButton positiveB,negativeB,neutralB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emotional_value);
		
		negativeB = (ImageButton)findViewById(R.id.negativeButton);
		positiveB = (ImageButton)findViewById(R.id.positiveButton);
		neutralB = (ImageButton)findViewById(R.id.neutralButton);
		//switcher = (ViewFlipper)findViewById(R.id)
		
		TextView txt1 = (TextView)findViewById(R.id.pageTitle);  
		TextView posTxt = (TextView)findViewById(R.id.positiveText);  
		TextView neuTxt = (TextView)findViewById(R.id.neutralText);  
		TextView negTxt = (TextView)findViewById(R.id.negativeText);  
		Typeface mFont = Typeface.createFromAsset(getAssets(), "Lucida Handwriting.ttf");		
		txt1.setTypeface(mFont);
		posTxt.setTypeface(mFont);
		neuTxt.setTypeface(mFont);
		negTxt.setTypeface(mFont);
		
		negativeB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.negative_feelings);
			}
		});
		
		positiveB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.positive_feelings);
			}
		});
		neutralB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.neutral_feelings);
			}
		});
	}
	
}
