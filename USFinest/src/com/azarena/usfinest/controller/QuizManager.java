package com.azarena.usfinest.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.azarena.usfinest.R;
import com.azarena.usfinest.database.HighScore;
import com.azarena.usfinest.database.President;
import com.azarena.usfinest.database.QuizDatabaseHelper;

import android.content.Context;

public class QuizManager {
	
	private QuizDatabaseHelper mHelper;
	private static QuizManager sFestivalManager;
    private Context mAppContext;
    private List<President> mPresidents;
    private List<HighScore> mHighScores;
    
    
    private QuizManager(Context appContext) {
        mAppContext = appContext;
        mHelper = new QuizDatabaseHelper(mAppContext);
        mPresidents = getPresidentResources();
        mHighScores = mHelper.queryHighScores();
    }
    
    public static QuizManager get(Context c) {
        if (sFestivalManager == null) {
            sFestivalManager = new QuizManager(c.getApplicationContext());
        }
        return sFestivalManager;
    }
    
    public List<President> getPresidents(){
    	return mPresidents;
    }
    
    public boolean saveHighScore(HighScore highScore) {
    	try {
            mHelper.insertHighScore(highScore);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<HighScore> getHighScores() {
    	//Arrays.sort(mHighScores.toArray());
    	mHighScores = mHelper.queryHighScores();
        return mHighScores;
    }
    
    public void deleteRowsFromHighScoreTable(){
    	mHelper.deleteAllFromHighScoreTable();
    }
	
	public void deleteDatabase(){
		mHelper.close();
		mAppContext.deleteDatabase("presidents_quiz.sqlite");
	}
	
	private List<President> getPresidentResources() {

		List<President> presidents = new ArrayList<President>();
		President president = null;
		final R.drawable drawableResources = new R.drawable();
		final Class<R.drawable> c = R.drawable.class;
		final Field[] fields = c.getDeclaredFields();

		for (int i = 0, max = fields.length; i < max; i++) {
		    final int resourceId;
		    try {
		        resourceId = fields[i].getInt(drawableResources);
		        String resourceName = mAppContext.getResources().getResourceEntryName(resourceId);
		        if(resourceName.startsWith("p_")){
		        	String presidentName = resourceName.replace("p_", "");
		        	presidentName = presidentName.replace("_", " ");
		        	presidentName = WordUtils.capitalize(presidentName);
		        	president = new President(resourceId, presidentName);
		        	presidents.add(president);
		        }
		    } catch (Exception e) {
		        continue;
		    }
		}
		return presidents;
	}

}
