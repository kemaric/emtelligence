package com.example.emtelligence;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	//Context context;
	private ImageButton recordB, trackB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//context = this;
		
		recordB = (ImageButton)findViewById(R.id.recordButton);
		trackB = (ImageButton)findViewById(R.id.trackButton);
		
		/*function for record button action to make journal entry*/
		recordB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent emotionActivity = new Intent(getApplicationContext(), EmotionActivity.class);
				startActivity(emotionActivity);
			}
		});
		
		//Taking you to the page of tracking your past journal entries
		trackB.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent emotionActivity = new Intent(getApplicationContext(), TrackingActivity.class);
				startActivity(emotionActivity);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
