package com.azarena.usfinest.dialogs;

import com.azarena.usfinest.controller.QuizManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DeleteDialogFragment extends DialogFragment {
	
	public static DeleteDialogFragment newInstance(String text){
		DeleteDialogFragment fragment = new DeleteDialogFragment();
		Bundle args = new Bundle();
		args.putString("text", text);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String text = getArguments().getString("text");
	    // TODO Auto-generated method stub
	    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
	    builder.setMessage(text);
	    setCancelable(false);
	    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	QuizManager.get(getActivity()).deleteRowsFromHighScoreTable();
	        	getActivity().finish();
	        }
	    });
	    
	    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        		dismiss();
	        }
	    });
	    
	    return builder.create();
	}

}
