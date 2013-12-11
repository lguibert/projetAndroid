package com.exiapps.puydufou.adapters;

import java.util.List;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.model.entities.Spectacle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpectacleAdapter extends ArrayAdapter<Spectacle> {

	private Context context;
	private List<Spectacle> spectacles;
	
	public SpectacleAdapter(Context context, int resource,List<Spectacle> spectacles) {
		super(context, resource,spectacles);
		this.context = context;
		this.spectacles = spectacles;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.spectacle_list_item, parent,false);
		
		TextView textView = (TextView) view.findViewById(R.id.spectacleItemName);
		
		textView.setText(spectacles.get(position).getNom());
		
		return view;
	}
	
	
}
