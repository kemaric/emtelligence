package com.example.emtelligence;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;

public class EmotionActivity extends Activity{

	private ViewSwitcher switcher;
	private ImageButton positiveB,negativeB,neutralB;
	@SuppressLint("NewApi")
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
				//switcher = (ViewSwitcher) findViewById(R.id.negativeF);
			}
		});
		
		
		positiveB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.positive_feelings);
				//switcher = (ViewSwitcher) findViewById(R.id.positiveF);
			}
		});
		neutralB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.neutral_feelings);
				//switcher = (ViewSwitcher) findViewById(R.id.neutralF);
			}
		});
		/*if(switcher != null && (switcher.getId() == R.id.neutralF || 
				switcher.getId() == R.id.positiveF ||
				switcher.getId() == R.id.negativeF)){
				.onGenericMotionEvent(event)
			}
		*/
	Button submitButton = (Button)findViewById(R.id.buttonSubPos);
	submitButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			ArrayList<Button> buttonList = new ArrayList<Button>();
			//Adding buttons to the list to pass on
			Button tmpButton = (Button)findViewById(R.id.buttonActive);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonHappy);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonConfident);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonAttractive);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonFocused);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonCalm);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonFunny);
			buttonList.add(tmpButton);
			tmpButton = (Button)findViewById(R.id.buttonSurprised);
			buttonList.add(tmpButton);
			
			Intent journalEnrty = new Intent(getApplicationContext(), JournalActivity.class);
			
			//journalEnrty.putExtra("mybuttons", buttonList.t);
			startActivity(journalEnrty);
		}
	});
	}
	
}
