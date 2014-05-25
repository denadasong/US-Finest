package com.azarena.usfinest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.azarena.usfinest.controller.QuizManager;
import com.azarena.usfinest.database.HighScore;
import com.azarena.usfinest.dialogs.DeleteDialogFragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HighScoresFragment extends ListFragment {
	
	private List<HighScore> mHighScores;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_high_scores, menu);
		

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
			
		case R.id.menu_item_delete:
			DeleteDialogFragment dialog = DeleteDialogFragment.newInstance(getString(R.string.delete_high_scores_dialog));
			dialog.show(getActivity().getSupportFragmentManager(), "ResultDialogFragment");
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		mHighScores = new ArrayList<HighScore>();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		View v = inflater.inflate(R.layout.empty_list_stub, parent, false);
		ListView listView = (ListView)v.findViewById(android.R.id.list);
		listView.setEmptyView(v.findViewById(android.R.id.empty));
		
		// QuizManager.get(getActivity()).deleteDatabase();
		
		
		if(databaseExists()){
        	mHighScores = QuizManager.get(getActivity()).getHighScores();
        	HighScoreAdapter adapter = new HighScoreAdapter(mHighScores);
    		setListAdapter(adapter);
		}
        else{
        }
		
		
		return v;
		
	}
	
	private class HighScoreAdapter extends ArrayAdapter<HighScore> {
		public HighScoreAdapter(List<HighScore> highScores) {
			super(getActivity(), 0, highScores);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.high_score_item, null);
				convertView.setOnClickListener(null);
			}
			// Configure the view for this Crime
			HighScore hs = getItem(position);
			
			TextView scoreTextView = (TextView)convertView.findViewById(R.id.presidents_highScoreTextView);
			TextView dateTextView = (TextView)convertView.findViewById(R.id.presidents_highScoreDateTextView);
			
			scoreTextView.setText("Score: " + String.valueOf(hs.getValue()));
			dateTextView.setText(hs.getDate());
			
			return convertView;
		}
		
	}
	
	private boolean databaseExists(){
		File database=getActivity().getApplicationContext().getDatabasePath("presidents_quiz.sqlite");

		if (!database.exists()) {
		    return false;
		} else {
		    return true;
		}
		
	}

}
