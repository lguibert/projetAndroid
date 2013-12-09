package com.exiapps.puydufou.arrayadapters;


import com.exiapps.puydufou.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DrawerArrayAdapter extends ArrayAdapter<MenuItem> {


	private Context context;


	public DrawerArrayAdapter(Context context, int resource) {
		super(context, resource);
		

		this.add(MenuItem.MAP);
		this.add(MenuItem.SCHEDULE);
		this.add(MenuItem.INFO);		
		this.add(MenuItem.SHOPS);
		this.add(MenuItem.RESTAURANTS);
		this.add(MenuItem.HISTORIC);
		
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.drawer_list_item, parent,false);
		
		TextView tv = (TextView) view.findViewById(R.id.drawerMenuItem);
		tv.setText(this.getItem(position).getString(context));
		
		return view;
		
	}
	
	
}
