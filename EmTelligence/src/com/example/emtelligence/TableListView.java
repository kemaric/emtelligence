package com.example.emtelligence;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;

public class TableListView extends ListActivity {
	
	private ListView lv;
	List<JournalEntry> JournalList;
	JournalDatabaseAdaper sqlhelper;
	int counter = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal_table);

        lv = (ListView) findViewById(R.id.entry_table);

        // Instanciating an array list (you don't need to do this, 
        // you already have yours).
        JournalList = new ArrayList<JournalEntry>();
        

        // This is the array adapter, it takes the context of the activity as a 
        // first parameter, the type of list view as a second parameter and your 
        // array as a third parameter.
        ArrayAdapter<JournalEntry> arrayAdapter = new ArrayAdapter<JournalEntry>(
                this, 
                android.R.layout.simple_list_item_1,
                JournalList );

        lv.setAdapter(arrayAdapter); 
        

    }

   

    @Override 
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    }
}

