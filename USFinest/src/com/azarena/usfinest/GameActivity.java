package com.azarena.usfinest;

import android.support.v4.app.Fragment;

public class GameActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		setTitle("Play Quiz");
		return new GameFragment();
	}

}
