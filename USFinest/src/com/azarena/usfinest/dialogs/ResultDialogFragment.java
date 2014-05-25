package com.azarena.usfinest.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ResultDialogFragment extends DialogFragment {
	
	public static ResultDialogFragment newInstance(String text){
		ResultDialogFragment fragment = new ResultDialogFragment();
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
	    setCancelable(false);
	    builder.setMessage(text);
	    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	if(getActivity().getClass().getSimpleName().equals("MenuActivity"))
	        		dismiss();
	        	else{
	        		getActivity().finish();
	        	}

	        }
	    });
	    
	    return builder.create();
	}

}
