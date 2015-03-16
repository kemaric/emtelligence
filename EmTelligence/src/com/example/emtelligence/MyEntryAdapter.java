package com.example.emtelligence;

import java.util.ArrayList;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MyEntryAdapter extends ArrayAdapter<JournalEntry>  {
	private Context context;

	public MyEntryAdapter(Context context, int textViewResourceId, ArrayList<JournalEntry> items) {
		super(context, textViewResourceId, items);
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.entry_view, null);
		}

		JournalEntry item = getItem(position);
		if (item!= null) {

			TextView emotion = (TextView) view.findViewById(R.id.entryEmotionCol);
			TextView date = (TextView) view.findViewById(R.id.entryDateCol);
			TextView score = (TextView) view.findViewById(R.id.entry_score);
			RelativeLayout entryContainer = (RelativeLayout)view.findViewById(R.id.entryContainter);
			
			//ImageView image = (ImageView) view.findViewById(R.id.image);
			//Drawable img ;
			if (item != null) {
				emotion.setText(item.getEmotion().getFeeling());
				date.setText("" + item.getEntryDate().getMonth() + "-" +item.getEntryDate().getDate() 
						+"-"+ item.getEntryDate().getYear() + " " + item.getEntryDate().getHours() +
						":" + item.getEntryDate().getMinutes());
				
				score.setText(String.valueOf(item.getEmotion().getEv().getValue()));
				switch (item.getEmotion().getEv()){
				case VERY_NEGATIVE:
				case MODERATELY_NEGATIVE:
				case SOMEWHAT_NEGATIVE: 
					entryContainer.setBackgroundColor(Color.MAGENTA); // red 
					// img = context.getResources().getDrawable(R.drawable.safty_alert);
					//image.setImageResource(R.drawable.safty_alert);

					break;
				case VERY_POSITIVE:
				case MODERATELY_POSITIVE:
				case SOMEWHAT_POSITIVE: 
					entryContainer.setBackgroundColor(Color.CYAN); // red 

					//image.setImageResource(R.drawable.weather_alert);
					break;
				default:
					entryContainer.setBackgroundColor(Color.GRAY); // red 
					//image.setImageResource(R.drawable.default_alert);

					/* This is a comment. */
					break;

				}

			}
		}

		return view;
	}
}
