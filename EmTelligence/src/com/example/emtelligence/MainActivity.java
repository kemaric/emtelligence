package com.example.emtelligence;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	//Context context;
	private Button recordB, trackB;
	public JournalDatabaseAdaper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		
		recordB = (Button)findViewById(R.id.recordButton);
		trackB = (Button)findViewById(R.id.trackButton);
				
		TextView txt1 = (TextView)findViewById(R.id.title1);  
		TextView txt2 = (TextView)findViewById(R.id.title2); 
		
		Typeface mFont = Typeface.createFromAsset(getAssets(), "Lucida Handwriting.ttf");		
		txt1.setTypeface(mFont);
		txt2.setTypeface(mFont);
		
		
		
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
				Intent trackingActivity = new Intent(getApplicationContext(), TrackingActivity.class);
				startActivity(trackingActivity);
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
