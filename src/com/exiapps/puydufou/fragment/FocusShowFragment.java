package com.exiapps.puydufou.fragment;

import com.exiapps.puydufou.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FocusShowFragment extends AbstractFragment {
	
	public FocusShowFragment(){
		this.title = "Spectacle d�tails";
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		
		view =  inflater.inflate(R.layout.fragment_focus_show, container, false);
		
		TextView tv = (TextView) view.findViewById(R.id.nameShow);
		
		tv.setText("La l�gende de martin");
		
		return view;
		
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
