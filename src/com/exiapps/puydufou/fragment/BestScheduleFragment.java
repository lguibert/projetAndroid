package com.exiapps.puydufou.fragment;


import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.RepresentationArrayAdapter;
import com.exiapps.puydufou.bestschedule.Representation;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;



public class BestScheduleFragment extends AbstractFragment implements OnReceiveListener {

	private SpectacleManager spectacleManager;
	private ListView listView;
	
	public BestScheduleFragment(){
		this.title = "MEILLEUR PLANNING";
		
		this.spectacleManager = new SpectacleManager();
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(com.exiapps.puydufou.R.layout.fragment_bestschedule, container, false);
		
		listView = (ListView) view.findViewById(R.id.listViewBestSchedule);
		
		this.spectacleManager.getAllDetailAsync();
		this.spectacleManager.setOnReceiveListener(this);
		
		return view;
	}



	@Override
	public void refresh() {
		
	}



	@Override
	public void OnReceive(Object object) {
		if(!isDetached()){		
		
			List<Spectacle> spectacles = (List<Spectacle>) object;
			
			List<Representation> representations = this.spectacleManager.getBest(spectacles);
			
			this.listView.setAdapter(new RepresentationArrayAdapter(getActivity(), R.id.spectacleItemName, representations));
		}
	}

}
