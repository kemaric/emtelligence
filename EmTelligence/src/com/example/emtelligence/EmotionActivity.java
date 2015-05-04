package com.example.emtelligence;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class EmotionActivity extends Activity{

	private ViewSwitcher switcher;
	private int emotionType = 0;
	private ImageButton positiveB,negativeB,neutralB;
	@SuppressLint("NewApi")
	@Override


	//TODO: Create submit button function to handle the submissions cases.
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
				emotionType= -1;
				setContentView(R.layout.negative_feelings);
				
				final ArrayList<ToggleButton> buttonList = new ArrayList<ToggleButton>();
				//Adding buttons to the list to pass on
				ToggleButton tmpButton = (ToggleButton)findViewById(R.id.buttonAngry);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonAnxious);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonConfused2);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonEnvious);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonLazy);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonSad);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonSurprised2);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonTired);
				buttonList.add(tmpButton);
					
				for (final ToggleButton b : buttonList){
				    b.setOnClickListener(new View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
				        	for (final ToggleButton other : buttonList){
				        		if (other != b)
				        			other.setChecked(false);
			
				        	}
				        }
				    });
				}
				
				
				
				Button submitButton = (Button)findViewById(R.id.buttonSubNeg);
				submitButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						for (final ToggleButton b : buttonList){
						    if (b.isChecked()){
						    	// B is the button that was pressed
						    	Intent journalEnrty = new Intent(getApplicationContext(), JournalActivity.class);
								
								journalEnrty.putExtra("mybuttons",b.getText().toString());
								journalEnrty.putExtra("EMOTION_TYPE",emotionType);
								startActivity(journalEnrty);
						    }
						}	
					}
				});
				//switcher = (ViewSwitcher) findViewById(R.id.negativeF);
			}
		});
		
		
		positiveB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.positive_feelings);
				emotionType = 1;
				final ArrayList<ToggleButton> buttonList = new ArrayList<ToggleButton>();
				//Adding buttons to the list to pass on
				ToggleButton tmpButton = (ToggleButton)findViewById(R.id.buttonActive);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonHappy);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonConfident);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonAttractive);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonFocused);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonCalm);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonFunny);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonSurprised);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonIntelligent);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonStrong);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonProud);
				buttonList.add(tmpButton);
					
				for (final ToggleButton b : buttonList){
				    b.setOnClickListener(new View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
				        	for (final ToggleButton other : buttonList){
				        		if (other != b)
				        			other.setChecked(false);
				        	}
				        }
				    });
				}
				
				
				
				Button submitButton = (Button)findViewById(R.id.buttonSubPos);
				submitButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						

						for (final ToggleButton b : buttonList){
						    if (b.isChecked()){
						    	// B is the button that was pressed
								Intent journalEnrty = new Intent(getApplicationContext(), JournalActivity.class);
								
								journalEnrty.putExtra("mybuttons", b.getText().toString());
								journalEnrty.putExtra("EMOTION_TYPE",emotionType);
								startActivity(journalEnrty);
						    }
						}
						
					}
				});
				//switcher = (ViewSwitcher) findViewById(R.id.positiveF);
			}
		});
		neutralB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				emotionType = 0;
				
				// TODO Auto-generated method stub
				setContentView(R.layout.neutral_feelings);
				final ArrayList<ToggleButton> buttonList = new ArrayList<ToggleButton>();
				//Adding buttons to the list to pass on
				ToggleButton tmpButton = (ToggleButton)findViewById(R.id.buttonBored);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonConfused);
				buttonList.add(tmpButton);
				tmpButton = (ToggleButton)findViewById(R.id.buttonIndifferent);
				buttonList.add(tmpButton);
					
				for (final ToggleButton b : buttonList){
				    b.setOnClickListener(new View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
				        	for (final ToggleButton other : buttonList){
				        		if (other != b)
				        			other.setChecked(false);
				        	}
				        }
				    });
				}
				
				
				
				Button submitButton = (Button)findViewById(R.id.buttonSubNue);
				submitButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						for (final ToggleButton b : buttonList){
						    if (b.isChecked()){
						    	// B is the button that was pressed
						    	Intent journalEnrty = new Intent(getApplicationContext(), JournalActivity.class);
								
								journalEnrty.putExtra("mybuttons", b.getText().toString());
								journalEnrty.putExtra("EMOTION_TYPE",emotionType);
								startActivity(journalEnrty);
						    }
						}
					}
				});
				//switcher = (ViewSwitcher) findViewById(R.id.neutralF);
			}
		});
		/*if(switcher != null && (switcher.getId() == R.id.neutralF || 
				switcher.getId() == R.id.positiveF ||
				switcher.getId() == R.id.negativeF)){
				.onGenericMotionEvent(event)
			}
		*/
	
	
	}
	
}
