package com.exiapps.puydufou.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.AbstractFragmentPagerAdater;
import com.exiapps.puydufou.adapters.FocusShowFragmentAdapter;
import com.exiapps.puydufou.adapters.InformationPagerAdapter;
import com.exiapps.puydufou.adapters.MapPagerAdapter;
import com.exiapps.puydufou.adapters.SpectacleAdapter;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.SpectacleManager;
import com.exiapps.puydufou.model.entities.Spectacle;
import com.exiapps.puydufou.view.FocusShowActivity;
import com.exiapps.puydufou.view.MainActivity;

public class ScheduleFragment extends AbstractFragment implements OnReceiveListener{

	private ListView listView;
	private SpectacleManager spectacleManager;
	
	public ScheduleFragment(){
		this.title = "Planning";
		this.spectacleManager = new SpectacleManager();
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
			Bundle savedInstanceState) {
		
		view =  inflater.inflate(R.layout.fragment_schedule, container, false);
		
		listView = (ListView) view.findViewById(R.id.scheduleList);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent(getActivity().getApplicationContext(), FocusShowActivity.class);	
				i.putExtra("id", ((Spectacle) listView.getAdapter().getItem(position)).getId());				
				startActivity(i);
			}			
		});
		
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
		if(!isDetached()){
			List<Spectacle> spectacles = (List<Spectacle>) object;
			
			this.listView.setAdapter(new SpectacleAdapter(getActivity(), R.id.spectacleItemName, spectacles));
		}		
	}
	
}