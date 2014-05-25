package com.azarena.usfinest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.azarena.usfinest.controller.QuizManager;
import com.azarena.usfinest.database.HighScore;
import com.azarena.usfinest.database.President;
import com.azarena.usfinest.dialogs.ResultDialogFragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameFragment extends Fragment {
	public static String TAG = "GAME_FRAGMENT";
	
	public static int TIMER_VALUE;
	public static int TOTAL_TIMER_VALUE;
	
	private List<President> mPresidents = new ArrayList<President>();
	private List<President> mPresidentsCopy = new ArrayList<President>();

	private ImageView mPresidentImageView;
	private Button mAnswerButton1, mAnswerButton2, mAnswerButton3;
	private TextView mTimeTextView, mScoreTextView;
	
	private String mCorrectAnswer;
	private int correctAnswerResourceId;
	private int mTime = 11;
	private int mTotalTime = 101;
	private int mScore = 0;
	
	private CountDownTimer mCountDownTimer;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		mPresidents = QuizManager.get(getActivity()).getPresidents();
		mPresidentsCopy = mPresidents;
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@SuppressWarnings("deprecation")
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		View v = inflater.inflate(R.layout.fragment_game, parent, false);
		
		mScoreTextView = (TextView)v.findViewById(R.id.presidents_scoreTextView);
		mTimeTextView = (TextView)v.findViewById(R.id.presidents_timeTextView);
		
		mScoreTextView.setText("Score: 0");
		
		mPresidentImageView = (ImageView)v.findViewById(R.id.president_image);
		int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		mPresidentImageView.getLayoutParams().width = Math.round(0.8f * width);
		
		double screenInches = getScreenInches();
		if(screenInches < 7)
			mPresidentImageView.getLayoutParams().height = Math.round(0.3f * height);
		else
			mPresidentImageView.getLayoutParams().height = Math.round(0.5f * height);


		mAnswerButton1 = (Button)v.findViewById(R.id.president_answerButton1);
		mAnswerButton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTime = 10;
				if(mAnswerButton1.getText().equals(mCorrectAnswer)){
					int pointsWon = 5+ GameFragment.TIMER_VALUE;
					Toast.makeText(getActivity().getApplicationContext(), "Correct! " + pointsWon + " Points", 
							   Toast.LENGTH_SHORT).show();
					
					mScore = mScore + pointsWon;
					mScoreTextView.setText("Score: " + mScore);
					
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
				else{
					if(mScore >= 3)
						mScore = mScore - 3;
					mScoreTextView.setText("Score: " + mScore);
					Toast.makeText(getActivity().getApplicationContext(), "Incorrect! -3 Points", 
							   Toast.LENGTH_SHORT).show();
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
				
			}
		});
		
		mAnswerButton2 = (Button)v.findViewById(R.id.president_answerButton2);
		mAnswerButton2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTime = 10;
				if(mAnswerButton2.getText().equals(mCorrectAnswer)){
					int pointsWon = 5+ GameFragment.TIMER_VALUE;
					Toast.makeText(getActivity().getApplicationContext(), "Correct! " + pointsWon + " Points", 
							   Toast.LENGTH_SHORT).show();
					
					mScore = mScore + pointsWon;
					mScoreTextView.setText("Score: " + mScore);
					
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
				else{
					if(mScore >= 3)
						mScore = mScore - 3;
					mScoreTextView.setText("Score: " + mScore);
					Toast.makeText(getActivity().getApplicationContext(), "Incorrect! -3 Points", 
							   Toast.LENGTH_SHORT).show();
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
			}
		});
		
		mAnswerButton3 = (Button)v.findViewById(R.id.president_answerButton3);
		mAnswerButton3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTime = 10;
				if(mAnswerButton3.getText().equals(mCorrectAnswer)){
					int pointsWon = 5+ GameFragment.TIMER_VALUE;
					Toast.makeText(getActivity().getApplicationContext(), "Correct! " + pointsWon + " Points", 
							   Toast.LENGTH_SHORT).show();
					
					mScore = mScore + pointsWon;
					mScoreTextView.setText("Score: " + mScore);
					
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
				else{
					if(mScore >= 3)
						mScore = mScore - 3;
					mScoreTextView.setText("Score: " + mScore);
					Toast.makeText(getActivity().getApplicationContext(), "Incorrect! -3 Points", 
							   Toast.LENGTH_SHORT).show();
					if(mPresidents.size() == 0){
						showDialog();
					}
					else{
						displayQuestion();
					}
				}
			}
		});
		
		displayQuestion();
		startNewTimer();
		
		return v;
		
	}
	
	private double getScreenInches() {
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width=dm.widthPixels;
		int height=dm.heightPixels;
		int dens=dm.densityDpi;
		double wi=(double)width/(double)dens;
		double hi=(double)height/(double)dens;
		double x = Math.pow(wi,2);
		double y = Math.pow(hi,2);
		double screenInches = Math.sqrt(x+y);
		
		return screenInches;
	}

	private void displayQuestion(){
		
		List<President> mQuestionPresidents = new ArrayList<President>();
		int randomQuestionNumber = 0;
		
		if(mPresidents.size() == 1){
			mCorrectAnswer = mPresidents.get(0).getPresident();
			correctAnswerResourceId = mPresidents.get(0).getResourceId();
		}
		else{
			randomQuestionNumber = getRandomNumber(0, mPresidents.size());
			mCorrectAnswer = mPresidents.get(randomQuestionNumber).getPresident();
			correctAnswerResourceId = mPresidents.get(randomQuestionNumber).getResourceId();
		}
		
		int randomWrongAnswer1;
		do{
			randomWrongAnswer1 = getRandomNumber(0, mPresidentsCopy.size());
		}while(mPresidentsCopy.get(randomWrongAnswer1).getResourceId() == mPresidents.get(randomQuestionNumber).getResourceId());
		
		int randomWrongAnswer2;
		do{
			randomWrongAnswer2 = getRandomNumber(0, mPresidentsCopy.size());
		}while((mPresidentsCopy.get(randomWrongAnswer2).getResourceId() == mPresidents.get(randomQuestionNumber).getResourceId())
				||(mPresidentsCopy.get(randomWrongAnswer2).getResourceId() == mPresidentsCopy.get(randomWrongAnswer1).getResourceId()));
		
		mQuestionPresidents.add(mPresidents.get(randomQuestionNumber));
		mQuestionPresidents.add(mPresidentsCopy.get(randomWrongAnswer1));
		mQuestionPresidents.add(mPresidentsCopy.get(randomWrongAnswer2));
		
		int answer1 = getRandomNumber(0, mQuestionPresidents.size());
		int answer2;
		do{
			answer2 = getRandomNumber(0, mQuestionPresidents.size());
		}while(answer1 == answer2);
		
		int answer3 = 0;
		for(int i = 0; i<mQuestionPresidents.size(); i++){
			if((answer1 != i) && (answer2 != i))
				answer3 = i;
		}
		
		mPresidentImageView.setImageResource(correctAnswerResourceId);
		
		List<President> mPresidentsTemp = new ArrayList<President>();
		for(int i=0; i<mPresidents.size(); i++){
			if(!mPresidents.get(i).getPresident().equals(mCorrectAnswer)){
				mPresidentsTemp.add(mPresidents.get(i));
			}
		}
		mPresidents = mPresidentsTemp;
		
		mAnswerButton1.setText(mQuestionPresidents.get(answer1).getPresident());
		mAnswerButton2.setText(mQuestionPresidents.get(answer2).getPresident());
		mAnswerButton3.setText(mQuestionPresidents.get(answer3).getPresident());
		
	
	}
	
	private int getRandomNumber(int minValue, int maxValue){
		Random randomGenerator = new Random();
	    int randomInt = randomGenerator.nextInt(maxValue - minValue) + minValue;
		
		return randomInt;
		
	}
	
	private void startNewTimer(){
		mCountDownTimer = new CountDownTimer(101000, 1000) {

	        public void onTick(long millisUntilFinished) {
	        	mTotalTime --;
	        	if(mTime == 0)
					   mTime = 0;
				   else {
					   mTime --;
				   }
	        	GameFragment.TIMER_VALUE = mTime;
	        	GameFragment.TOTAL_TIMER_VALUE = mTotalTime;
	        	mTimeTextView.setText("Time: " + mTime + " (" + mTotalTime + ")");
	        }

	        public void onFinish() {
	        	
	        	mTimeTextView.setText("Game Over!");
	        	showDialog();
	        }
	     }.start();
	}
	
	@SuppressLint("SimpleDateFormat")
	public void showDialog(){
		mCountDownTimer.cancel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		String date = sdf.format(d);
		
		HighScore hs = new HighScore();
		hs.setDate(date);
		hs.setValue(mScore);
		
		QuizManager.get(getActivity()).saveHighScore(hs);
		
		ResultDialogFragment dialog = ResultDialogFragment.newInstance(getString(R.string.game_result, String.valueOf(mScore)));
		dialog.show(getActivity().getSupportFragmentManager(), "ResultDialogFragment");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		mCountDownTimer.cancel();
		getActivity().finish();
	}

}
