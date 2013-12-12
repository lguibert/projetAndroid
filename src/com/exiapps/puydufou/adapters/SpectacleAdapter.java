package com.exiapps.puydufou.adapters;

import java.util.List;

import com.exiapps.puydufou.R;
import com.exiapps.puydufou.bestschedule.Time;
import com.exiapps.puydufou.bestschedule.TimeUtils;
import com.exiapps.puydufou.model.entities.Spectacle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
		
		TextView twName = (TextView) view.findViewById(R.id.spectacleItemName);
		TextView twTime = (TextView) view.findViewById(R.id.spectacleItemTime);
		
		twName.setText(spectacles.get(position).getNom());
		
		Spectacle spectacle = this.spectacles.get(position);
		
		
		String[] hours = spectacle.getHours();
		
		for (int i = 0; i < hours.length; i++) {
			
			Time time = TimeUtils.stringToTime(hours[i]);
			
			twTime.append(time.toString());
			
			if(i < hours.length - 1){
				twTime.append(", ");
			}
		}
		
		return view;
	}
	
	
}
