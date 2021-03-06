package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrackingActivity extends Activity{

	private Button journalB, reportB, settingsB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tracking_options);
		
		journalB = (Button)findViewById(R.id.journalButton);
		reportB = (Button)findViewById(R.id.progressButton);
		settingsB = (Button)findViewById(R.id.settingsButton);
		
		TextView txt1 = (TextView)findViewById(R.id.seeText);   
		Typeface mFont = Typeface.createFromAsset(getAssets(), "Lucida Handwriting.ttf");		
		txt1.setTypeface(mFont);
		
		journalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent journalTable = new Intent(TrackingActivity.this,JournalTable.class);
				startActivity(journalTable);
			}
		});
		
		reportB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent graphActivity = new Intent(TrackingActivity.this,Graph.class);
				startActivity(graphActivity);
			}
		});
		
		settingsB.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent settingsActivity = new Intent(TrackingActivity.this,SettingsActivity.class);
				startActivity(settingsActivity);
			}
		});
	}
	
}
