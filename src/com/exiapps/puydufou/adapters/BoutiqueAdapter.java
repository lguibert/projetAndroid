package com.exiapps.puydufou.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.entities.Service;

public class BoutiqueAdapter extends ArrayAdapter<Service> {

	private Context context;
	private List<Service> services;

	public BoutiqueAdapter(Context context, int resource, List<Service> services) {
		super(context, resource, services);
		this.context = context;
		this.services = services;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.boutique_list_item, parent, false);

		TextView textView = (TextView) view.findViewById(R.id.boutiqueItemName);

		textView.setText(services.get(position).getNom());

		return view;
	}

}
