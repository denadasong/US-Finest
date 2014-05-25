package com.azarena.usfinest;

import com.azarena.usfinest.dialogs.ResultDialogFragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

	private Button mNewGameButton, mHighScoresButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_menu, menu);

	}

	@TargetApi(11)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_share:
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("text/plain");
			StringBuilder sb = new StringBuilder();
			sb.append("<p>" + getString(R.string.app_share_text1) + "</p>")
					.append("<p>" + getString(R.string.app_share_text2)
							+ "</p>")
					.append("<p>" + getString(R.string.app_share_text3)
							+ "</p>")
					.append("<p>" + getString(R.string.app_share_text4)
							+ "</p>")
					.append("<p>" + getString(R.string.app_share_text5)
							+ "</p>");

			i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(sb.toString())
					.toString());

			i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name_share));
			i = Intent.createChooser(i, getString(R.string.send_info));
			startActivity(i);

			return true;
		case R.id.menu_item_app_info:
			ResultDialogFragment dialog = ResultDialogFragment
					.newInstance(getString(R.string.app_info_text));
			dialog.show(getActivity().getSupportFragmentManager(),
					"InfoDialogFragment");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_menu, parent, false);

		// QuizManager.get(getActivity()).deleteDatabase();

		mNewGameButton = (Button) v.findViewById(R.id.president_newGameButton);
		mNewGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), GameActivity.class);
				startActivity(i);
			}
		});

		mHighScoresButton = (Button) v
				.findViewById(R.id.presidents_highScoresButton);
		mHighScoresButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), HighScoresActivity.class);
				startActivity(i);
			}
		});

		return v;
	}

}
