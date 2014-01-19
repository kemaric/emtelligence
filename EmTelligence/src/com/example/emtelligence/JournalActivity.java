package com.example.emtelligence;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.database.sqlite.SQLiteOpenHelper;
public class JournalActivity extends Activity{
	EditText feeling, description;
	private String array_spinner[];
	Spinner scoreBox;
	Button submitB;
	JournalEntry jEntry;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_entry);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String value = extras.getString("button_list");
		}
		jEntry = null;
		feeling = (EditText)findViewById(R.id.feelingInput);
		scoreBox = (Spinner)findViewById(R.id.spinner1);
		description = (EditText)findViewById(R.id.feelingExplanation);
		array_spinner=new String[7];
        array_spinner[0]="-3";
        array_spinner[1]="-2";
        array_spinner[2]="-1";
        array_spinner[3]="0";
        array_spinner[4]="1";
        array_spinner[5]="2";
        array_spinner[6]="3";
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
        		array_spinner);
		submitB = (Button)findViewById(R.id.submitB);
		scoreBox.setAdapter(adapter);
		
		//scoreBox.get
		submitB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				JournalEntry jEntry = new JournalEntry(new Emotion(Emotion.EmotionalValue.valueOf(Integer.parseInt(array_spinner[4])),feeling.toString()) {
				}, description.toString());
				
			//	MySQLiteHelper sqlhelper = 
				//MySQLiteHelper sqlhelper = MySQLiteOpenHelper()

			}
		});
	}
}
