package com.exiapps.puydufou.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.RestaurantAdapter;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.ServiceManager;
import com.exiapps.puydufou.model.entities.Service;

public class RestaurantFragment extends AbstractFragment implements OnReceiveListener {

	private ListView listView;
	private ServiceManager serviceManager;

	public RestaurantFragment() {
		this.title = "Restaurant";
		this.serviceManager = new ServiceManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_restaurant, container, false);

		listView = (ListView) view.findViewById(R.id.restaurantList);

		this.refresh();

		return view;
	}

	@Override
	public void refresh() {
		this.serviceManager.getAllRestaurantDetailAsync();
		this.serviceManager.setOnReceiveListener((OnReceiveListener) this);
	}

	@Override
	public void OnReceive(Object object) {
		if(!isDetached()){
			List<Service> services = (List<Service>) object;

			this.listView.setAdapter(new RestaurantAdapter(getActivity(), R.id.restaurantItemName, services));
		}		
	}

}