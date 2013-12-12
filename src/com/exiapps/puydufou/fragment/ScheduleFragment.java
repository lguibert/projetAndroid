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
				//Toast.makeText(getActivity().getApplicationContext(), "coucou "+position, Toast.LENGTH_LONG).show();	
				
				//view = inflater.inflate(R.layout.fragment_focus_show, container, false);
				
								
				
				
				AbstractFragmentPagerAdater pagerAdapter = ((MainActivity)getActivity()).getmPagerAdapter();
				
				for (int i = 0; i < pagerAdapter.getCount(); i++) {
					getActivity().getSupportFragmentManager().beginTransaction().remove(pagerAdapter.getItem(i)).commit();
				}
				
				pagerAdapter = new InformationPagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
				
	
				
				((MainActivity)getActivity()).getPager().removeAllViews();
				((MainActivity)getActivity()).getPager().setAdapter(pagerAdapter);
				
				
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
		List<Spectacle> spectacles = (List<Spectacle>) object;
		
		this.listView.setAdapter(new SpectacleAdapter(getActivity(), R.id.spectacleItemName, spectacles));
	}
	
}