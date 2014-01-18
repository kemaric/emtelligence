package com.example.emtelligence;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;

public class MySQLiteHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "JournalDB";

	private static final String DATE_TIME = "date/time";
	private static final String	EMOTION = "emotion";
	private static final String SCORE = "score";
	private static final String[] COLUMNS = {DATE_TIME,EMOTION,SCORE};

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_JOURNAL_TABLE = "CREATE TABLE entries ( " +
				"id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				"date/time TEXT, "+
				"feeling TEXT, " + "score INTEGER)";

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
		values.put(SCORE, String.valueOf(entry.getEmotion().getEv().getValue())); 
		// 3. insert
		db.insert(, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		// 4. close
		db.close(); 
	}
}
