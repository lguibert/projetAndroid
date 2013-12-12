package com.exiapps.puydufou.fragment;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.adapters.BoutiqueAdapter;
import com.exiapps.puydufou.model.OnReceiveListener;
import com.exiapps.puydufou.model.ServiceManager;
import com.exiapps.puydufou.model.entities.Service;

public class BoutiqueFragment extends AbstractFragment implements OnReceiveListener {

	private ListView listView;
	private ServiceManager serviceManager;

	public BoutiqueFragment() {
		this.title = "Boutique";
		this.serviceManager = new ServiceManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_boutique, container, false);

		listView = (ListView) view.findViewById(R.id.boutiqueList);

		this.refresh();

		return view;
	}

	@Override
	public void refresh() {
		this.serviceManager.getAllBoutiqueDetailAsync();
		this.serviceManager.setOnReceiveListener((OnReceiveListener) this);
	}

	@Override
	public void OnReceive(Object object) {
		List<Service> services = (List<Service>) object;

		this.listView.setAdapter(new BoutiqueAdapter(getActivity(), R.id.boutiqueItemName, services));
	}

}