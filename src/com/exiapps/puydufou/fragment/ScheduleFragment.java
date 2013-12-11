package com.exiapps.puydufou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exiapps.puydufou.R;

public class ScheduleFragment extends AbstractFragment {

	public ScheduleFragment(){
		this.title = "Planning";
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view =  inflater.inflate(R.layout.page_gauche_layout, container, false);
		
		return view;
	}

	@Override
	public void refresh() {

	}
	
}
