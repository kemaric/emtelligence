package com.example.emtelligence;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
public class JournalTable extends Activity {

	private ListView journalTable; 
	public static ArrayList<JournalEntry> JournalList;
	JournalDatabaseAdaper sqlhelper;
	private ArrayAdapter<JournalEntry> adapter2;
	int counter = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journal_table);

		/*JournalEntry a = new JournalEntry(1,"I just climbed a mountain",
				new Date(), new Emotion(Emotion.EmotionalValue.VERY_POSITIVE, "extremely happy!"));
		JournalEntry b = new JournalEntry(2,"I punched a tree when my dad yelled at me",
				new Date(), new Emotion(Emotion.EmotionalValue.VERY_NEGATIVE, "angry as hell!"));
		JournalEntry c = new JournalEntry(3,"I just failed my test",
				new Date(), new Emotion(Emotion.EmotionalValue.MODERATELY_NEGATIVE, "dissapointed"));
		JournalEntry d = new JournalEntry(4,"I just had free cookie",
				new Date(), new Emotion(Emotion.EmotionalValue.SOMEWHAT_POSITIVE, "happy"));
*/
		journalTable = (ListView)findViewById(R.id.entry_table);
		sqlhelper = new JournalDatabaseAdaper(this);
		
		JournalList = (ArrayList<JournalEntry>) sqlhelper.getJournalEntries(null,null,null);
		
		// create a new TableRow
		//createTableRows();
		
		adapter2 = new MyEntryAdapter(this, R.layout.entry_view, JournalList);
				
				
		journalTable.setAdapter(adapter2);
		journalTable.setOnItemClickListener(journalTable.getOnItemClickListener());
				
				
				// this is the click listenier for the list imtems
		journalTable.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View view,
			                int position, long id) {

//			       Object o = listView.getItemAtPosition(position);
//			       String str=(String)o;//As you are using Default String Adapter
//			       Toast.makeText(getBaseContext(),str+position,Toast.LENGTH_SHORT).show();
//			       
			        //We add the index of the item  to a bundle so the next activity can get the data
			       Bundle extras = new Bundle();
					extras.putLong("position", position);
					
					Intent intent = new Intent(JournalTable.this,EntryView.class);
					intent.putExtras(extras);
					startActivity(intent);
			        }

				
			  
			    });
				
			

	}
	/*@SuppressLint("NewApi")
	public void createTableRows()
	{
		counter = 0;
		TableRow row = new TableRow(this);
		row.setId(100);
		row.setBackgroundColor(Color.GRAY);
		row.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		
		//Date column
		TextView date = new TextView(this);
		date.setId(200);
		date.setText("Date");
		date.setTextColor(Color.WHITE);
		date.setPadding(5, 5, 5, 5);
		date.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		row.addView(date);

		//Emotion column
		TextView emotion = new TextView(this);
		emotion.setId(300);
		emotion.setText("Emotion");
		emotion.setTextColor(Color.WHITE);
		emotion.setPadding(5, 5, 5, 5);
		emotion.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		row.addView(emotion);
		
		//Score column
		TextView  DateFormat.getDateInstance(DateFormat.SHORT)e = new TextView(this);
		score.setId(400);
		score.setText("Score");
		score.setTextColor(Color.WHITE);
		date.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
		score.setPadding(5, 5, 5, 5);
		row.addView(score);
		// add the TableRow to the TableLayout
		journalTable.addView(row,new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		for (int curr = 0; curr <  JournalList.size(); curr++){
			 row = new TableRow(this);
			row.setId(100+curr);
			row.setBackgroundColor(Color.GRAY);
			
			row.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
			
			 date = new TextView(this);
			date.setId(200+curr);
			date.setText("" + JournalList.get(curr).getEntryDate().getMonth() + "-" +JournalList.get(curr).getEntryDate().getDate() 
					+"-"+ JournalList.get(curr).getEntryDate().getYear() + " " + JournalList.get(curr).getEntryDate().getHours() +
					":" + JournalList.get(curr).getEntryDate().getMinutes());
			date.setTextColor(Color.WHITE);
			date.setPadding(5, 5, 5, 5);
			date.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
			row.addView(date);

			 emotion = new TextView(this);
			emotion.setText(JournalList.get(curr).getEmotion().getFeeling());
			emotion.setId(300+curr);
			emotion.setText(JournalList.get(curr).getEmotion().getFeeling());
			emotion.setTextColor(Color.WHITE);
			emotion.setPadding(5, 5, 5, 5);
			emotion.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
			row.addView(emotion);
			
			 score = new TextView(this);
			score.setText(String.valueOf(JournalList.get(curr).getEmotion().getEv()));
			score.setId(400+curr);
			String tmp = String.valueOf((JournalList.get(curr).getEmotion().getEv()));
			if(JournalList.get(curr).getEmotion().getEv().ordinal() > 0)
				score.setText("+" + tmp);
			else score.setText(tmp);
			score.setTextColor(Color.WHITE);
			score.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
			score.setPadding(5, 5, 5, 5);
			row.addView(score);
			// add the TableRow to the TableLayout
			journalTable.addView(row,new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			
		}
	}*/
}
