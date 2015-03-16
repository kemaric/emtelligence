package com.example.emtelligence;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite
import android.database.sqlite.SQLiteOpenHelper;
public class JournalActivity extends Activity{
	EditText feeling, description;
	private String array_spinner[];
	Spinner scoreBox;
	Button submitB;
	JournalEntry jEntry;
	JournalDatabaseAdaper sqlhelper;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_entry);
		Bundle extras = getIntent().getExtras();
		
		//Getting the user imput from the emotion icons
		String feels = "other";
		int emotionTyp = 0;
		if (extras != null) {
			feels = extras.getString("mybuttons");
			emotionTyp = extras.getInt("EMOTION_TYPE");
		}
		jEntry = null;
		
		//Feeling will be filled with the emotion they picked
		feeling = (EditText)findViewById(R.id.feelingInput);
		feeling.setText(feels);
	
		scoreBox = (Spinner)findViewById(R.id.spinner1);
		description = (EditText)findViewById(R.id.feelingExplanation);
		array_spinner=new String[4];
		array_spinner[0]= "Neutral";
		array_spinner[1]="Some what";
		array_spinner[2]="Moderately";
		array_spinner[3]="Very";
	
		final ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item,
				array_spinner);
		submitB = (Button)findViewById(R.id.submitB);
		scoreBox.setAdapter(adapter);
		
		sqlhelper = new JournalDatabaseAdaper(this); 
		//scoreBox.get
		submitB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String level = (String) adapter.getItem(scoreBox.getSelectedItemPosition());
				Integer emotionLvl = 0;
				if (level.equals("VERY_POSITIVE")) {
					emotionLvl = EmotionalValue.VERY_POSITIVE.ordinal();
				} else if (level.equals("VERY_NEGATIVE")) {
					emotionLvl = EmotionalValue.VERY_NEGATIVE.ordinal();
				}else if (level.equals("MODERATELY_NEGATIVE")) {
					emotionLvl = EmotionalValue.MODERATELY_NEGATIVE.ordinal();
				}else if (level.equals("SOMEWHAT_NEGATIVE")) {
					emotionLvl = EmotionalValue.SOMEWHAT_NEGATIVE.ordinal();
				}else if (level.equals("NEUTRAL")) {
					emotionLvl = EmotionalValue.NEUTRAL.ordinal();
				}else if (level.equals("SOMEWHAT_POSITIVE")) {
					emotionLvl = EmotionalValue.SOMEWHAT_POSITIVE.ordinal();
				}else if (level.equals("MODERATELY_POSITIVE")) {
					emotionLvl = EmotionalValue.MODERATELY_POSITIVE.ordinal();
				}

				JournalEntry jEntry = new JournalEntry(new 
						Emotion(EmotionalValue.valueOf(emotionLvl),feeling.getText().toString()), 
						description.getText().toString());
				
				//SQLiteDatabase database = sqlhelper.db.getWritableDatabase();
				//Cursor c = database.rawQuery("SELECT  * FROM " + "entires", null);
				//if(c.equals(null))
				//	Log.d("Feteching: ", "Database not there"); 
				//else{
					long success = sqlhelper.addJournalEntry(jEntry);
					if(success < 0)
						Toast.makeText(getBaseContext(), "Failed to add entry!", Toast.LENGTH_LONG).show();
					else
						Toast.makeText(getBaseContext(), "Successfully added entry!", Toast.LENGTH_LONG).show();
					
					Intent i= new Intent(getApplicationContext(),MainActivity.class);//homescreen of your app.
				    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
				    startActivity(i);
				    finish(); 
			}
		});
	}
}
