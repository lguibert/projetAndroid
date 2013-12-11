package com.exiapps.puydufou.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.SpectacleAdapter;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;

public class ScheduleFragment extends AbstractFragment implements OnReceiveListener{

	private ListView listView;
	private SpectacleManager spectacleManager;
	
	public ScheduleFragment(){
		this.title = "Planning";
		this.spectacleManager = new SpectacleManager();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view =  inflater.inflate(R.layout.fragment_schedule, container, false);
		
		listView = (ListView) view.findViewById(R.id.scheduleList);
		
		this.refresh();
		
		return view;
	}

	@Override
	public void refresh() {
		this.spectacleManager.getAllDetailAsync();
		this.spectacleManager.setOnReceiveListener((OnReceiveListener) this);
	}


	@Override
	public void OnReceive(Object object) {
		List<Spectacle> spectacles = (List<Spectacle>) object;
		
		this.listView.setAdapter(new SpectacleAdapter(getActivity(), R.id.spectacleItemName, spectacles));
	}
	
}