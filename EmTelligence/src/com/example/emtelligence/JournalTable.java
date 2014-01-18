package com.example.emtelligence;
import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
public class JournalTable extends Activity{

	private TableLayout journalTable; 
	ArrayList<JournalEntry> JournalList;
	int counter = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_table);
		
		TableLayout table = (TableLayout)findViewById(R.id.entryTable);
		counter++;
		// create a new TableRow
        TableRow row = new TableRow(this);
        for (JournalEntry entry:JournalList){
        	TextView date = new TextView(this);
        	TextView emotion = new TextView(this);
        	TextView score = new TextView(this);
        	date.setText(entry.getEntryDate().toString());
        	emotion.setText(entry.getEmotion().getFeeling());
        	score.setText(entry.getScore());
        	row.addView(date);
        	row.addView(emotion);
        	row.addView(score);
        	// add the TableRow to the TableLayout
            table.addView(row,new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }


		
	}

	
}
