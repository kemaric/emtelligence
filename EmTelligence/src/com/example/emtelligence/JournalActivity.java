package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import android.database.sqlite
public class JournalActivity extends Activity{
	EditText feeling, description;
	private String array_spinner[];
	Spinner scoreBox;
	Button submitB;
	JournalEntry jEntry;
	JournalDatabaseAdaper sqlhelper;
	String feels = "other";
	int emotionTyp = 0;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_entry);
		Bundle extras = getIntent().getExtras();
		
		//Getting the user imput from the emotion icons
		feels = "other";
		emotionTyp = 0;
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
		if(emotionTyp != 0){
			array_spinner=new String[3];
			array_spinner[0]="Some what";
			array_spinner[1]="Moderately";
			array_spinner[2]="Very";

		}else{
			array_spinner=new String[1];
			array_spinner[0]="Neutral";
		}

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
				Integer emotionLvl;

				//TODO: Change this section (make it choose positive/negative 1,2,3 based on emotion)
				/**This is very dumb. Should ultimately only check for if you picked positive,
				 * negative or neutral and depending on that, the +/- will be applied. A Neutral of
				 * a +/- is just +/- 1.
				 *
				 * In here emotionLvl never gets changed so the value stays as 0.
				**/
				if (level.equals("Some what")) {
					emotionLvl = emotionTyp*1;
				} else if (level.equals("Moderately")) {
					emotionLvl = emotionTyp*2;
				}else if (level.equals("Very")) {
					emotionLvl = emotionTyp*3;
				}else{
					emotionLvl = 0;
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
