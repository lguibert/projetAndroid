package com.exiapps.puydufou.adapters;

import java.util.List;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.bestschedule.Representation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RepresentationArrayAdapter extends ArrayAdapter<Representation>{

	private Context context;
	private List<Representation> spectacles;
	
	public RepresentationArrayAdapter(Context context, int textViewResourceId, List<Representation> objects) {
		super(context, textViewResourceId, objects);

		this.context = context;
		this.spectacles = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.spectacle_list_item, parent, false);
		
		TextView twName = (TextView) view.findViewById(R.id.spectacleItemName);
		TextView twTime = (TextView) view.findViewById(R.id.spectacleItemTime);
		

		twName.setText(this.spectacles.get(position).getSpectacle().getNom());
		
		twTime.setText(this.spectacles.get(position).getTime().toString());
		return view;
	}
	
	

}
