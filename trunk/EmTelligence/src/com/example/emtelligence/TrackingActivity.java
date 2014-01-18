package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrackingActivity extends Activity{

	private Button journalB, reportB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tracking_options);
		
		journalB = (Button)findViewById(R.id.journalButton);
		reportB = (Button)findViewById(R.id.progressButton);
	
		journalB.setOnClickListener( new View.OnClickListener() {
			
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
	}
	
}
