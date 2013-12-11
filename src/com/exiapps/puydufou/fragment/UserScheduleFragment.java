package com.exiapps.puydufou.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.SpectacleManager;

public class UserScheduleFragment extends AbstractFragment {

	private ListView listView;
	private SpectacleManager spectacleManager;	
	
	
	public UserScheduleFragment(){
		this.title = "MON PLANNING";
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view =  inflater.inflate(R.layout.fragment_userschedule, container, false);
		
		listView = (ListView) view.findViewById(R.id.userScheduleList);
		
		this.refresh();
		
		return view;
	}
	
	@Override
	public void refresh() {
				
	}

}
