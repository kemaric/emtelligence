package com.example.emtelligence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.emtelligence.Emotion.EmotionalValue;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;

//private final Context outcontext;

public class MySQLiteHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "JournalDB";

	 // Books table name
    private static final String TABLE_JOURNAL_ENTRIES = "journal_entries";
	
    private static final String ENTRYID = "entryID";
	private static final String DATE_TIME = "date/time";
	private static final String	EMOTION = "emotion";
	private static final String SCORE = "score";
	private static final String DESCRIPTION = "descripton";
	private static final String[] COLUMNS = {ENTRYID,DATE_TIME,EMOTION,DESCRIPTION,SCORE};

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_JOURNAL_TABLE = "CREATE TABLE entries ( " +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"date/time TEXT, "+
				"feeling TEXT, " + "description TEXT" +
						"score INTEGER)";

		// create books table
		db.execSQL(CREATE_JOURNAL_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older books table if existed
		db.execSQL("DROP TABLE IF EXISTS entries");

		// create fresh books table
		this.onCreate(db);
	}

	public void addJournalEntry(JournalEntry entry){
		//for logging
		Log.d("addJournalEntry", entry.toString()); 
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(DATE_TIME,entry.getEntryDate().toString());  
		values.put(EMOTION, entry.getEmotion().getFeeling()); 
		values.put(DESCRIPTION, entry.getDescription()); 
		values.put(SCORE, String.valueOf(entry.getEmotion().getEv())); 
		// 3. insert
		db.insert(TABLE_JOURNAL_ENTRIES, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values
		// 4. close
		db.close(); 
	}
	
	public JournalEntry getJournalEntry(int id){
		 
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(TABLE_JOURNAL_ENTRIES, // a. table
	            COLUMNS, // b. column names
	            " id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build Journal Entry object
	    JournalEntry jEntry = new JournalEntry();
	    jEntry.setEntryId(Integer.parseInt(cursor.getString(0)));
	    jEntry.setEntryDate(Date.valueOf(cursor.getString(1)));
	    jEntry.setEmotion(new Emotion(Emotion.EmotionalValue.valueOf(Integer.parseInt(cursor.getString(4))),cursor.getString(2)));
	    jEntry.setDescription(cursor.getString(3));
	    //jEntry.setScore(Integer.parseInt(cursor.getString(4)));
	 
	    //log 
	Log.d("getJournalEntry("+id+")", jEntry.toString());
	 
	    // 5. return journal entry
	    return jEntry;
	}

	public void deleteBook(JournalEntry jEntry) {
		 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_JOURNAL_ENTRIES, //table name
                ENTRYID+" = ?",  // selections
                new String[] { String.valueOf(jEntry.getEntryId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    Log.d("deleteJournalEntry", jEntry.toString());
 
    }
	
	
	public int updateBook(JournalEntry jEntry) {
		 
	    // 1. get reference to writable DB
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    // 2. create ContentValues to add key "column"/value
	    ContentValues values = new ContentValues();
	    values.put(DATE_TIME,jEntry.getEntryDate().toString());  
		values.put(EMOTION, jEntry.getEmotion().getFeeling()); 
		values.put(DESCRIPTION, jEntry.getDescription()); 
		values.put(SCORE, String.valueOf(jEntry.getEmotion().getEv())); 
	    
	    // 3. updating row
	    int i = db.update(TABLE_JOURNAL_ENTRIES, //table
	            values, // column/value
	            ENTRYID+" = ?", // selections
	            new String[] { String.valueOf(jEntry.getEntryId()) }); //selection args
	 
	    // 4. close
	    db.close();
	 
	    return i;
	 
	}
	
	public List<JournalEntry> getAllJournalEntries() {
	       List<JournalEntry> jEntryList = new ArrayList<JournalEntry>();
	 
	       // 1. build the query
	       String query = "SELECT  * FROM " + TABLE_JOURNAL_ENTRIES;
	 
	       // 2. get reference to writable DB
	       SQLiteDatabase db = this.getWritableDatabase();
	       Cursor cursor = db.rawQuery(query, null);
	 
	       // 3. go over each row, build entry and add it to list
	       JournalEntry jEntry = null;
	       if (cursor.moveToFirst()) {
	           do {
	        	   jEntry = new JournalEntry();
	        	   jEntry.setEntryId(Integer.parseInt(cursor.getString(0)));
		       	    jEntry.setEntryDate(Date.valueOf(cursor.getString(1)));
		       	    jEntry.setEmotion(new Emotion(Emotion.EmotionalValue.valueOf(Integer.parseInt(cursor.getString(4))),cursor.getString(2)));
		       	    jEntry.setDescription(cursor.getString(3));
		       	    //jEntry.setScore(Integer.parseInt(cursor.getString(4)));
	 
	               // Add jEntry to list
	               jEntryList.add(jEntry);
	           } while (cursor.moveToNext());
	       }
	 
	       Log.d("getAllJournalEntries()", jEntryList.toString());
	 
	       return jEntryList;
	   }
}
