package com.example.emtelligence;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.emtelligence.EmotionalValue;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;

//private final Context outcontext;

public class JournalDatabaseAdaper  {

	JournalDBHelper journalHelper;//helper that is used to access database for outside classes
	public JournalDatabaseAdaper(Context context){

		journalHelper = new JournalDBHelper(context);

	}

	public long addJournalEntry(JournalEntry entry){
		//for logging
		Log.d("addJournalEntry", entry.toString()); 
		// 1. get reference to writable DB
		SQLiteDatabase db = journalHelper.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(JournalDBHelper.DATE_TIME,entry.getEntryDate().toString());  
		values.put(JournalDBHelper.EMOTION, entry.getEmotion().getFeeling()); 
		values.put(JournalDBHelper.DESCRIPTION, entry.getDescription()); 
		values.put(JournalDBHelper.SCORE, entry.getEmotion().getEv().getValue()); 
		// 3. insert
		long id =db.insert(JournalDBHelper.TABLE_NAME, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

		// 4. close
		db.close(); 
		return id;
	}

	public JournalEntry getJournalEntry(int id){
		Log.d("getJournalEntry", "getting entry " + id + " from table.");
		// 1. get reference to readable DB
		SQLiteDatabase db = journalHelper.getReadableDatabase();

		// 2. build query
		Cursor cursor = 
				db.query(JournalDBHelper.TABLE_NAME, // a. table
						JournalDBHelper.COLUMNS, // b. column names
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
		int emotionInd = cursor.getColumnIndex(JournalDBHelper.EMOTION);
		int dateInd = cursor.getColumnIndex(JournalDBHelper.DATE_TIME);
		int descInd = cursor.getColumnIndex(JournalDBHelper.DESCRIPTION);
		int idInd = cursor.getColumnIndex(JournalDBHelper.ENTRYID);
		int score = cursor.getColumnIndex(JournalDBHelper.SCORE);
		jEntry = new JournalEntry();
		jEntry.setEntryId(cursor.getInt(idInd));
		jEntry.setEntryDate(jEntry.getEntryDateObject(cursor.getString(dateInd).toString()));
		int emotionScore = Integer.valueOf(cursor.getString(score));
		String feeling = cursor.getString(emotionInd);
		EmotionalValue ev = EmotionalValue.valueOf(emotionScore);
		jEntry.setEmotion(new Emotion(ev,feeling));		jEntry.setDescription(cursor.getString(descInd));
		//log 
		Log.d("getJournalEntry("+id+")", jEntry.toString());

		// 5. return journal entry
		return jEntry;
	}

	public void deleteBook(JournalEntry jEntry) {
		Log.d("deleteBook", jEntry.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = journalHelper.getWritableDatabase();

		// 2. delete
		db.delete(JournalDBHelper.TABLE_NAME, //table name
				JournalDBHelper.ENTRYID+" = ?",  // selections
				new String[] { String.valueOf(jEntry.getEntryId()) }); //selections args

		// 3. close
		db.close();

		//log
		Log.d("deleteJournalEntry", jEntry.toString());

	}


	public int updateBook(JournalEntry jEntry) {

		// 1. get reference to writable DB
		Log.d("updateBook", jEntry.toString());
		SQLiteDatabase db = journalHelper.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(JournalDBHelper.DATE_TIME,jEntry.getEntryDate().toString());  
		values.put(JournalDBHelper.EMOTION, jEntry.getEmotion().getFeeling()); 
		values.put(JournalDBHelper.DESCRIPTION, jEntry.getDescription()); 
		values.put(JournalDBHelper.SCORE, String.valueOf(jEntry.getEmotion().getEv().getValue())); 

		// 3. updating row
		int i = db.update(JournalDBHelper.TABLE_NAME, //table
				values, // column/value
				JournalDBHelper.ENTRYID+" = ?", // selections
				new String[] { String.valueOf(jEntry.getEntryId()) }); //selection args

		// 4. close
		db.close();

		return i;

	}

	public List<JournalEntry> getJournalEntries(String[]fields,String where,String[]whereArgs) {

		Log.d("getAllJournalEntries","Getting all of the entries in the journal table.");
		List<JournalEntry> jEntryList = new ArrayList<JournalEntry>();
		Cursor cursor;


		// 1. get reference to writable DB
		SQLiteDatabase db = journalHelper.getWritableDatabase();
		if(fields != null){
			if(where != null)
				cursor = db.query(JournalDBHelper.TABLE_NAME, fields, where, whereArgs, null, null, null);
			else
				cursor = db.query(JournalDBHelper.TABLE_NAME, fields, null, null, null, null, null);
		}else{
			cursor = db.query(JournalDBHelper.TABLE_NAME, JournalDBHelper.COLUMNS, null, null, null, null, null);
		}



		// 3. go over each row, build entry and add it to list
		//MAJOR BUG HERE for when the other parameters are null
		JournalEntry jEntry = null;
		if (cursor.moveToFirst()) {
			do {
				int emotionInd = cursor.getColumnIndex(JournalDBHelper.EMOTION);
				int dateInd = cursor.getColumnIndex(JournalDBHelper.DATE_TIME);
				int descInd = cursor.getColumnIndex(JournalDBHelper.DESCRIPTION);
				int idInd = cursor.getColumnIndex(JournalDBHelper.ENTRYID);
				int score = cursor.getColumnIndex(JournalDBHelper.SCORE);
				jEntry = new JournalEntry();
				jEntry.setEntryId(cursor.getInt(idInd));
				String entrydate =cursor.getString(dateInd).toString();
				Date entryDate =  jEntry.getEntryDateObject(entrydate); 
				jEntry.setEntryDate(entryDate);
				int emotionScore = Integer.valueOf(cursor.getString(score));
				String feeling = cursor.getString(emotionInd);
				EmotionalValue ev = EmotionalValue.valueOf(emotionScore);
				jEntry.setEmotion(new Emotion(ev,feeling));
				jEntry.setDescription(cursor.getString(descInd));
				//jEntry.setScore(Integer.parseInt(cursor.getString(4)));

				// Add jEntry to list
				jEntryList.add(jEntry);
			} while (cursor.moveToNext());
		}

		Log.d("getAllJournalEntries()", jEntryList.toString());

		return jEntryList;
	}

	// closing database
	public void closeDB() {
		Log.d("closeDB", "Closing the database.");
		SQLiteDatabase db = journalHelper.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	private static class JournalDBHelper extends SQLiteOpenHelper{
		// Database Version
		private static final int DATABASE_VERSION = 5;
		// Database Name
		private static final String DATABASE_NAME = "JournalDB";

		// Books table name
		private static final String TABLE_NAME = "journalentries";

		private static final String ENTRYID = "entryID";
		private static final String DATE_TIME = "date_time";
		private static final String	EMOTION = "emotion";
		private static final String SCORE = "score";
		private static final String DESCRIPTION = "descripton";
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
		private static final String[] COLUMNS = {ENTRYID,DATE_TIME,EMOTION,DESCRIPTION,SCORE};

		private Context context;
		public JournalDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context = context;
			Log.d("JournalDBHelper", "constructor called");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// SQL statement to create book table
			Log.d("onCreate", "Creating the local journal table.");
			String CREATE_JOURNAL_TABLE = "CREATE TABLE " + TABLE_NAME+" (" +
					ENTRYID +" INTEGER PRIMARY KEY AUTOINCREMENT," + 
					DATE_TIME+" DATE,"+
					EMOTION+" VARCHAR(255)," + DESCRIPTION+" TEXT," +
					SCORE+" INTEGER);";

			// create entries table
			try{
				db.execSQL(CREATE_JOURNAL_TABLE);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drop older books table if existed
			Log.d("onUpgrade", "Upgrading the journal table.");

			try {
				db.execSQL(DROP_TABLE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// create fresh books table
			this.onCreate(db);
		}


	}



}
