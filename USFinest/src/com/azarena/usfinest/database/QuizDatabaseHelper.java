package com.azarena.usfinest.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "presidents_quiz.sqlite";
	private static final int VERSION = 1;
	
	private static final String TABLE_HIGH_SCORE = "high_score";

	private static final String COLUMN_HIGH_SCORE_VALUE = "value";
	private static final String COLUMN_HIGH_SCORE_DATE = "date";
	
	private List<HighScore> highScoreList;
	
	public QuizDatabaseHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table high_score ("
				+ "id integer primary key autoincrement, "
				+ "value integer, " + "date varchar(25))");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public long insertHighScore(HighScore highScore) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_HIGH_SCORE_DATE, highScore.getDate());
		cv.put(COLUMN_HIGH_SCORE_VALUE, highScore.getValue());
		
		return getWritableDatabase().insert(TABLE_HIGH_SCORE, null, cv);
	}
	
	public List<HighScore> queryHighScores() {
		Cursor cursor = getReadableDatabase().query(TABLE_HIGH_SCORE, null, null,
				null, null, null, null);
		highScoreList = new ArrayList<HighScore>();
		HighScore highScore;
		if (cursor.moveToFirst()) {
			while (cursor.isAfterLast() == false) {
				highScore = new HighScore();

				String date = cursor.getString(cursor
						.getColumnIndex(COLUMN_HIGH_SCORE_DATE));
				int value = cursor.getInt(cursor
						.getColumnIndex(COLUMN_HIGH_SCORE_VALUE));

				highScore.setDate(date);
				highScore.setValue(value);

				highScoreList.add(highScore);
				cursor.moveToNext();
			}
		}
		
		Collections.sort(highScoreList);
		
		return highScoreList;
	}
	
	public void deleteAllFromHighScoreTable() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_HIGH_SCORE, null, null);
	}

}
