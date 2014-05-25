package com.azarena.usfinest;

import android.support.v4.app.Fragment;

public class HighScoresActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		setTitle("High Scores");
		return new HighScoresFragment();
	}

}
