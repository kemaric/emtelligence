package com.example.emtelligence;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EntryView extends Activity{

	private JournalEntry entry;
	private TextView date;
	private TextView emotion;
	private TextView score;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry_view);
		Bundle extras = getIntent().getExtras();
		int position = (int) extras.getLong("position");

		entry = JournalTable.JournalList.get(position);

		date=(TextView)findViewById(R.id.entryDateCol);
		date.setText("" + entry.getEntryDate().getMonth() + "-" +entry.getEntryDate().getDate() 
				+"-"+ entry.getEntryDate().getYear() + " " + entry.getEntryDate().getHours() +
				":" + entry.getEntryDate().getMinutes());

		emotion=(TextView)findViewById(R.id.entryEmotionCol);
		emotion.setText(entry.getEmotion().getFeeling());

		score=(TextView)findViewById(R.id.entry_score);
		score.setText(String.valueOf(entry.getEmotion().getEv().getValue()));
	}

}
