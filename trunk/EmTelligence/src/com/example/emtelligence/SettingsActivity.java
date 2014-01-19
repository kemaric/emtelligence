package com.example.emtelligence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_entry);
				
		TextView txt1 = (TextView)findViewById(R.id.reminder_text);   
		Typeface mFont = Typeface.createFromAsset(getAssets(), "Lucida Handwriting.ttf");		
		txt1.setTypeface(mFont);
		
		
	}
}
